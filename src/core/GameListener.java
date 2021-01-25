package core;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collection;

import common.Castle;
import common.Helper;
import models.pieces.*;
import common.Position;
import enums.PieceColor;
import models.boards.Board;
import views.gui.BoardView;

public class GameListener
{	
	private final HashMap<Piece, Collection<Position>> piecesAndPositions;
	
	private boolean gameOver;
	private final Board board;
	private final BoardView boardView;
	
	private PieceColor currentPlayerColor;
	
	public GameListener(Board board, BoardView boardView)
	{	
		piecesAndPositions = new HashMap<>();
		
		Helper.throwIfNull(board);
		this.board = board;
		
		Helper.throwIfNull(boardView);
		this.boardView = boardView;
		
		currentPlayerColor = PieceColor.WHITE;
		gameOver = false;
	}
	
	public Collection<Position> onFromPositionSelected(Position from)
	{
		Collection<Position> reachablePositions = getReachablePositions(from);
		
		if (getKingPosition().equals(from))
		{
			ArrayList<Position> castlePositions = new ArrayList<>();
			
			for (Castle pair : getKing().getPossibleCastles(getKingPosition(), board))
			{
				castlePositions.add(pair.getNewKingPosition());				
			}
			
			reachablePositions.addAll(castlePositions);
			boardView.makeCellsCastlable(castlePositions);
		}
		
		return reachablePositions;
	}
	
	public void onToPositionSelected(Position from, Position to)
	{
		if (isGameOver())
			throw new RuntimeException("Cannot make moves if the game is over");
		
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
			throw new IllegalArgumentException("'To' position is not reachable");
	
		Piece piece = board.getAt(from);
		
		if (piece.getClass().equals(King.class) && isCastlePosition(to))
		{
			performCastle(to);
		}
		
		piece = board.getAt(from);
		board.setToEmpty(from);
		board.setAt(to, piece);
		
		piece.move();
		
		if (piece.getClass().equals(Pawn.class) && ((Pawn)piece).canBePromoted(to))
		{
			boardView.redraw();
			Piece newPiece = boardView.announcePawnPromotion(piece.getColor());
			board.setAt(to, newPiece);
		}
		
		nextPlayer();
		board.rotate();
		piecesAndPositions.clear();
		
		boardView.redraw();
		
		if (getKing().isChecked(getKingPosition(), board))
		{
			if (getKing().isCheckmated(getKingPosition(), board))
			{
				gameOver = true;
				boardView.announceGameOver(getWinnerColor());
				return;
			}
			
			boardView.announceCheck();
		}
	}
	
	public PieceColor getCurrentPlayerColor()
	{
		return currentPlayerColor;
	}
	
	public boolean isGameOver()
	{
		return gameOver;
	}
	
	public PieceColor getLoserColor()
	{
		if (isGameOver()) 
			return getCurrentPlayerColor();
		
		throw new RuntimeException("Cannot obtain loser color because the game is not over");
	}
	
	public PieceColor getWinnerColor()
	{
		if (isGameOver())
		{
			return getLoserColor() == PieceColor.WHITE 
					? PieceColor.BLACK : PieceColor.WHITE;
		}
		
		throw new RuntimeException("Cannot obtain winner color because the game is not over");
	}
	
	public Collection<Position> getReachablePositions(Position piecePosition)
	{	
		ArrayList<Position> reachablePositions = new ArrayList<>();
		Piece piece = board.getAt(piecePosition);
		
		if (!board.isEmptyAt(piecePosition) && piece.getColor() == getCurrentPlayerColor())
		{	
			if (piecesAndPositions.containsKey(piece))
				return piecesAndPositions.get(piece);
			
			Iterable<Position> allPositions = piece.getReachablePositions(piecePosition, board);
			
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
	
	private void performCastle(Position position)
	{
		for (Castle pair : getKing().getPossibleCastles(getKingPosition(), board))
		{
			if (pair.getNewKingPosition().equals(position))
			{
				board.setToEmpty(pair.getOldRookPosition());
				board.setAt(pair.getNewRookPosition(), pair.getRook());
			}
		}
	}
	
	private boolean isCastlePosition(Position position)
	{
		for (Castle pair : getKing().getPossibleCastles(getKingPosition(), board))
			if (pair.getNewKingPosition().equals(position))
				return true;
		
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
					return new Position(i, j);
			}
		}
		
		throw new RuntimeException("King not found");
	}
	
	private void nextPlayer()
	{
		currentPlayerColor = currentPlayerColor == PieceColor.WHITE 
				? PieceColor.BLACK : PieceColor.WHITE;
	}
}
