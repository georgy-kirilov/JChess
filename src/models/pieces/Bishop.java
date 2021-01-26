package models.pieces;

import java.util.Collection;

import common.Position;
import common.OffsetPair;

import enums.PieceColor;
import models.boards.Board;

public class Bishop extends BasePiece
{	
	public Bishop(PieceColor color)
	{
		super(color);
	}

	@Override
	public Collection<Position> getReachablePositions(Position bishopPosition, Board board) 
	{		
		OffsetPair[] offsetPairs = new OffsetPair[]
		{
			OffsetPair.TOP_LEFT,
			OffsetPair.TOP_RIGHT,
			OffsetPair.BOTTOM_LEFT,
			OffsetPair.BOTTOM_RIGHT,
		};
				
		return getReachableConsequtivePositions(bishopPosition, board, offsetPairs);
	}
}
