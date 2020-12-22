package models.pieces;

import java.util.ArrayList;

import common.Position;
import enums.PieceColor;
import models.boards.Board;

public class Bishop extends BasePiece
{
	ArrayList<Position> possibleMoves;
	private Position newPosition;
	
	public Bishop(PieceColor color) {
		super(color);
		possibleMoves = new ArrayList<>();
	}

	@Override
	public Iterable<Position> getAllReachablePositions(Position currentPosition, Board board) 
	{		
		for(int i = currentPosition.getRow(); i < board.getWidth(); i++)
		{
			for(int j = currentPosition.getColumn(); j < board.getHeight(); j++)
			{
				newPosition.setRow(i + 1);
				newPosition.setColumn(j + 1);
				
				if(!board.isEmptyAt(newPosition))
				{
					if(board.getAt(newPosition).getColor() != this.getColor())
					{
						possibleMoves.add(newPosition);
					}
				}
				else if(board.isEmptyAt(newPosition))
				{
					possibleMoves.add(newPosition);
				}
			}
		}
		
		for(int i = currentPosition.getRow(); i >= 0; i--)
		{
			for(int j = currentPosition.getColumn(); j >= 0; j--)
			{
				newPosition.setRow(i - 1);
				newPosition.setColumn(j - 1);
				
				if(!board.isEmptyAt(newPosition))
				{
					if(board.getAt(newPosition).getColor() != this.getColor())
					{
						possibleMoves.add(newPosition);
					}
				}
				else if(board.isEmptyAt(newPosition))
				{
					possibleMoves.add(newPosition);
				}
			}
		}
		
		for(int i = currentPosition.getRow(); i < board.getWidth(); i++)
		{
			for(int j = currentPosition.getColumn(); j >= 0; j--)
			{
				newPosition.setRow(i + 1);
				newPosition.setColumn(j - 1);
				
				if(!board.isEmptyAt(newPosition))
				{
					if(board.getAt(newPosition).getColor() != this.getColor())
					{
						possibleMoves.add(newPosition);
					}
				}
				else if(board.isEmptyAt(newPosition))
				{
					possibleMoves.add(newPosition);
				}
			}
		}
		
		for(int i = currentPosition.getRow(); i >= 0; i--)
		{
			for(int j = currentPosition.getColumn(); j < board.getHeight(); j++)
			{
				newPosition.setRow(i - 1);
				newPosition.setColumn(j + 1);
				
				if(!board.isEmptyAt(newPosition))
				{
					if(board.getAt(newPosition).getColor() != this.getColor())
					{
						possibleMoves.add(newPosition);
					}
				}
				else if(board.isEmptyAt(newPosition))
				{
					possibleMoves.add(newPosition);
				}
			}
		}
		return possibleMoves;
	}
}
