package models.pieces;

import common.Position;
import enums.PieceColor;

import java.util.Collection;

import common.OffsetPair;
import models.boards.Board;

public class Knight extends BasePiece
{	
	public Knight(PieceColor colorOfFigure)
	{
		super(colorOfFigure);
	}

	@Override
	public Collection<Position> getReachablePositions(Position currentPosition, Board board)
	{
		OffsetPair[] offsetPairs = new OffsetPair[]
		{
			new OffsetPair(-2, -1),
			new OffsetPair(-2, 1),
			new OffsetPair(-1, -2),
			new OffsetPair(-1, 2),
			new OffsetPair(2, -1),
			new OffsetPair(2, 1),
			new OffsetPair(1, -2),
			new OffsetPair(1, 2),
		};
		
		return getReachableSinglePositions(currentPosition, board, offsetPairs);
	}
}
