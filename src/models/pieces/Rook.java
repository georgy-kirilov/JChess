package models.pieces;

import java.util.ArrayList;

import common.Position;
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
		
		//UP
		allPossiblePositions.addAll(
				this.getReachableConsequtivePositions(currentPosition, board, -1, 0));
		
		//RIGHT
		allPossiblePositions.addAll(
				this.getReachableConsequtivePositions(currentPosition, board, 0, 1));
		
		//DOWN
		allPossiblePositions.addAll(
				this.getReachableConsequtivePositions(currentPosition, board, 1, 0));
		
		//LEFT
		allPossiblePositions.addAll(
				this.getReachableConsequtivePositions(currentPosition, board, 0, -1));
		
		return allPossiblePositions;
	}
}

