package views.gui;

import java.util.TreeSet;
import java.util.ArrayList;

import common.Position;
import jthrow.JThrower;
import enums.GameStatus;
import enums.PieceColor;
import models.pieces.King;
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
		
		this.players = this.getUniquePlayers(board);
		this.currentPlayerIndex = 0;
		this.kingPosition();
	}
	
	@Override
	public Iterable<Position> onFromPositionSelected(Position from)
	{
		Piece piece = this.board.getAt(from);
		
		ArrayList<Position> validPositions = new ArrayList<>();
		
		if (!this.board.isEmptyAt(from) && piece.getColor() == this.currentPlayer())
		{
			Iterable<Position> allPositions = piece.getAllReachablePositions(from, this.board);
			
			King king = (King)this.board.getAt(this.kingPosition());
			
			for (Position position : allPositions)
			{
				Piece captured = this.board.getAt(position);
				this.board.setAt(position, piece);
				this.board.setToEmpty(from);
				
				if (!king.isChecked(this.kingPosition(), this.board))
				{
					validPositions.add(position);
				}
				
				this.board.setAt(position, captured);
				this.board.setAt(from, piece);
			}
		}
		
		return validPositions;
	}

	@Override
	public GameStatus onToPositionSelected(Position from, Position to)
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
			throw new IllegalArgumentException("'To' position is not reachable");
		}
		
		Piece piece = this.board.getAt(from);
		this.board.setToEmpty(from);
		this.board.setAt(to, piece);
		
		piece.move();
		this.nextPlayer();
		this.board.rotateAnticlockwise(2);
		
		Position kingPosition = this.kingPosition();
		King king = (King)this.board.getAt(kingPosition); 
		
		if (king.isChecked(kingPosition, this.board))
		{
			if (king.isCheckmated(kingPosition, this.board))
			{
				return GameStatus.CHECKMATE;
			}
	
			return GameStatus.CHECK;
		}
	
		return GameStatus.CONTINUE;
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
	
	private Position kingPosition()
	{
		for	(int i = 0; i < this.board.getHeight(); i++)
		{
			for (int j = 0; j < this.board.getWidth(); j++)
			{
				if (!this.board.isEmptyAt(i, j))
				{
					Piece piece = this.board.getAt(i, j);
					
					if (piece.getColor() == this.currentPlayer() && piece.getClass().equals(King.class))
					{
						return new Position(i, j);
					}
				}
			}
		}
		
		throw new RuntimeException("King not found");
	}
	
	private PieceColor[] getUniquePlayers(Board board)
	{
		TreeSet<PieceColor> uniquePlayers = new TreeSet<>();

		for (int row = 0; row < board.getHeight(); row++)
		{
			for (int col = 0; col < board.getWidth(); col++)
			{	
				if (!board.isEmptyAt(row, col))
				{
					PieceColor player = board.getAt(row, col).getColor();
					uniquePlayers.add(player);
				}
			}
		}
		
		return uniquePlayers.toArray(new PieceColor[uniquePlayers.size()]);
	}
}
