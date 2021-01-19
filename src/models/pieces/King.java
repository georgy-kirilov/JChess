package models.pieces;

import common.Position;
import enums.PieceColor;
import models.boards.Board;

import common.MovementOffsetPair;

public class King extends BasePiece
{
	public King(PieceColor color)
	{
		super(color);
	}

	@Override
	public Iterable<Position> getAllReachablePositions(Position currentPosition, Board board)
	{
		MovementOffsetPair[] offsetPairs = new MovementOffsetPair[]
		{
			MovementOffsetPair.UP,
			MovementOffsetPair.DOWN,
			MovementOffsetPair.RIGHT,
			MovementOffsetPair.LEFT,
			MovementOffsetPair.TOP_RIGHT,
			MovementOffsetPair.TOP_LEFT,
			MovementOffsetPair.BOTTOM_RIGHT,
			MovementOffsetPair.BOTTOM_LEFT,
		};
		
		return this.getReachableSinglePositions(currentPosition, board, offsetPairs);
	}
	
	public boolean isChecked(Position kingPosition, Board board)
	{
		kingPosition.translate(board);
		board.rotateAnticlockwise(2);
		
		for (int i = 0; i < board.getHeight(); i++)
		{
			for (int j = 0; j < board.getWidth(); j++)
			{
				Piece piece = board.getAt(i, j);
				
				if (!board.isEmptyAt(i, j) && piece.getColor() != this.getColor())
				{
					for (Position position : piece.getAllReachablePositions(new Position(i, j), board))
					{
						if (position.equals(kingPosition))
						{
							kingPosition.translate(board);
							board.rotateAnticlockwise(2);
							return true;
						}
					}
				}
			}
		}
		
		kingPosition.translate(board);
		board.rotateAnticlockwise(2);
		return false;
	}
	
	public boolean isCheckmated(Position kingPosition, Board board)
	{		
		if (!this.isChecked(kingPosition, board))
			return false;
		
		System.out.println("INSIDE");
		
		for (int i = 0; i < board.getHeight(); i++)
		{
			for (int j = 0; j < board.getWidth(); j++)
			{
				if (!board.isEmptyAt(i, j))
				{
					Piece piece = board.getAt(i, j);
					
					if (piece.getColor() == this.getColor())
					{	
						Iterable<Position> positions = 
								piece.getAllReachablePositions(new Position(i, j), board);
						
						Position pos = kingPosition;
						
						for (Position position : positions)
						{
							Piece captured = board.getAt(position);
							board.setAt(position, piece);
							board.setToEmpty(i, j);
							
							if (piece.getClass().equals(King.class))
							{
								pos = position;
							}
							
							if (!this.isChecked(pos, board))
							{
								board.setAt(position, captured);
								board.setAt(i, j, piece);
								return false;
							}
							
							board.setAt(position, captured);
							board.setAt(i, j, piece);
						}
					}
				}
			}
		}
		
		return true;
	}
}
