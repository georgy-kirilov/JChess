package models.pieces;

import common.Position;
import enums.PieceColor;
import common.OffsetPair;
import models.boards.Board;

public class Bishop extends BasePiece
{	
	public Bishop(PieceColor color)
	{
		super(color);
	}

	@Override
	public Iterable<Position> getReachablePositions(Position currentPosition, Board board) 
	{		
		OffsetPair[] offsetPairs = new OffsetPair[]
		{
			OffsetPair.TOP_LEFT,
			OffsetPair.TOP_RIGHT,
			OffsetPair.BOTTOM_LEFT,
			OffsetPair.BOTTOM_RIGHT,
		};
				
		return getReachableConsequtivePositions(currentPosition, board, offsetPairs);
	}
}
