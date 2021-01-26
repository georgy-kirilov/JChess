package core;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collection;

import common.Castle;
import common.GlobalConstants;
import common.Helper;
import common.Position;
import common.OffsetPair;

import models.pieces.*;
import models.boards.Board;

import enums.PieceColor;

public class GameListener
{	
	private Collection<Castle> possibleCastles;
	private final HashMap<Piece, Collection<Position>> piecesAndPositions;
	
	private final Board board;
	private final IOProvider ioProvider;
	
	private boolean gameOver;
	private PieceColor currentPlayerColor;
	
	private Position enPassantPawnPosition;
	private Position enPassantCapturePosition;
	
	public GameListener(Board board, IOProvider ioProvider)
	{	
		Helper.throwIfNull(board);
		this.board = board;
		
		Helper.throwIfNull(ioProvider);
		this.ioProvider = ioProvider;
		
		currentPlayerColor = PieceColor.WHITE;
		gameOver = false;
		
		piecesAndPositions = new HashMap<>();
		possibleCastles = getKing().getPossibleCastles(getKingPosition(), board);
	}
	
	public Collection<Position> onFromPositionSelected(Position from)
	{
		if (getKingPosition().equals(from))
		{
			ioProvider.announceCastlingPositions(getCastlingPositions());			
		}
		
		return getReachablePositions(from);
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
		
		boolean canNextPlayerPerformEnPassant = piece instanceof Pawn && !piece.isMoved()
				 && from.moveBy(OffsetPair.UP).moveBy(OffsetPair.UP).equals(to);
		
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
			ioProvider.redrawBoard();
			Piece newPiece = ioProvider.announcePawnPromotion(piece.getColor());
			board.setAt(to, newPiece);
		}
		
		nextPlayer();
		nextTurn();
		
		if (getKing().isChecked(getKingPosition(), board))
		{
			if (getKing().isCheckmated(getKingPosition(), board))
			{
				gameOver = true;
				ioProvider.announceGameOver(getWinnerColor());
				return;
			}
			
			ioProvider.announceCheck();
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
		
		throw new RuntimeException(GlobalConstants.ErrorMessages.CANNOT_OBTAIN_LOSER);
	}
	
	public PieceColor getWinnerColor()
	{
		if (isGameOver())
		{
			return getLoserColor() == PieceColor.WHITE ? PieceColor.BLACK : PieceColor.WHITE;
		}
		
		throw new RuntimeException(GlobalConstants.ErrorMessages.CANNOT_OBTAIN_WINNER);
	}
	
	public Collection<Position> getReachablePositions(Position piecePosition)
	{	
		Collection<Position> reachablePositions = new ArrayList<>();
		
		Piece piece = board.getAt(piecePosition);
		
		boolean pieceExists = !board.isEmptyAt(piecePosition) 
				&& piece.getColor() == getCurrentPlayerColor();
		
		if (pieceExists)
		{	
			// Checks whether the piece has already been used and returns its data from the hash map
			
			if (piecesAndPositions.containsKey(piece))
			{
				return piecesAndPositions.get(piece);				
			}
			
			Collection<Position> allPiecePositions = piece.getReachablePositions(piecePosition, board);
			
			for (Position position : allPiecePositions)
			{
				// Makes the move
				
				Piece captured = board.setToEmpty(position);
				board.setAt(position, piece);
				board.setToEmpty(piecePosition);
				
				// Checks whether the move is valid
				
				if (!getKing().isChecked(getKingPosition(), board))
				{
					reachablePositions.add(position);
				}
				
				// Reverts the move
				
				board.setAt(position, captured);
				board.setAt(piecePosition, piece);
			}
			
			// Checks whether the piece is King and if so adds its castling positions to the list
			
			if (getKingPosition().equals(piecePosition))
			{
				reachablePositions.addAll(getCastlingPositions());
			}
			
			if (canCurrentPlayerPerformEnPassant(piece, piecePosition))
			{
				reachablePositions.add(enPassantCapturePosition);
			}
		}
		
		piecesAndPositions.put(piece, reachablePositions);
		
		return reachablePositions;
	}
	
	private void performCastling(Position kingCastlingPosition)
	{
		for (Castle castle : possibleCastles)
		{
			if (castle.getNewKingPosition().equals(kingCastlingPosition))
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
	
	private boolean isValidCastlingPosition(Position kingCastlingPosition)
	{
		for (Castle castle : possibleCastles)
		{
			if (castle.getNewKingPosition().equals(kingCastlingPosition))
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
						&& piece instanceof King;
				
				if (kingFound)
				{
					return new Position(i, j);					
				}
			}
		}
		
		throw new RuntimeException("King not found");
	}
	
	private void disableEnPassant()
	{
		enPassantPawnPosition = null;
		enPassantCapturePosition = null;
	}
	
	private boolean canCurrentPlayerPerformEnPassant(Piece piece, Position piecePosition)
	{
		return !board.isEmptyAt(piecePosition) 
				&& piece instanceof Pawn 
				&& piece.getColor() == getCurrentPlayerColor() 
				&& (piecePosition.moveBy(OffsetPair.TOP_LEFT).equals(enPassantCapturePosition) 
				|| piecePosition.moveBy(OffsetPair.TOP_RIGHT).equals(enPassantCapturePosition));
	}
	
	private void nextPlayer()
	{
		currentPlayerColor = currentPlayerColor == PieceColor.WHITE ? 
				PieceColor.BLACK : PieceColor.WHITE;
	}
	
	private void nextTurn()
	{
		board.rotate();
		piecesAndPositions.clear();
		ioProvider.redrawBoard();
		possibleCastles = getKing().getPossibleCastles(getKingPosition(), board);
	}
}
