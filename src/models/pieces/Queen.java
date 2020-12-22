package models.pieces;

import java.util.ArrayList;

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
		allPossiblePositions.addAll(this.getReachableConsequtivePositions(currentPosition, board, -1, 0));
		//DOWN
		allPossiblePositions.addAll(this.getReachableConsequtivePositions(currentPosition, board, 1, 0));
		//RIGHT
		allPossiblePositions.addAll(this.getReachableConsequtivePositions(currentPosition, board, 0, 1));
		//LEFT
		allPossiblePositions.addAll(this.getReachableConsequtivePositions(currentPosition, board, 0, -1));
		//LEFT DIAGONAL (UP)	
		allPossiblePositions.addAll(this.getReachableConsequtivePositions(currentPosition, board, -1, -1));
		//LEFT DIAGONAL (DOWN)
		allPossiblePositions.addAll(this.getReachableConsequtivePositions(currentPosition, board, 1, -1));
		//RIGHT DIAGONAL (UP)
		allPossiblePositions.addAll(this.getReachableConsequtivePositions(currentPosition, board, -1, 1));
		//RIGHT DIAGONAL (DOWN)
		allPossiblePositions.addAll(this.getReachableConsequtivePositions(currentPosition, board, 1, 1));
		return allPossiblePositions;
		
	}}
