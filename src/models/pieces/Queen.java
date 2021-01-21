package models.pieces;

import common.Position;
import enums.PieceColor;

import java.util.Collection;

import common.OffsetPair;
import models.boards.Board;

public class Queen extends BasePiece
{
	public Queen(PieceColor color)
	{
		super(color);
	}
	
	@Override
	public Collection<Position> getReachablePositions(Position currentPosition, Board board) 
	{
		OffsetPair[] offsetPairs = new OffsetPair[]
		{
			OffsetPair.UP,
			OffsetPair.DOWN,
			OffsetPair.LEFT,
			OffsetPair.RIGHT,
			OffsetPair.TOP_LEFT,
			OffsetPair.BOTTOM_LEFT,
			OffsetPair.TOP_RIGHT,
			OffsetPair.BOTTOM_RIGHT,
		};
		
		return getReachableConsequtivePositions(currentPosition, board, offsetPairs);
	}
}
