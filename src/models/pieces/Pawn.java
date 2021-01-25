package models.pieces;

import java.util.ArrayList;
import java.util.Collection;

import common.Position;
import common.OffsetPair;

import enums.PieceColor;
import models.boards.Board;

public class Pawn extends BasePiece
{
	public Pawn(PieceColor color)
	{
		super(color);
	}

	@Override
	public Collection<Position> getReachablePositions(Position currentPosition, Board board)
	{	
		ArrayList<Position> positions = new ArrayList<>();
		
		Position up = currentPosition.moveBy(OffsetPair.UP);
		
		if (canMoveFreelyTo(up, board))
		{
			positions.add(up);
			
			if (!isMoved())
			{
				Position twiceUp = currentPosition.moveBy(new OffsetPair(-2, 0));
				
				if (canMoveFreelyTo(twiceUp, board))
				{
					positions.add(twiceUp);					
				}
			}
		}

		Position topRight = currentPosition.moveBy(OffsetPair.TOP_RIGHT);
		
		if (canCaptureAt(topRight, board))
		{
			positions.add(topRight);			
		}

		Position topLeft = currentPosition.moveBy(OffsetPair.TOP_LEFT);
		
		if (canCaptureAt(topLeft, board))
		{
			positions.add(topLeft);			
		}
		
		return positions;
	}
	
	public boolean canBePromoted(Position pawnPosition)
	{
		return pawnPosition.getRow() == 0;
	}
}
