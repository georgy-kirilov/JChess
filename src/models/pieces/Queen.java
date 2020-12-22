package models.pieces;

import java.util.ArrayList;

import common.MovementOffsetPair;
import common.Position;
import enums.PieceColor;
import models.boards.Board;

public class Queen extends BasePiece
{
	public Queen(PieceColor color)
	{
		super(color);
	}
	
	@Override
	public Iterable<Position> getAllReachablePositions(Position currentPosition, Board board) 
	{
		ArrayList<Position> allPossiblePositions = new ArrayList<>();

		allPossiblePositions.addAll(this.getReachableConsequtivePositions(currentPosition, board, MovementOffsetPair.UP));

		allPossiblePositions.addAll(this.getReachableConsequtivePositions(currentPosition, board, MovementOffsetPair.DOWN));

		allPossiblePositions.addAll(this.getReachableConsequtivePositions(currentPosition, board, MovementOffsetPair.RIGHT));
		
		allPossiblePositions.addAll(this.getReachableConsequtivePositions(currentPosition, board, MovementOffsetPair.LEFT));
			
		allPossiblePositions.addAll(this.getReachableConsequtivePositions(currentPosition, board, MovementOffsetPair.TOP_LEFT));
		
		allPossiblePositions.addAll(this.getReachableConsequtivePositions(currentPosition, board, MovementOffsetPair.BOTTOM_LEFT));
		
		allPossiblePositions.addAll(this.getReachableConsequtivePositions(currentPosition, board, MovementOffsetPair.TOP_RIGHT));
		
		allPossiblePositions.addAll(this.getReachableConsequtivePositions(currentPosition, board, MovementOffsetPair.BOTTOM_RIGHT));
		
		return allPossiblePositions;
	}}
