package models.pieces;

import java.util.ArrayList;

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
		ArrayList<Position> availablePositionsToMoveList = new ArrayList<>();
		ArrayList<Position> allKingProbablePositions = new ArrayList<>();
		
		// Adding all 8 of the Kings sides as positions to a List
		Position kingProbablePositionUp = Position.moveUp(currentPosition);
		allKingProbablePositions.add(kingProbablePositionUp);
		
		Position kingProbablePositionRightUp = Position.moveTopRight(currentPosition);
		allKingProbablePositions.add(kingProbablePositionRightUp);
		
		Position kingProbablePositionRight = Position.moveRight(currentPosition);
		allKingProbablePositions.add(kingProbablePositionRight);
		
		Position kingProbablePositionRightDown = Position.moveBottomRight(currentPosition);
		allKingProbablePositions.add(kingProbablePositionRightDown);
		
		Position kingProbablePositionDown = Position.moveDown(currentPosition);
		allKingProbablePositions.add(kingProbablePositionDown);
		
		Position kingProbablePositionLeftDown = Position.moveBottomLeft(currentPosition);
		allKingProbablePositions.add(kingProbablePositionLeftDown);
		
		Position kingProbablePositionLeft = Position.moveLeft(currentPosition);
		allKingProbablePositions.add(kingProbablePositionLeft);
		
		Position kingProbablePositionLeftUp = Position.moveTopLeft(currentPosition);
		allKingProbablePositions.add(kingProbablePositionLeftUp);
		
		//Checking all sides of the King if they are empty or taken by an opponent Figure
		for (Position position : allKingProbablePositions)
		{
			if (board.isEmptyAt(position) || board.getAt(position).getColor() != this.getColor())
			{
				availablePositionsToMoveList.add(position);
			}
		}
		
		//Returning all Available Positions to move
		return availablePositionsToMoveList;
	}
	
	@Override
	public String toString()
	{
		return "K";
	}
}
