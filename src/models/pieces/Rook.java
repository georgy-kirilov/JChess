package models.pieces;

import java.util.ArrayList;

import common.Position;
import enums.PieceType;
import enums.PieceColor;
import models.boards.Board;
import java.util.function.Function;

public class Rook extends BasePiece
{	
	public Rook(PieceColor color) 
	{
		super(PieceType.ROOK, color);
	}
	
	@Override
	public ArrayList<Position> getAllReachablePositions(Position currentPosition, Board board) 
	{
		ArrayList<Position> reachablePositions = new ArrayList<>();
		
		reachablePositions.addAll(this.reachablePositionsInDirection
				(Position::moveUp, currentPosition, board));
		
		reachablePositions.addAll(this.reachablePositionsInDirection
				(Position::moveDown, currentPosition, board));

		reachablePositions.addAll(this.reachablePositionsInDirection
				(Position::moveLeft, currentPosition, board));
	
		reachablePositions.addAll(this.reachablePositionsInDirection
				(Position::moveRight, currentPosition, board));
		
		return reachablePositions;
	}

	private ArrayList<Position> reachablePositionsInDirection(Function<Position, Position> getNextPosition, 
			Position current, Board board)
	{
		ArrayList<Position> positions = new ArrayList<>();
		Position next = getNextPosition.apply(current);
		
		while (board.isPositionInside(next))
		{
			if (board.isEmptyAt(next))
			{
				positions.add(next);
			}
			else
			{
				if (board.getAt(next).getColor() != this.getColor())
				{
					positions.add(next);
				}
				
				break;
			}
			
			next = getNextPosition.apply(next);
		}
		
		return positions;
	}
}
