package models.pieces;

import common.Position;
import enums.PieceColor;
import models.boards.Board;
import common.MovementOffsetPair;

public class Knight extends BasePiece
{	
	public Knight(PieceColor colorOfFigure)
	{
		super(colorOfFigure);
	}

	@Override
	public Iterable<Position> getAllReachablePositions(Position currentPosition, Board board)
	{
		MovementOffsetPair[] offsetPairs = new MovementOffsetPair[]
		{
			MovementOffsetPair.TWICE_UP_ONCE_LEFT,
			MovementOffsetPair.TWICE_UP_ONCE_RIGHT,
			MovementOffsetPair.ONCE_UP_TWICE_LEFT,
			MovementOffsetPair.ONCE_UP_TWICE_RIGHT,
			MovementOffsetPair.TWICE_DOWN_ONCE_LEFT,
			MovementOffsetPair.TWICE_DOWN_ONCE_RIGHT,
			MovementOffsetPair.ONCE_DOWN_TWICE_LEFT,
			MovementOffsetPair.ONCE_DOWN_TWICE_RIGHT,
		};
		
		return this.getReachableSinglePositions(currentPosition, board, offsetPairs);
	}
}
