package models.pieces;

import java.util.ArrayList;

import common.Position;
import enums.PieceColor;
import jthrow.JThrower;
import models.boards.Board;
import common.MovementOffsetPair;

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
			MovementOffsetPair offsetPair)
	{
		ArrayList<Position> allPossiblePositions = new ArrayList<>();
		
		Position nextPosition = new Position(currentPosition.getRow() + offsetPair.getRowOffset(), 
				currentPosition.getColumn() + offsetPair.getColumnOffset());
		
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
			
			nextPosition = new Position(nextPosition.getRow() + offsetPair.getRowOffset(), 
					nextPosition.getColumn() + offsetPair.getColumnOffset());
		}
		
		return allPossiblePositions;
	}
	
	protected Iterable<Position> getReachableSinglePositions(Position currentPosition, Board board, 
			MovementOffsetPair[] offsetPairs)
	{
		ArrayList<Position> positions = new ArrayList<>();
		
		for (MovementOffsetPair pair : offsetPairs)
		{
			Position nextPosition = new Position(currentPosition.getRow() + pair.getRowOffset(), 
					currentPosition.getColumn() + pair.getColumnOffset());
			
			boolean isPositionValid = board.isPositionInside(nextPosition) && 
					(board.isEmptyAt(nextPosition) || board.getAt(nextPosition).getColor() != this.getColor());
			
			if (isPositionValid)
			{
				positions.add(nextPosition);
			}
		}
		
		return positions;
	}
	
	public abstract Iterable<Position> getAllReachablePositions(Position currentPosition, Board board);
}
