package models.pieces;

import java.util.ArrayList;

import common.MovementOffsetPair;
import common.Position;
import enums.PieceColor;
import models.boards.Board;

public class Bishop extends BasePiece
{	
	public Bishop(PieceColor color) {
		super(color);
	}

	@Override
	public Iterable<Position> getAllReachablePositions(Position currentPosition, Board board) 
	{		
		ArrayList<Position> possibleMoves = new ArrayList<>();
		
		possibleMoves.addAll(
				this.getReachableConsequtivePositions(currentPosition, board, MovementOffsetPair.TOP_RIGHT));
		
		possibleMoves.addAll(
				this.getReachableConsequtivePositions(currentPosition, board, MovementOffsetPair.TOP_LEFT));
		
		possibleMoves.addAll(
				this.getReachableConsequtivePositions(currentPosition, board, MovementOffsetPair.BOTTOM_RIGHT));
		
		possibleMoves.addAll(
				this.getReachableConsequtivePositions(currentPosition, board, MovementOffsetPair.BOTTOM_LEFT));
		
		return possibleMoves;
	}
}
