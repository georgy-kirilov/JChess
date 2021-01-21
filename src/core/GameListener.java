package core;

import java.util.HashMap;
import java.util.ArrayList;

import common.Helper;
import common.Position;
import enums.PieceColor;
import models.pieces.King;
import models.pieces.Piece;
import models.boards.Board;
import views.gui.BoardView;

public class GameListener
{	
	private final HashMap<Piece, Iterable<Position>> piecesAndPositions;
	
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
	
	public Iterable<Position> onFromPositionSelected(Position from)
	{
		return getReachablePositions(from);
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
		board.setToEmpty(from);
		board.setAt(to, piece);
		
		piece.move();
		
		//TODO: Pawn promotion logic should go in here
		
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
	
	public Iterable<Position> getReachablePositions(Position piecePosition)
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
