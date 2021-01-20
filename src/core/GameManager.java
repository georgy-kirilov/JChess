package core;

import java.util.ArrayList;

import common.Position;
import enums.GameStatus;
import enums.PieceColor;
import models.pieces.King;
import models.boards.Board;
import models.pieces.Piece;
import validation.ThrowHelper;

public class GameManager
{	
	private final Board board;
	private boolean gameOver;
	private PieceColor currentPlayerColor;
	
	public GameManager(Board board, PieceColor startPlayerColor)
	{
		ThrowHelper.throwIfNull(board);
		this.board = board;
		
		ThrowHelper.throwIfNull(startPlayerColor);
		currentPlayerColor = startPlayerColor;
		
		gameOver = false;
	}
	
	public PieceColor getCurrentPlayerColor()
	{
		return currentPlayerColor;
	}
	
	public boolean isGameOver()
	{
		return gameOver;
	}
	
	public Board getBoard()
	{
		return board;
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
	
	public Iterable<Position> getReachablePositions(Position piecePosition)
	{
		ArrayList<Position> reachablePositions = new ArrayList<>();
		Piece piece = board.getAt(piecePosition);
		
		if (!board.isEmptyAt(piecePosition) && piece.getColor() == getCurrentPlayerColor())
		{	
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
		
		return reachablePositions;
	}
	
	public GameStatus makeMove(Position from, Position to)
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
		board.setToEmpty(from);
		board.setAt(to, piece);
		
		piece.move();
		nextPlayer();
		board.rotate();
		
		if (getKing().isCheckmated(getKingPosition(), board))
		{
			gameOver = true;
			return GameStatus.CHECKMATE;
		}
		
		if (getKing().isChecked(getKingPosition(), board))
		{
			return GameStatus.CHECK;			
		}
		
		return GameStatus.CONTINUE;
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
