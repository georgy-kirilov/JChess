package models.pieces;

import java.util.ArrayList;

import common.Position;
import enums.PieceColor;
import enums.PieceType;
import models.boards.Board;

public class King extends BasePiece
{
	public King(PieceColor color)
	{
		super(PieceType.KING, color);
	}

	@Override
	public ArrayList<Position> getAllReachablePositions(Position currentPosition, Board board)
	{
		ArrayList<Position> reachablePositions = new ArrayList<>();
		
		Position[] possiblePositions = new Position[]
		{
			new Position(currentPosition.getRow() + 1, currentPosition.getColumn()),
			new Position(currentPosition.getRow() + 1, currentPosition.getColumn() + 1),
			new Position(currentPosition.getRow(), currentPosition.getColumn() + 1),
			new Position(currentPosition.getRow() - 1, currentPosition.getColumn() + 1),
			new Position(currentPosition.getRow() - 1, currentPosition.getColumn()),
			new Position(currentPosition.getRow() - 1, currentPosition.getColumn() - 1),
			new Position(currentPosition.getRow(), currentPosition.getColumn() - 1),
			new Position(currentPosition.getRow() - 1, currentPosition.getColumn() + 1),
		};
		
		for (Position position : possiblePositions)
		{
			boolean shouldBeAdded = 
					board.isPositionInside(position) &&
					(board.isEmptyAt(position) || 
					board.getAt(position).getColor() != this.getColor());
			
			if (shouldBeAdded)
			{
				reachablePositions.add(position);
			}
		}

		return reachablePositions;
	}
}
