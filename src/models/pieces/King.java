package models.pieces;

import common.Position;
import enums.PieceColor;

import java.util.ArrayList;
import java.util.Collection;

import common.CastlePair;
import common.OffsetPair;
import models.boards.Board;

public class King extends BasePiece
{	
	public King(PieceColor color)
	{
		super(color);
	}

	@Override
	public Collection<Position> getReachablePositions(Position currentPosition, Board board)
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
		Position flippedKingPosition = kingPosition.flipOver(board);
		board.rotate();
		
		for (int i = 0; i < board.getHeight(); i++)
		{
			for (int j = 0; j < board.getWidth(); j++)
			{
				Piece piece = board.getAt(i, j);
				
				if (board.isEmptyAt(i, j) || piece.getColor() == getColor())
					continue;
					
				for (Position position : piece.getReachablePositions(new Position(i, j), board))
				{
					if (position.equals(flippedKingPosition))
					{
						board.rotate();
						return true;
					}
				}
			}
		}
		
		board.rotate();
		return false;
	}
	
	public boolean isCheckmated(Position kingPosition, Board board)
	{		
		if (!isChecked(kingPosition, board))
		{
			return false;			
		}
		
		for (int i = 0; i < board.getHeight(); i++)
		{
			for (int j = 0; j < board.getWidth(); j++)
			{
				Piece piece = board.getAt(i, j);
				
				if (board.isEmptyAt(i, j) || piece.getColor() != getColor())
				{
					continue;					
				}
				
				// Checks whether there's at least one move which doesn't put king in check
				
				Collection<Position> positions = piece.getReachablePositions(new Position(i, j), board);
				
				Position checkPosition = kingPosition;
				
				for (Position position : positions)
				{
					Piece captured = board.getAt(position);
					board.setAt(position, piece);
					board.setToEmpty(i, j);
					
					if (piece.getClass().equals(King.class))
					{
						checkPosition = position;						
					}
	
					boolean checked = isChecked(checkPosition, board);
					
					board.setAt(position, captured);
					board.setAt(i, j, piece);
					
					if (!checked)
					{
						return false;						
					}
				}
			}
		}
		
		return true;
	}
	
	public Collection<CastlePair> getCastlePairs(Position kingPosition, Board board)
	{	
		ArrayList<CastlePair> castlePairs = new ArrayList<>();
		
		if (isMoved())
			return castlePairs;
		
		int row = board.getHeight() - 1;
		
		for (int col = 0; col < board.getWidth(); col++)
		{
			Piece piece = board.getAt(row, col);
			
			boolean rookFound = !board.isEmptyAt(row, col) && piece.getColor() == getColor() 
					&& piece.getClass().equals(Rook.class) && !piece.isMoved();
			
			if (!rookFound) 
				continue;				
			
			Rook rook = (Rook)piece;
			
			boolean rookLeftFromKing = col < kingPosition.getColumn();
			
			OffsetPair rookDirection = rookLeftFromKing ? OffsetPair.RIGHT : OffsetPair.LEFT;
			
			boolean allCellsFree = true;
			
			Position rookPosition = new Position(row, col);
			
			while (true)
			{
				rookPosition = rookPosition.moveBy(rookDirection);
				
				if (!board.isEmptyAt(rookPosition))
				{
					if (!rookPosition.equals(kingPosition))
					{
						allCellsFree = false;						
					}
					
					break;
				}
			}
			
			if (!allCellsFree)
				continue;
			
			OffsetPair kingDirection = rookLeftFromKing ? OffsetPair.LEFT : OffsetPair.RIGHT;
			Position rookCastlePosition = kingPosition.moveBy(kingDirection);
			
			board.setToEmpty(kingPosition);
			board.setAt(rookCastlePosition, this);
			
			if (!isChecked(rookCastlePosition, board))
			{
				board.setToEmpty(row, col);
				board.setAt(rookCastlePosition, rook);
				
				Position kingCastlePosition = rookCastlePosition.moveBy(kingDirection);
				
				if (board.isEmptyAt(kingCastlePosition))
				{
					board.setAt(kingCastlePosition, this);
					
					if (!isChecked(kingCastlePosition, board))
					{
						castlePairs.add(new CastlePair(rook, rookCastlePosition, kingCastlePosition, new Position(row, col)));
					}
					
					board.setToEmpty(kingCastlePosition);
				}
			}
			
			board.setToEmpty(rookCastlePosition);
			board.setAt(kingPosition, this);
			board.setAt(row, col, rook);
		}
		
		return castlePairs;
	}
}
