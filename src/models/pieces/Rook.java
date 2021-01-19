package models.pieces;

import common.Position;
import enums.PieceColor;
import common.OffsetPair;
import models.boards.Board;

public class Rook extends BasePiece
{
	public Rook(PieceColor color)
	{
		super(color);
	}
	
	@Override
	public Iterable<Position> getReachablePositions(Position currentPosition, Board board) 
	{
		OffsetPair[] offsetPairs = new OffsetPair[]
		{
			OffsetPair.UP,
			OffsetPair.DOWN,
			OffsetPair.LEFT,
			OffsetPair.RIGHT,
		};
		
		return getReachableConsequtivePositions(currentPosition, board, offsetPairs);
	}
}

