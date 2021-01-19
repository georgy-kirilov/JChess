package views.gui;

import java.util.TreeSet;

import javax.swing.JOptionPane;

import java.util.ArrayList;

import common.Position;
import jthrow.JThrower;
import enums.GameStatus;
import enums.PieceColor;
import models.pieces.King;
import models.pieces.Pawn;
import models.pieces.Piece;
import models.boards.Board;

public class StandardGameListener implements GameListener
{
	private final Board board;
	private final PieceColor[] players;
	private int currentPlayerIndex;
	
	public StandardGameListener(Board board) 
	{
		JThrower.throwIf(board).isNull();
		this.board = board;
		players = getUniquePlayers(board);
		currentPlayerIndex = 0;
	}
	
	@Override
	public Iterable<Position> onFromPositionSelected(Position from)
	{
		Piece piece = board.getAt(from);
		
		ArrayList<Position> validPositions = new ArrayList<>();
		
		if (!board.isEmptyAt(from) && piece.getColor() == currentPlayer())
		{
			Iterable<Position> allPositions = piece.getReachablePositions(from, board);
			
			King king = (King)board.getAt(kingPosition());
			
			for (Position position : allPositions)
			{
				Piece captured = board.getAt(position);
				board.setAt(position, piece);
				board.setToEmpty(from);
				
				if (!king.isChecked(kingPosition(), board))
				{
					validPositions.add(position);
				}
				
				board.setAt(position, captured);
				board.setAt(from, piece);
			}
		}
		
		return validPositions;
	}

	@Override
	public GameStatus onToPositionSelected(Position from, Position to)
	{
		boolean isToPositionValid = false;
		
		for (Position position : onFromPositionSelected(from))
		{
			if (position.equals(to))
			{
				isToPositionValid = true;
				break;
			}
		}
		
		if (!isToPositionValid)
		{
			throw new IllegalArgumentException("'To' position is not reachable");
		}
		
		Piece piece = board.getAt(from);
		board.setToEmpty(from);
		board.setAt(to, piece);
		
		piece.move();
		
		if (piece.getClass().equals(Pawn.class) && ((Pawn)piece).canBePromoted(to))
		{
			JOptionPane.showMessageDialog(null, "Queen, Rook, Bishop, Knight");
			JOptionPane.showInputDialog("Stuf");
		}
		
		nextPlayer();
		board.rotateAnticlockwise(2);
		
		Position kingPosition = kingPosition();
		King king = (King)board.getAt(kingPosition);
		
		if (king.isChecked(kingPosition(), board))
		{
			if (king.isCheckmated(kingPosition(), board))
				return GameStatus.CHECKMATE;
	
			return GameStatus.CHECK;
		}
	
		return GameStatus.CONTINUE;
	}
	
	private PieceColor currentPlayer()
	{
		return players[currentPlayerIndex];
	}
	
	private void nextPlayer()
	{	
		currentPlayerIndex = currentPlayerIndex == players.length - 1 
				? 0 : currentPlayerIndex + 1;
	}
	
	private Position kingPosition()
	{
		for	(int i = 0; i < board.getHeight(); i++)
		{
			for (int j = 0; j < board.getWidth(); j++)
			{
				Piece piece = board.getAt(i, j);
				
				boolean kingFound = !board.isEmptyAt(i, j) 
						&& piece.getColor() == currentPlayer() 
						&& piece.getClass().equals(King.class);
				
				if (kingFound) 
					return new Position(i, j);
			}
		}
		
		throw new RuntimeException("King not found");
	}
	
	private PieceColor[] getUniquePlayers(Board board)
	{
		TreeSet<PieceColor> uniquePlayers = new TreeSet<>();

		for (int i = 0; i < board.getHeight(); i++)
		{
			for (int j = 0; j < board.getWidth(); j++)
			{	
				if (!board.isEmptyAt(i, j))
				{
					PieceColor player = board.getAt(i, j).getColor();
					uniquePlayers.add(player);
				}
			}
		}
		
		return uniquePlayers.toArray(new PieceColor[uniquePlayers.size()]);
	}
}
