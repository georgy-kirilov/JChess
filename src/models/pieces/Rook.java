package models.pieces;

import java.util.Collection;

import common.Position;
import common.OffsetPair;

import enums.PieceColor;
import models.boards.Board;

public class Rook extends BasePiece
{
	public Rook(PieceColor color)
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
		};
		
		return getReachableConsequtivePositions(currentPosition, board, offsetPairs);
	}
}

