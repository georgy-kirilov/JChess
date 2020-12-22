package models.pieces;

import java.util.ArrayList;
import java.util.Collection;

import common.Position;
import enums.PieceColor;
import jthrow.JThrower;
import models.boards.Board;

public abstract class BasePiece implements Piece
{
	private final PieceColor color;
	private boolean moved;
	
	public BasePiece(PieceColor color)
	{	
		JThrower.throwIf(color, "color").isNull();
		this.color = color;
		
		this.moved = false;
	}
	
	public PieceColor getColor()
	{
		return this.color;
	}
	
	public boolean isMoved()
	{
		return this.moved;
	}
	
	public void move()
	{
		this.moved = true;
	}
	
	protected ArrayList<Position> getReachableConsequtivePositions(Position currentPosition, Board board, 
			int rowOffset, int columnOffset)
	{
		ArrayList<Position> allPossiblePositions = new ArrayList<>();
		
		Position nextPosition = new Position(currentPosition.getRow() + rowOffset, 
				currentPosition.getColumn() + columnOffset);
		
		while (board.isPositionInside(nextPosition))
		{
			if (board.isEmptyAt(nextPosition))
			{
				allPossiblePositions.add(nextPosition);
			}
			else
			{
				if (board.getAt(nextPosition).getColor() != this.getColor())
				{
					allPossiblePositions.add(nextPosition);
				}
				
				break;
			}
			
			nextPosition = new Position(nextPosition.getRow() + rowOffset, 
					nextPosition.getColumn() + columnOffset);
		}
		
		return allPossiblePositions;
	}
	
	public abstract Iterable<Position> getAllReachablePositions(Position currentPosition, Board board);
}
