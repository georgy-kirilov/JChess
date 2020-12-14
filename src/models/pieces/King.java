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
		// TODO Auto-generated method stub
		ArrayList<Position> availablePositionsToMoveList = new ArrayList<Position>();
		Position kingProbablePositionUp = new Position(currentPosition.getRow()+1, currentPosition.getColumn());
		Position kingProbablePositionRightUp = new Position(currentPosition.getRow()+1, currentPosition.getColumn()+1);
		Position kingProbablePositionRight = new Position(currentPosition.getRow(), currentPosition.getColumn()+1);
		Position kingProbablePositionRightDown = new Position(currentPosition.getRow()-1, currentPosition.getColumn()+1);
		Position kingProbablePositionDown = new Position(currentPosition.getRow()-1, currentPosition.getColumn());
		Position kingProbablePositionLeftDown = new Position(currentPosition.getRow()-1, currentPosition.getColumn()-1);
		Position kingProbablePositionLeft = new Position(currentPosition.getRow(), currentPosition.getColumn()-1);
		Position kingProbablePositionLeftUp = new Position(currentPosition.getRow()-1, currentPosition.getColumn()+1);

		if (board.isEmptyAt(kingProbablePositionUp) || 	board.getAt(kingProbablePositionUp).getColor()!=this.getColor())
		{
			availablePositionsToMoveList.add(kingProbablePositionUp);
		}
		
		if (board.isEmptyAt(kingProbablePositionRightUp) || board.getAt(kingProbablePositionRightUp).getColor()!=this.getColor())
		{
			availablePositionsToMoveList.add(kingProbablePositionRightUp);
		}
		
		if (board.isEmptyAt(kingProbablePositionRight) || board.getAt(kingProbablePositionRight).getColor()!=this.getColor())
		{
			availablePositionsToMoveList.add(kingProbablePositionRight);
		}
		if (board.isEmptyAt(kingProbablePositionRightDown) || board.getAt(kingProbablePositionRightDown).getColor()!=this.getColor())
		{
			availablePositionsToMoveList.add(kingProbablePositionRightDown);
		}
		if (board.isEmptyAt(kingProbablePositionDown) || board.getAt(kingProbablePositionDown).getColor()!=this.getColor())
		{
			availablePositionsToMoveList.add(kingProbablePositionDown);
		}
		if (board.isEmptyAt(kingProbablePositionLeftDown) || board.getAt(kingProbablePositionLeftDown).getColor()!=this.getColor())
		{
			availablePositionsToMoveList.add(kingProbablePositionLeftDown);
		}
		if (board.isEmptyAt(kingProbablePositionLeft) || board.getAt(kingProbablePositionLeft).getColor()!=this.getColor())
		{
			availablePositionsToMoveList.add(kingProbablePositionLeft);
		}
		if (board.isEmptyAt(kingProbablePositionLeftUp) || board.getAt(kingProbablePositionLeftUp).getColor()!=this.getColor())
		{
			availablePositionsToMoveList.add(kingProbablePositionLeftUp);
		}
		
		
		return availablePositionsToMoveList;
	}
	//TODO: Implement the King class
}
