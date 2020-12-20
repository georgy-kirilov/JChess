package models.pieces;

import java.util.ArrayList;

import common.Position;
import enums.PieceColor;
import enums.PieceType;
import jthrow.JThrower;
import models.boards.Board;

public class Rook extends BasePiece //implements Board
{
	public Rook(PieceColor color) 
	{
		super(PieceType.ROOK, color);
	}

	
	@Override
	public ArrayList<Position> getAllReachablePositions(Position currentPosition, Board board) 
	{
		ArrayList<Position> allPossiblePositions = new ArrayList<Position>();
		
		allPossiblePositions.addAll(getUpPositions(currentPosition, board));
		allPossiblePositions.addAll(getDownPositions(currentPosition, board));
		allPossiblePositions.addAll(getRightPositions(currentPosition, board));
		allPossiblePositions.addAll(getLeftPositions(currentPosition, board));
		
		return allPossiblePositions;
	}

	private ArrayList<Position> getUpPositions(Position currentPosition, Board board)
	{
		ArrayList<Position> upPositions = new ArrayList<Position>();

		for( int rowMove = currentPosition.getRow() ; rowMove < board.getHeight(); rowMove++ )
		{
			Position newUpPosition = new Position(rowMove + 1, currentPosition.getColumn());
			if(board.isPositionInside(newUpPosition))
			{
				if( board.isEmptyAt(newUpPosition) )
				{
					upPositions.add(new Position(rowMove + 1, currentPosition.getColumn()));
				}
				
				else if( board.getAt(newUpPosition).getColor() != this.getColor() )
				{
					upPositions.add(new Position(rowMove + 1, currentPosition.getColumn()));
					break;
				}
			}
		}
		return upPositions;
	}

	private ArrayList<Position> getDownPositions(Position currentPosition, Board board)
	{
		ArrayList<Position> downPositions = new ArrayList<Position>();
		
		for( int rowMove = currentPosition.getRow() ; rowMove >= 0; rowMove-- )
		{
			Position newDownPosition = new Position(rowMove - 1, currentPosition.getColumn());
			if(board.isPositionInside(newDownPosition))
			{
				if( board.isEmptyAt(newDownPosition) )
				{
					downPositions.add(new Position(rowMove - 1, currentPosition.getColumn()));
				}
				
				else if( board.getAt(newDownPosition).getColor() != this.getColor() )
				{
					downPositions.add(new Position(rowMove - 1, currentPosition.getColumn()));
					break;
				}
			}
		}
		return downPositions;
	}
	
	private ArrayList<Position> getRightPositions(Position currentPosition, Board board)
	{
		ArrayList<Position> rightPositions = new ArrayList<Position>();
		
		for( int columnMove = currentPosition.getColumn() ; columnMove < board.getWidth(); columnMove ++ )
		{
			Position newRightPosition = new Position(columnMove + 1, currentPosition.getRow());
			
			if(board.isPositionInside(newRightPosition))
			{
				if( board.isEmptyAt(newRightPosition) )
				{
					rightPositions.add(new Position(columnMove + 1, currentPosition.getRow()));
				}
				
				else if( board.getAt(newRightPosition).getColor() != this.getColor() )
				{
					rightPositions.add(new Position(columnMove + 1, currentPosition.getRow()));
					break;
				}
			}
		}
		return rightPositions;
	}
	
	private ArrayList<Position> getLeftPositions(Position currentPosition, Board board)
	{
		ArrayList<Position> leftPositions = new ArrayList<Position>();
	
		for( int columnMove = currentPosition.getColumn() ; columnMove >= 0; columnMove -- )
		{
			Position newLeftPosition = new Position(columnMove - 1, currentPosition.getRow());
			if(board.isPositionInside(newLeftPosition))
			{
				if( board.isEmptyAt(newLeftPosition) )
				{
					leftPositions.add(new Position(columnMove - 1, currentPosition.getRow()));
				}
				
				else if( board.getAt(newLeftPosition).getColor() != this.getColor() )
				{
					leftPositions.add(new Position(columnMove - 1, currentPosition.getRow()));
					break;
				}
			}
		}
		return leftPositions;
	}
}

