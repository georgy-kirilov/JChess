package models.pieces;

import java.util.ArrayList;

import common.Position;
import enums.PieceColor;
import models.boards.Board;
import common.MovementOffsetPair;

public class Bishop extends BasePiece
{	
	public Bishop(PieceColor color)
	{
		super(color);
	}

	@Override
	public Iterable<Position> getAllReachablePositions(Position currentPosition, Board board) 
	{		
		ArrayList<Position> reachablePositions = new ArrayList<>();
		
		reachablePositions.addAll(this.getReachableConsequtivePositions
				(currentPosition, board, MovementOffsetPair.TOP_RIGHT));
		
		reachablePositions.addAll(this.getReachableConsequtivePositions
				(currentPosition, board, MovementOffsetPair.TOP_LEFT));
		
		reachablePositions.addAll(this.getReachableConsequtivePositions
				(currentPosition, board, MovementOffsetPair.BOTTOM_RIGHT));
		
		reachablePositions.addAll(this.getReachableConsequtivePositions
				(currentPosition, board, MovementOffsetPair.BOTTOM_LEFT));
		
		return reachablePositions;
	}
}
