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
		Position kingProbablePositionUp = new Position(currentPosition.getRow()+1, currentPosition.getColumn());
		AllKingProbablePositions.add(kingProbablePositionUp);
		
		Position kingProbablePositionRightUp = new Position(currentPosition.getRow()+1, currentPosition.getColumn()+1);
		AllKingProbablePositions.add(kingProbablePositionRightUp);
		
		Position kingProbablePositionRight = new Position(currentPosition.getRow(), currentPosition.getColumn()+1);
		AllKingProbablePositions.add(kingProbablePositionRight);
		
		Position kingProbablePositionRightDown = new Position(currentPosition.getRow()-1, currentPosition.getColumn()+1);
		AllKingProbablePositions.add(kingProbablePositionRightDown);
		
		Position kingProbablePositionDown = new Position(currentPosition.getRow()-1, currentPosition.getColumn());
		AllKingProbablePositions.add(kingProbablePositionDown);
		
		Position kingProbablePositionLeftDown = new Position(currentPosition.getRow()-1, currentPosition.getColumn()-1);
		AllKingProbablePositions.add(kingProbablePositionLeftDown);
		
		Position kingProbablePositionLeft = new Position(currentPosition.getRow(), currentPosition.getColumn()-1);
		AllKingProbablePositions.add(kingProbablePositionLeft);
		
		Position kingProbablePositionLeftUp = new Position(currentPosition.getRow()-1, currentPosition.getColumn()+1);
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
