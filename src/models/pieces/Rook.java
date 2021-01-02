package models.pieces;

import java.util.ArrayList;

import common.Position;
import enums.PieceColor;
import models.boards.Board;
import common.MovementOffsetPair;

public class Rook extends BasePiece
{
	public Rook(PieceColor color)
	{
		super(color);
	}
	
	@Override
	public Iterable<Position> getAllReachablePositions(Position currentPosition, Board board) 
	{
		ArrayList<Position> reachablePositions = new ArrayList<>();
		
		reachablePositions.addAll(this.getReachableConsequtivePositions
				(currentPosition, board, MovementOffsetPair.UP));

		reachablePositions.addAll(this.getReachableConsequtivePositions
				(currentPosition, board, MovementOffsetPair.RIGHT));
		
		reachablePositions.addAll(this.getReachableConsequtivePositions
				(currentPosition, board, MovementOffsetPair.DOWN));
		
		reachablePositions.addAll(this.getReachableConsequtivePositions
				(currentPosition, board, MovementOffsetPair.LEFT));
		
		return reachablePositions;
	}
}

