package models.pieces;

import common.Position;
import enums.PieceColor;
import common.OffsetPair;
import models.boards.Board;

public class King extends BasePiece
{
	public King(PieceColor color)
	{
		super(color);
	}

	@Override
	public Iterable<Position> getReachablePositions(Position currentPosition, Board board)
	{
		OffsetPair[] offsetPairs = new OffsetPair[]
		{
			OffsetPair.UP,
			OffsetPair.DOWN,
			OffsetPair.RIGHT,
			OffsetPair.LEFT,
			OffsetPair.TOP_RIGHT,
			OffsetPair.TOP_LEFT,
			OffsetPair.BOTTOM_RIGHT,
			OffsetPair.BOTTOM_LEFT,
		};
		
		return getReachableSinglePositions(currentPosition, board, offsetPairs);
	}
	
	public boolean isChecked(Position kingPosition, Board board)
	{
		kingPosition.flipOver(board);
		board.rotate();
		
		for (int i = 0; i < board.getHeight(); i++)
		{
			for (int j = 0; j < board.getWidth(); j++)
			{
				Piece piece = board.getAt(i, j);
				
				if (!board.isEmptyAt(i, j) && piece.getColor() != getColor())
				{
					for (Position position : piece.getReachablePositions(new Position(i, j), board))
					{
						if (position.equals(kingPosition))
						{
							kingPosition.flipOver(board);
							board.rotate();
							
							return true;
						}
					}
				}
			}
		}
		
		kingPosition.flipOver(board);
		board.rotate();
		
		return false;
	}
	
	public boolean isCheckmated(Position kingPosition, Board board)
	{		
		if (!isChecked(kingPosition, board))
			return false;
		
		for (int i = 0; i < board.getHeight(); i++)
		{
			for (int j = 0; j < board.getWidth(); j++)
			{
				Piece piece = board.getAt(i, j);
				
				if (!board.isEmptyAt(i, j) && piece.getColor() == getColor())
				{
					Iterable<Position> positions = piece
							.getReachablePositions(new Position(i, j), board);
					
					Position checkPosition = kingPosition;
					
					for (Position position : positions)
					{
						Piece captured = board.getAt(position);
						board.setAt(position, piece);
						board.setToEmpty(i, j);
						
						if (piece.getClass().equals(King.class))
							checkPosition = position;
		
						boolean checked = isChecked(checkPosition, board);
						
						board.setAt(position, captured);
						board.setAt(i, j, piece);
						
						if (!checked) return false;
					}
				}
			}
		}
		
		return true;
	}
	
	public Iterable<Position> getCastlePositions(Position kingPosition, Board board)
	{
		//TODO: Implement getCastlePositions method
		throw new UnsupportedOperationException();
	}
}
