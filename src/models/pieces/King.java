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
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Position> getAllReachablePositions(Position currentPosition, Board board)
	{
		
		ArrayList<Position> availablePositionsToMoveList = new ArrayList<Position>();
		
		ArrayList<Position> AllKingProbablePositions = new ArrayList<Position>();
		
		// Adding all 8 of the Kings sides as positions to a List
		Position kingProbablePositionUp;
		kingProbablePositionUp = currentPosition.moveUp(currentPosition);
		AllKingProbablePositions.add(kingProbablePositionUp);
		
		Position kingProbablePositionRightUp;
		kingProbablePositionRightUp = currentPosition.moveTopRight(currentPosition);
		AllKingProbablePositions.add(kingProbablePositionRightUp);
		
		Position kingProbablePositionRight;
		kingProbablePositionRight = currentPosition.moveBottomRight(currentPosition);
		AllKingProbablePositions.add(kingProbablePositionRight);
		
		Position kingProbablePositionRightDown;
		kingProbablePositionRightDown = currentPosition.moveBottomRight(currentPosition);
		AllKingProbablePositions.add(kingProbablePositionRightDown);
		
		Position kingProbablePositionDown;
		kingProbablePositionDown = currentPosition.moveDown(currentPosition);
		AllKingProbablePositions.add(kingProbablePositionDown);
		
		Position kingProbablePositionLeftDown;
		kingProbablePositionLeftDown = currentPosition.moveBottomLeft(currentPosition);
		AllKingProbablePositions.add(kingProbablePositionLeftDown);
		
		Position kingProbablePositionLeft;
		kingProbablePositionLeft = currentPosition.moveLeft(currentPosition);
		AllKingProbablePositions.add(kingProbablePositionLeft);
		
		Position kingProbablePositionLeftUp;
		kingProbablePositionLeftUp = currentPosition.moveTopLeft(currentPosition);
		AllKingProbablePositions.add(kingProbablePositionLeftUp);
		
		//Checking all sides of the King if they are empty or taken by an opponent Figure
		for (int i = 0; i < AllKingProbablePositions.size(); i++) 
		{
			if (board.isEmptyAt(AllKingProbablePositions.get(i)) || board.getAt(AllKingProbablePositions.get(i)).getColor()!=this.getColor())
			{
				availablePositionsToMoveList.add(AllKingProbablePositions.get(i));
			}

}
		//returning all Available Positions to move
		return availablePositionsToMoveList;
	}
}
