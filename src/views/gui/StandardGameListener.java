package views.gui;

import java.util.TreeSet;
import java.util.ArrayList;

import common.Position;
import jthrow.JThrower;
import enums.PieceColor;
import models.boards.Board;
import models.pieces.Piece;

public class StandardGameListener implements GameListener
{
	private final Board board;
	private final PieceColor[] players;
	private int currentPlayerIndex;
	
	public StandardGameListener(Board board)
	{
		JThrower.throwIf(board).isNull();
		this.board = board;
		
		this.players = this.getUniquePlayers(board);
		this.currentPlayerIndex = 0;
	}
	
	@Override
	public Iterable<Position> onFromPositionSelected(Position from)
	{
		Piece piece = this.board.getAt(from);
	
		//TODO: Add validation for check and checkmate
		
		if (piece != Board.EMPTY_CELL && piece.getColor() == this.currentPlayer())
		{
			return piece.getAllReachablePositions(from, this.board);
		}
		
		return new ArrayList<Position>();
	}

	@Override
	public void onToPositionSelected(Position from, Position to)
	{
		boolean isToPositionValid = false;
		
		for (Position position : this.onFromPositionSelected(from))
		{
			if (position.equals(to))
			{
				isToPositionValid = true;
			}
		}
		
		if (!isToPositionValid)
		{
			throw new IllegalArgumentException("'to' position is not reachable");
		}
		
		Piece piece = this.board.getAt(from);
		this.board.setToEmpty(from);
		this.board.setAt(to, piece);
		
		piece.move();
		this.nextPlayer();
		
		//this.board.flipBoard();
	}
	
	private PieceColor currentPlayer()
	{
		return this.players[this.currentPlayerIndex];
	}
	
	private void nextPlayer()
	{	
		if (this.currentPlayerIndex == this.players.length - 1)
		{
			this.currentPlayerIndex = 0;
		}
		else
		{
			this.currentPlayerIndex++;
		}
	}
	
	private PieceColor[] getUniquePlayers(Board board)
	{
		TreeSet<PieceColor> uniquePlayers = new TreeSet<>();
		
		Position position = new Position();

		for (int row = 0; row < board.getHeight(); row++)
		{
			position.setRow(row);
			
			for (int col = 0; col < board.getWidth(); col++)
			{
				position.setColumn(col);
				
				if (!board.isEmptyAt(position))
				{
					PieceColor player = board.getAt(position).getColor();
					uniquePlayers.add(player);
				}
			}
		}
		
		return uniquePlayers.toArray(new PieceColor[uniquePlayers.size()]);
	}
}
