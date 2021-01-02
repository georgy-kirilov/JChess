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
		
		Position pawnProbablePositionUp = currentPosition.move(MovementOffsetPair.UP);
		
		if (this.canMoveFreelyTo(pawnProbablePositionUp, board))
		{
			reachablePositions.add(pawnProbablePositionUp);			
		}

		if (!this.isMoved())
		{
			Position pawnProbablePositionTwiceUp = currentPosition.move(MovementOffsetPair.TWICE_UP);
			
			if (this.canMoveFreelyTo(pawnProbablePositionTwiceUp, board))
			{
				reachablePositions.add(pawnProbablePositionTwiceUp);		
			}
		}

		Position pawnProbablePositionTopRight = currentPosition.move(MovementOffsetPair.TOP_RIGHT);
		
		if (this.canCaptureAt(pawnProbablePositionTopRight, board)) 
		{	
			reachablePositions.add(pawnProbablePositionTopRight);
		}

		Position pawnProbablePositionTopLeft = currentPosition.move(MovementOffsetPair.TOP_LEFT);
		
		if (this.canCaptureAt(pawnProbablePositionTopLeft, board)) 
		{	
			reachablePositions.add(pawnProbablePositionTopLeft);
		}
		
		return reachablePositions;
	}
}
