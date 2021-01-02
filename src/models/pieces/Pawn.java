package models.pieces;

import java.util.ArrayList;

import common.MovementOffsetPair;
import common.Position;
import enums.PieceColor;
import models.boards.Board;

public class Pawn extends BasePiece {

	public Pawn(PieceColor color) {
		super(color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Iterable<Position> getAllReachablePositions(Position currentPosition, Board board) {
		
		ArrayList<Position> availablePositionsToMoveList = new ArrayList<>();
		
		Position pawnProbablePositionUp = currentPosition.move(MovementOffsetPair.UP);
		if(canMoveFreelyTo(pawnProbablePositionUp,board))	
			availablePositionsToMoveList.add(pawnProbablePositionUp);
			

		if (!isMoved()) 
		{
			Position pawnProbablePositionDoubleUp = currentPosition.move(MovementOffsetPair.DOUBLEUP);
			if(canMoveFreelyTo(pawnProbablePositionDoubleUp,board))
				availablePositionsToMoveList.add(pawnProbablePositionDoubleUp);
		}

		Position pawnProbablePositionTopRight = currentPosition.move(MovementOffsetPair.TOP_RIGHT);
		if (canCaptureAt(pawnProbablePositionTopRight,board)) 
		{
			
			availablePositionsToMoveList.add(pawnProbablePositionTopRight);
		}

		Position pawnProbablePositionTopLeft = currentPosition.move(MovementOffsetPair.TOP_LEFT);
		if (canCaptureAt(pawnProbablePositionTopLeft,board)) 
		{
			
			availablePositionsToMoveList.add(pawnProbablePositionTopLeft);
		}
		
			return availablePositionsToMoveList;

		
	}
	// TODO: Implement the Pawn class
}
