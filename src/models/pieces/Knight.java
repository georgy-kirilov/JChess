package models.pieces;

import common.Position;
import enums.PieceColor;
import common.OffsetPair;
import models.boards.Board;

public class Knight extends BasePiece
{	
	public Knight(PieceColor colorOfFigure)
	{
		super(colorOfFigure);
	}

	@Override
	public Iterable<Position> getReachablePositions(Position currentPosition, Board board)
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
		
		return this.getReachableSinglePositions(currentPosition, board, offsetPairs);
	}
}
