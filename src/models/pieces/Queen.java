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
		
		//UP
		allPossiblePositions.addAll(this.getReachableConsequtivePositions(currentPosition, board, MovementOffsetPair.UP));
		
		//DOWN
		allPossiblePositions.addAll(this.getReachableConsequtivePositions(currentPosition, board, MovementOffsetPair.DOWN));
		
		//RIGHT
		allPossiblePositions.addAll(this.getReachableConsequtivePositions(currentPosition, board, MovementOffsetPair.RIGHT));
		
		//LEFT
		allPossiblePositions.addAll(this.getReachableConsequtivePositions(currentPosition, board, MovementOffsetPair.LEFT));
		
		//LEFT DIAGONAL (UP)	
		allPossiblePositions.addAll(this.getReachableConsequtivePositions(currentPosition, board, MovementOffsetPair.TOP_LEFT));
		
		//LEFT DIAGONAL (DOWN)
		allPossiblePositions.addAll(this.getReachableConsequtivePositions(currentPosition, board, MovementOffsetPair.BOTTOM_LEFT));
		
		//RIGHT DIAGONAL (UP)
		allPossiblePositions.addAll(this.getReachableConsequtivePositions(currentPosition, board, MovementOffsetPair.TOP_RIGHT));
		
		//RIGHT DIAGONAL (DOWN)
		allPossiblePositions.addAll(this.getReachableConsequtivePositions(currentPosition, board, MovementOffsetPair.BOTTOM_RIGHT));
		
		return allPossiblePositions;
	}}
