package models.pieces;

import common.Position;
import enums.PieceColor;

import java.util.Collection;

import common.OffsetPair;
import models.boards.Board;

public class Bishop extends BasePiece
{	
	public Bishop(PieceColor color)
	{
		super(color);
	}

	@Override
	public Collection<Position> getReachablePositions(Position currentPosition, Board board) 
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
