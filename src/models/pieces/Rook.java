package models.pieces;

import java.util.ArrayList;

import common.*;
import enums.PieceColor;
import models.boards.Board;

public class Rook extends BasePiece
{
	public Rook(PieceColor color)
	{
		super(color);
	}
	
	@Override
	public Iterable<Position> getAllReachablePositions(Position currentPosition, Board board) 
	{
		ArrayList<Position> allPossiblePositions = new ArrayList<>();
		
		allPossiblePositions.addAll(
				this.getReachableConsequtivePositions(currentPosition, board, MovementOffsetPair.UP));

		allPossiblePositions.addAll(
				this.getReachableConsequtivePositions(currentPosition, board, MovementOffsetPair.RIGHT));
		
		allPossiblePositions.addAll(
				this.getReachableConsequtivePositions(currentPosition, board, MovementOffsetPair.DOWN));
		
		allPossiblePositions.addAll(
				this.getReachableConsequtivePositions(currentPosition, board, MovementOffsetPair.LEFT));
		
		return allPossiblePositions;
	}
}

