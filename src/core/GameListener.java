package core;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collection;

import common.Castle;
import common.Helper;
import common.Position;
import common.OffsetPair;

import models.pieces.*;
import models.boards.Board;

import enums.PieceColor;

public class GameListener
{	
	private final String CANNOT_OBTAIN_COLOR_MESSAGE = "Cannot obtain %s color because the game isn't over";
	
	private final HashMap<Piece, Collection<Position>> piecesAndPositions;
	private Collection<Castle> possibleCastles;
	
	private final Board board;
	private final GameAnnouncer gameAnnouncer;
	
	private boolean gameOver;
	private PieceColor currentPlayerColor;
	private Position enPassantCapturePosition = null;
	private Position enPassantPawnPosition = null;
	
	public GameListener(Board board, GameAnnouncer gameAnnouncer)
	{	
		Helper.throwIfNull(board);
		this.board = board;
		
		Helper.throwIfNull(gameAnnouncer);
		this.gameAnnouncer = gameAnnouncer;
		
		currentPlayerColor = PieceColor.WHITE;
		gameOver = false;
		
		piecesAndPositions = new HashMap<>();
		possibleCastles = getKing().getPossibleCastles(getKingPosition(), board);
	}
	
	public Collection<Position> onFromPositionSelected(Position from)
	{
		Collection<Position> reachablePositions = getReachablePositions(from);
		
		if (getKingPosition().equals(from))
		{
			reachablePositions.addAll(getCastlingPositions());
			gameAnnouncer.announceCastlingPositions(getCastlingPositions());
		}
		
		Piece piece = board.getAt(from);
		
		if (canCurrentPlayerPerformEnPassant(piece, from))
		{
			reachablePositions.add(enPassantCapturePosition);
		}
		
		return reachablePositions;
	}
	
	public void onToPositionSelected(Position from, Position to)
	{
		if (isGameOver())
		{
			throw new RuntimeException("Cannot make moves because the game is over");						
		}
		
		boolean positionValid = false;
		
		for (Position position : getReachablePositions(from))
		{
			if (position.equals(to))
			{
				positionValid = true;
				break;
			}
		}
		
		if (!positionValid)
		{
			throw new IllegalArgumentException("'To' position is not reachable");				
		}
	
		Piece piece = board.getAt(from);
		
		if (canCurrentPlayerPerformEnPassant(piece, from))
		{
			board.setToEmpty(enPassantPawnPosition);			
		}
		
		disableEnPassant();
		
		boolean canNextPlayerPerformEnPassant = piece instanceof Pawn && !piece.isMoved() && 
				from.moveBy(OffsetPair.UP).moveBy(OffsetPair.UP).equals(to);
		
		if (canNextPlayerPerformEnPassant)
		{
			enPassantPawnPosition = to.flipOver(board);
			enPassantCapturePosition = from.moveBy(OffsetPair.UP).flipOver(board);
		}
		
		if (piece instanceof King && isValidCastlingPosition(to))
		{
			performCastling(to);
		}
		
		piece = board.getAt(from);
		board.setToEmpty(from);
		board.setAt(to, piece);
		
		piece.move();
		
		if (piece instanceof Pawn && ((Pawn)piece).canBePromoted(to))
		{
			gameAnnouncer.redrawBoard();
			Piece newPiece = gameAnnouncer.announcePawnPromotion(piece.getColor());
			board.setAt(to, newPiece);
		}
		
		nextPlayer();
		
		if (getKing().isChecked(getKingPosition(), board))
		{
			if (getKing().isCheckmated(getKingPosition(), board))
			{
				gameOver = true;
				gameAnnouncer.announceGameOver(getWinnerColor());
				return;
			}
			
			gameAnnouncer.announceCheck();
		}
	}
	
	public boolean isGameOver()
	{
		return gameOver;
	}
	
	public PieceColor getCurrentPlayerColor()
	{
		return currentPlayerColor;
	}
	
	public PieceColor getLoserColor()
	{
		if (isGameOver()) 
		{
			return getCurrentPlayerColor();			
		}
		
		throw new RuntimeException(String.format(CANNOT_OBTAIN_COLOR_MESSAGE, "loser"));
	}
	
	public PieceColor getWinnerColor()
	{
		if (isGameOver())
		{
			return getLoserColor() == PieceColor.WHITE ? PieceColor.BLACK : PieceColor.WHITE;
		}
		
		throw new RuntimeException(String.format(CANNOT_OBTAIN_COLOR_MESSAGE, "winner"));
	}
	
	public Collection<Position> getReachablePositions(Position piecePosition)
	{	
		ArrayList<Position> reachablePositions = new ArrayList<>();
		
		Piece piece = board.getAt(piecePosition);
		
		if (!board.isEmptyAt(piecePosition) && piece.getColor() == getCurrentPlayerColor())
		{	
			if (piecesAndPositions.containsKey(piece))
			{
				return piecesAndPositions.get(piece);				
			}
			
			Collection<Position> allPositions = piece.getReachablePositions(piecePosition, board);
			
			for (Position position : allPositions)
			{
				Piece captured = board.setToEmpty(position);
				board.setAt(position, piece);
				board.setToEmpty(piecePosition);
				
				if (!getKing().isChecked(getKingPosition(), board))
				{
					reachablePositions.add(position);
				}
				
				board.setAt(position, captured);
				board.setAt(piecePosition, piece);
			}
		}
		
		piecesAndPositions.put(piece, reachablePositions);
		
		return reachablePositions;
	}
	
	private void performCastling(Position position)
	{
		for (Castle castle : possibleCastles)
		{
			if (castle.getNewKingPosition().equals(position))
			{
				board.setToEmpty(castle.getOldRookPosition());
				board.setAt(castle.getNewRookPosition(), castle.getRook());
				return;
			}
		}
		
		throw new IllegalArgumentException("Cannot perform castling");
	}
	
	private Collection<Position> getCastlingPositions()
	{
		Collection<Position> castlingPositions = new ArrayList<Position>();
		
		for (Castle castle : possibleCastles)
		{
			castlingPositions.add(castle.getNewKingPosition());
		}
		
		return castlingPositions;
	}
	
	private boolean isValidCastlingPosition(Position position)
	{
		for (Castle castle : possibleCastles)
		{
			if (castle.getNewKingPosition().equals(position))
			{
				return true;
			}
		}
		
		return false;
	}
	
	private King getKing()
	{
		return (King)board.getAt(getKingPosition());
	}
	
	private Position getKingPosition()
	{
		for	(int i = 0; i < board.getHeight(); i++)
		{
			for (int j = 0; j < board.getWidth(); j++)
			{
				Piece piece = board.getAt(i, j);
				
				boolean kingFound = !board.isEmptyAt(i, j) 
						&& piece.getColor() == getCurrentPlayerColor()
						&& piece.getClass().equals(King.class);
				
				if (kingFound)
				{
					return new Position(i, j);					
				}
			}
		}
		
		throw new RuntimeException("King not found");
	}
	
	private boolean canCurrentPlayerPerformEnPassant(Piece piece, Position position)
	{
		return !board.isEmptyAt(position) && piece instanceof Pawn && 
				piece.getColor() == getCurrentPlayerColor() &&
				(position.moveBy(OffsetPair.TOP_LEFT).equals(enPassantCapturePosition) ||
				position.moveBy(OffsetPair.TOP_RIGHT).equals(enPassantCapturePosition));
	}
	
	private void disableEnPassant()
	{
		enPassantPawnPosition = null;
		enPassantCapturePosition = null;
	}
	
	private void nextPlayer()
	{
		currentPlayerColor = currentPlayerColor == PieceColor.WHITE ? 
				PieceColor.BLACK : PieceColor.WHITE;
		
		board.rotate();
		piecesAndPositions.clear();
		gameAnnouncer.redrawBoard();
		possibleCastles = getKing().getPossibleCastles(getKingPosition(), board);
	}
}
