package models.pieces;

import common.Position;
import enums.PieceColor;
import models.boards.Board;
import common.MovementOffsetPair;

public class King extends BasePiece
{
	public King(PieceColor color)
	{
		super(color);
	}

	@Override
	public Iterable<Position> getAllReachablePositions(Position currentPosition, Board board)
	{
		MovementOffsetPair[] offsetPairs = new MovementOffsetPair[]
		{
			MovementOffsetPair.UP,
			MovementOffsetPair.DOWN,
			MovementOffsetPair.RIGHT,
			MovementOffsetPair.LEFT,
			MovementOffsetPair.TOP_RIGHT,
			MovementOffsetPair.TOP_LEFT,
			MovementOffsetPair.BOTTOM_RIGHT,
			MovementOffsetPair.BOTTOM_LEFT,
		};
		
		return this.getReachableSinglePositions(currentPosition, board, offsetPairs);
	}
}
