package models.pieces;

import java.util.ArrayList;
import java.util.Collection;

import common.Castle;
import common.Position;
import common.OffsetPair;

import enums.PieceColor;
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
				
				// Checks for at least one enemy move which puts the king in check
				
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
			return false;			
		
		for (int i = 0; i < board.getHeight(); i++)
		{
			for (int j = 0; j < board.getWidth(); j++)
			{
				Piece piece = board.getAt(i, j);
				
				if (board.isEmptyAt(i, j) || piece.getColor() != getColor())
					continue;
				
				// Checks for at least one move which doesn't put king in check
				
				Collection<Position> positions = piece.getReachablePositions(new Position(i, j), board);
				
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
					
					if (!checked)
						return false;				
				}
			}
		}
		
		return true;
	}
	
	public Collection<Castle> getPossibleCastles(Position kingPosition, Board board)
	{	
		ArrayList<Castle> castles = new ArrayList<>();
		
		if (isMoved())
			return castles;
		
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
			
			boolean allInsideCellsFree = true;
			
			Position rookOffset = new Position(row, col);
			
			while (true)
			{
				rookOffset = rookOffset.moveBy(rookDirection);
				
				if (!board.isEmptyAt(rookOffset))
				{
					if (!rookOffset.equals(kingPosition))
						allInsideCellsFree = false;						
					
					break;
				}
			}
			
			if (!allInsideCellsFree)
				continue;
			
			OffsetPair kingOffset = rookLeftFromKing ? OffsetPair.LEFT : OffsetPair.RIGHT;
			Position rookCastlingPosition = kingPosition.moveBy(kingOffset);
			
			board.setToEmpty(kingPosition);
			board.setAt(rookCastlingPosition, this);
			
			if (!isChecked(rookCastlingPosition, board))
			{
				board.setToEmpty(row, col);
				board.setAt(rookCastlingPosition, rook);
				
				Position kingCastlingPosition = rookCastlingPosition.moveBy(kingOffset);
				
				if (board.isEmptyAt(kingCastlingPosition))
				{
					board.setAt(kingCastlingPosition, this);
					
					if (!isChecked(kingCastlingPosition, board))
					{
						Position oldRookPosition = new Position(row, col);
						
						Castle castle = new Castle(rook, rookCastlingPosition, 
								kingCastlingPosition, oldRookPosition);
						
						castles.add(castle);
					}
					
					board.setToEmpty(kingCastlingPosition);
				}
			}
			
			board.setToEmpty(rookCastlingPosition);
			board.setAt(kingPosition, this);
			board.setAt(row, col, rook);
		}
		
		return castles;
	}
}
