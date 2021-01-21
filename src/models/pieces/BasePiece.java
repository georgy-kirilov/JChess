package models.pieces;

import java.util.ArrayList;

import common.Helper;
import common.Position;
import enums.PieceColor;
import common.OffsetPair;
import models.boards.Board;

public abstract class BasePiece implements Piece
{
	private final PieceColor color;
	private boolean moved;
	
	public BasePiece(PieceColor color)
	{	
		Helper.throwIfNull(color);
		this.color = color;
		moved = false;
	}
	
	public PieceColor getColor()
	{
		return color;
	}
	
	public boolean isMoved()
	{
		return moved;
	}
	
	public void move()
	{
		moved = true;
	}
	
	public boolean canCaptureAt(Position position, Board board)
	{
		return board.isPositionInside(position) && !board.isEmptyAt(position) 
				&& board.getAt(position).getColor() != getColor();
	}
	
	public boolean canMoveFreelyTo(Position position, Board board)
	{
		return board.isPositionInside(position) && board.isEmptyAt(position);
	}
	
	protected Iterable<Position> getReachableConsequtivePositions(
			Position currentPosition, 
			Board board, 
			OffsetPair[] offsetPairs)
	{
		ArrayList<Position> positions = new ArrayList<>();
		
		for (OffsetPair offsetPair : offsetPairs)
		{
			Position nextPosition = currentPosition.moveBy(offsetPair);
			
			while (true)
			{
				if (canMoveFreelyTo(nextPosition, board))
				{
					positions.add(nextPosition);
				}
				else
				{
					if (canCaptureAt(nextPosition, board))
						positions.add(nextPosition);
					
					break;
				}
				
				nextPosition = nextPosition.moveBy(offsetPair);
			}
		}
		
		return positions;
	}
	
	protected Iterable<Position> getReachableSinglePositions(
			Position currentPosition, 
			Board board, 
			OffsetPair[] offsetPairs)
	{
		ArrayList<Position> positions = new ArrayList<>();
		
		for (OffsetPair offsetPair : offsetPairs)
		{
			Position nextPosition = currentPosition.moveBy(offsetPair);
			
			boolean isPositionValid = canMoveFreelyTo(nextPosition, board) || 
					canCaptureAt(nextPosition, board);
		
			if (isPositionValid)
				positions.add(nextPosition);
		}
		
		return positions;
	}
	
	public abstract Iterable<Position> getReachablePositions(Position currentPosition, Board board);
}
