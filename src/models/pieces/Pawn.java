package models.pieces;

import java.util.ArrayList;

import common.Position;
import enums.PieceColor;
import models.boards.Board;
import common.MovementOffsetPair;

public class Pawn extends BasePiece
{
	public Pawn(PieceColor color)
	{
		super(color);
	}

	@Override
	public Iterable<Position> getAllReachablePositions(Position currentPosition, Board board)
	{	
		ArrayList<Position> reachablePositions = new ArrayList<>();
		
		Position up = currentPosition.move(MovementOffsetPair.UP);
		
		if (this.canMoveFreelyTo(up, board))
		{
			reachablePositions.add(up);
			
			if (!this.isMoved())
			{
				Position twiceUp = currentPosition.move(MovementOffsetPair.TWICE_UP);
				
				if (this.canMoveFreelyTo(twiceUp, board))
				{
					reachablePositions.add(twiceUp);		
				}
			}
		}

		Position topRight = currentPosition.move(MovementOffsetPair.TOP_RIGHT);
		
		if (this.canCaptureAt(topRight, board)) 
		{	
			reachablePositions.add(topRight);
		}

		Position topLeft = currentPosition.move(MovementOffsetPair.TOP_LEFT);
		
		if (this.canCaptureAt(topLeft, board)) 
		{	
			reachablePositions.add(topLeft);
		}
		
		return reachablePositions;
	}
}
