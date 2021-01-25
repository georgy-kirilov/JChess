package models.pieces;

import java.util.Collection;

import common.Position;
import common.OffsetPair;

import enums.PieceColor;
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
