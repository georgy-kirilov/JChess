package models.pieces;

import java.util.ArrayList;

import common.Position;
import enums.PieceColor;
import common.OffsetPair;
import models.boards.Board;

public class Pawn extends BasePiece
{
	public Pawn(PieceColor color)
	{
		super(color);
	}

	@Override
	public Iterable<Position> getReachablePositions(Position currentPosition, Board board)
	{	
		ArrayList<Position> positions = new ArrayList<>();
		
		Position up = currentPosition.move(OffsetPair.UP);
		
		if (canMoveFreelyTo(up, board))
		{
			positions.add(up);
			
			if (!isMoved())
			{
				Position twiceUp = currentPosition.move(new OffsetPair(-2, 0));
				
				if (canMoveFreelyTo(twiceUp, board))
					positions.add(twiceUp);
			}
		}

		Position topRight = currentPosition.move(OffsetPair.TOP_RIGHT);
		
		if (canCaptureAt(topRight, board)) 
			positions.add(topRight);

		Position topLeft = currentPosition.move(OffsetPair.TOP_LEFT);
		
		if (canCaptureAt(topLeft, board)) 
			positions.add(topLeft);
		
		return positions;
	}
	
	public boolean canBePromoted(Position pawnPosition)
	{
		return pawnPosition.getRow() == 0;
	}
}
