package models.pieces;

import java.util.ArrayList;

import common.Position;
import enums.PieceColor;
import models.boards.Board;

public class Pawn extends BasePiece {

	public Pawn(PieceColor color) {
		super(color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Position> getAllReachablePositions(Position currentPosition, Board board) {
		ArrayList<Position> availableTakingPositionsToMoveList = new ArrayList<>();
		ArrayList<Position> availableMovingPositionsToMoveList = new ArrayList<>();
		ArrayList<Position> availablePositionsToMoveList = new ArrayList<>();

		if (this.getColor() == PieceColor.WHITE) 
		{
			Position pawnProbablePositionUp = Position.moveUp(currentPosition);
			availableMovingPositionsToMoveList.add(pawnProbablePositionUp);

			if (currentPosition.getRow() == 1) 
			{
				Position pawnProbablePositionDoubleUp = Position.moveUp(currentPosition);
				pawnProbablePositionDoubleUp = Position.moveUp(pawnProbablePositionDoubleUp);
				availableMovingPositionsToMoveList.add(pawnProbablePositionDoubleUp);
			}

			Position topRight = new Position(currentPosition.getRow() + 1, currentPosition.getColumn() + 1);
			if (!board.isEmptyAt(topRight)&&board.getAt(topRight).getColor()!=this.getColor()) 
			{
				Position pawnProbablePositionUpRight = Position.moveTopRight(currentPosition);
				availableTakingPositionsToMoveList.add(pawnProbablePositionUpRight);
			}

			Position topLeft = new Position(currentPosition.getRow() + 1, currentPosition.getColumn() - 1);
			if (!board.isEmptyAt(topLeft)&&board.getAt(topLeft).getColor()!=this.getColor()) 
			{
				Position pawnProbablePositionUpLeft = Position.moveTopLeft(currentPosition);
				availableTakingPositionsToMoveList.add(pawnProbablePositionUpLeft);
			}

		}
		
		else if (this.getColor() == PieceColor.BLACK) 
		{
			Position pawnProbablePositionDown = Position.moveDown(currentPosition);
			availableMovingPositionsToMoveList.add(pawnProbablePositionDown);

			if (currentPosition.getRow() == 6) 
			{
				Position pawnProbablePositionDoubleDown = Position.moveDown(currentPosition);
				pawnProbablePositionDoubleDown = Position.moveDown(pawnProbablePositionDoubleDown);
				availableMovingPositionsToMoveList.add(pawnProbablePositionDoubleDown);
			}
			
			Position bottomRight= new Position(currentPosition.getRow() - 1, currentPosition.getColumn() + 1);
			if (!board.isEmptyAt(bottomRight)&&board.getAt(bottomRight).getColor()!=this.getColor()) 
			{
				Position pawnProbablePositionDownRight = Position.moveBottomRight(currentPosition);
				availableTakingPositionsToMoveList.add(pawnProbablePositionDownRight);
			}

			Position bottomLeft = new Position(currentPosition.getRow() - 1, currentPosition.getColumn() - 1);
			if (!board.isEmptyAt(bottomLeft)&&board.getAt(bottomLeft).getColor()!=this.getColor()) 
			{
				Position pawnProbablePositionDownLeft = Position.moveBottomLeft(currentPosition);
				availableTakingPositionsToMoveList.add(pawnProbablePositionDownLeft);
			}
		}
		
		for (Position position : availableTakingPositionsToMoveList) 
		{
			if (board.isEmptyAt(position) && board.getAt(position).getColor() != this.getColor()) 
			{
				availablePositionsToMoveList.add(position);				}
			}
		for (Position position : availableMovingPositionsToMoveList) 
		{
			if (board.isEmptyAt(position)) 
			{
				availablePositionsToMoveList.add(position);
			}
		}
			return availablePositionsToMoveList;

		
	}
	// TODO: Implement the Pawn class
}
