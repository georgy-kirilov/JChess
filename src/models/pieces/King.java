package models.pieces;

import common.MovementOffsetPair;
import common.Position;
import enums.PieceColor;
import models.boards.Board;

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
			new MovementOffsetPair(-1, 0),
			new MovementOffsetPair(0, 1),
			new MovementOffsetPair(1, 0),
			new MovementOffsetPair(0, -1),
			new MovementOffsetPair(-1, -1),
			new MovementOffsetPair(-1, 1),
			new MovementOffsetPair(1, 1),
			new MovementOffsetPair(1, -1),
		};
		
		return this.getReachableSinglePositions(currentPosition, board, offsetPairs);
	}
}
