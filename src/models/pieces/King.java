package models.pieces;

import java.util.Collection;

import common.Position;
import common.OffsetPair;

import enums.PieceColor;
import models.boards.Board;

public class King extends BasePiece
{	
	public King(PieceColor color)
	{
		super(color);
	}

	@Override
	public Collection<Position> getReachablePositions(Position kingPosition, Board board)
	{
		OffsetPair[] offsetPairs = new OffsetPair[]
		{
			OffsetPair.UP,
			OffsetPair.DOWN,
			OffsetPair.RIGHT,
			OffsetPair.LEFT,
			OffsetPair.TOP_RIGHT,
			OffsetPair.TOP_LEFT,
			OffsetPair.BOTTOM_RIGHT,
			OffsetPair.BOTTOM_LEFT,
		};
		
		return getReachableSinglePositions(kingPosition, board, offsetPairs);
	}
}
