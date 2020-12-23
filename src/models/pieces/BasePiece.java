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
	
	public boolean canCaptureAt(Position position, Board board)
	{
		return board.isPositionInside(position) && !board.isEmptyAt(position) 
				&& board.getAt(position).getColor() != this.getColor();
	}
	
	public boolean canMoveFreelyTo(Position position, Board board)
	{
		return board.isPositionInside(position) && board.isEmptyAt(position);
	}
	
	protected ArrayList<Position> getReachableConsequtivePositions(Position currentPosition, Board board, MovementOffsetPair offsetPair)
	{
		ArrayList<Position> positions = new ArrayList<>();
		
		Position nextPosition = currentPosition.move(offsetPair);
		
		while (true)
		{
			if (this.canMoveFreelyTo(nextPosition, board))
			{
				positions.add(nextPosition);
			}
			else
			{
				if (this.canCaptureAt(nextPosition, board))
				{
					positions.add(nextPosition);
				}
				
				break;
			}
			
			nextPosition = nextPosition.move(offsetPair);
		}
		
		return positions;
	}
	
	protected Iterable<Position> getReachableSinglePositions(Position currentPosition, Board board, MovementOffsetPair[] offsetPairs)
	{
		ArrayList<Position> positions = new ArrayList<>();
		
		for (MovementOffsetPair offsetPair : offsetPairs)
		{
			Position nextPosition = currentPosition.move(offsetPair);
			
			boolean isPositionValid = this.canMoveFreelyTo(nextPosition, board) || this.canCaptureAt(nextPosition, board);
				
			
		
			if (isPositionValid)
			{
				positions.add(nextPosition);
			}
		}
		
		return positions;
	}
	
	public abstract Iterable<Position> getAllReachablePositions(Position currentPosition, Board board);
}
