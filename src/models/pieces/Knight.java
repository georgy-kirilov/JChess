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
			MovementOffsetPair.Knight_2Up_1Left,
			MovementOffsetPair.Knight_2Up_1Right,
			MovementOffsetPair.Knight_1Up_2Left,
			MovementOffsetPair.Knight_1Up_2Right,
			MovementOffsetPair.Knight_2Down_1Left,
			MovementOffsetPair.Knight_2Down_1Right,
			MovementOffsetPair.Knight_1Down_2Left,
			MovementOffsetPair.Knight_1Down_2Right,
		};
		
		return this.getReachableSinglePositions(currentPosition, board, offsetPairs);
	}
}
