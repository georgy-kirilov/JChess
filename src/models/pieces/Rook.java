package models.pieces;

import java.util.ArrayList;

import common.Position;
import enums.PieceColor;
import enums.PieceType;
import jthrow.JThrower;
import models.boards.Board;

public class Rook extends BasePiece //implements Board
{
	ArrayList<Position> AllPossiblePosition;
	Position newPosition;
	
	public Rook(PieceType type, PieceColor color) 
	{
		super(type, color);
		AllPossiblePosition = new ArrayList<Position>();
	}

	
	@Override
	public ArrayList<Position> getAllReachablePositions(Position currentPosition, Board board) 
	{
		for( int i = currentPosition.getColumn() ; i < board.getHeight(); i++ )
		{
			newPosition.setRow(currentPosition.getRow());
			newPosition.setColumn(i + 1) ;
			if( !board.isEmptyAt(newPosition) )
			{
				if( board.getAt(newPosition).getColor() != this.getColor() )
				{
					AllPossiblePosition.add(newPosition);
				}
			}
			if( board.isEmptyAt(newPosition) )
			{
				AllPossiblePosition.add(newPosition);
			}
		}
		
		
		for( int i = currentPosition.getColumn() ; i >= 0; i-- )
		{
			newPosition.setRow(currentPosition.getRow());
			newPosition.setColumn(i - 1) ;
			if( !board.isEmptyAt(newPosition) )
			{
				if( board.getAt(newPosition).getColor() != this.getColor() )
				{
					AllPossiblePosition.add(newPosition);
				}
			}
			if ( board.isEmptyAt(newPosition) )
			{
				AllPossiblePosition.add(newPosition);
			}
		}
		
		for( int i = currentPosition.getRow() ; i < board.getWidth(); i++ )
		{
			newPosition.setColumn(currentPosition.getColumn());
			newPosition.setRow( i + 1 );
			if( !board.isEmptyAt(newPosition) )
			{
				if(board.getAt(newPosition).getColor() != this.getColor())
				{
					AllPossiblePosition.add(newPosition);
				}
			}
			if ( board.isEmptyAt(newPosition) )
			{
				AllPossiblePosition.add(newPosition);
			}
		}
		
		for( int i = currentPosition.getRow() ; i >= 0; i-- )
		{
			newPosition.setColumn(currentPosition.getColumn());
			newPosition.setRow(i - 1);
			if( !board.isEmptyAt(newPosition) )
			{
				if(board.getAt(newPosition).getColor() != this.getColor())
				{
					AllPossiblePosition.add(newPosition);
				}
			}
			if ( board.isEmptyAt(newPosition) )
			{
				AllPossiblePosition.add(newPosition);
			}
		}
		return AllPossiblePosition;
	}


	
}
