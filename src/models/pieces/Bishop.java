package models.pieces;

import java.util.ArrayList;

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
		
		//UP - RIGHT
		possibleMoves.addAll(
				this.getReachableConsequtivePositions(currentPosition, board, -1, 1));
		
		//UP - LEFT
		possibleMoves.addAll(
				this.getReachableConsequtivePositions(currentPosition, board, -1, -1));
		
		//DOWN - RIGHT
		possibleMoves.addAll(
				this.getReachableConsequtivePositions(currentPosition, board, 1, 1));
		
		//DOWN - LEFT
		possibleMoves.addAll(
				this.getReachableConsequtivePositions(currentPosition, board, 1, -1));
		
		return possibleMoves;
	}
}
