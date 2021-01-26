package core;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collection;

import common.Castle;
import common.Helper;
import common.Position;
import common.OffsetPair;
import common.GlobalConstants;

import models.pieces.*;
import models.boards.Board;

import enums.PieceColor;
import enums.ReasonForDraw;

public class GameListener
{	
	private Collection<Castle> possibleCastles;
	private final HashMap<Piece, Collection<Position>> piecesAndPositions;
	
	private final Board board;
	private final IoProvider ioProvider;
	
	private boolean gameOver;
	private PieceColor currentPlayerColor;
	
	private Position enPassantPawnPosition;
	private Position enPassantCapturePosition;
	
	public GameListener(Board board, IoProvider ioProvider)
	{	
		Helper.throwIfNull(board);
		this.board = board;
		
		Helper.throwIfNull(ioProvider);
		this.ioProvider = ioProvider;
		
		currentPlayerColor = PieceColor.WHITE;
		gameOver = false;
		
		piecesAndPositions = new HashMap<>();
		possibleCastles = calculatePossibleCastles();
	}
	
	public Collection<Position> onFromPositionSelected(Position from)
	{
		if (getKingPosition().equals(from))
		{
			ioProvider.announceCastlingPositions(getCastlingPositions());			
		}
		
		return getReachablePositions(from);
	}
	
	public void onToPositionSelected(Position from, Position to)
	{
		if (isGameOver())
		{
			throw new RuntimeException(
					GlobalConstants.ErrorMessages.CANNOT_MAKE_MOVES);							
		}
		
		boolean positionValid = false;
		
		for (Position position : getReachablePositions(from))
		{
			if (position.equals(to))
			{
				positionValid = true;
				break;
			}
		}
		
		if (!positionValid)
		{
			throw new IllegalArgumentException(
					GlobalConstants.ErrorMessages.UNREACHABLE_POSITION);				
		}
	
		Piece piece = board.getAt(from);
		
		if (canCurrentPlayerPerformEnPassant(piece, from))
		{
			board.setToEmpty(enPassantPawnPosition);		
		}
		
		disableEnPassant();
		
		boolean canNextPlayerPerformEnPassant = piece instanceof Pawn && !piece.isMoved()
				 && from.moveBy(OffsetPair.UP).moveBy(OffsetPair.UP).equals(to);
		
		if (canNextPlayerPerformEnPassant)
		{
			enPassantPawnPosition = to.flipOver(board);
			enPassantCapturePosition = from.moveBy(OffsetPair.UP).flipOver(board);
		}
		
		if (piece instanceof King && isValidCastlingPosition(to))
		{
			performCastling(to);
		}
		
		piece = board.getAt(from);
		board.setToEmpty(from);
		board.setAt(to, piece);
		
		piece.move();
		
		if (piece instanceof Pawn && ((Pawn)piece).canBePromoted(to))
		{
			ioProvider.redrawBoard();
			Piece newPiece = ioProvider.announcePawnPromotion(piece.getColor());
			board.setAt(to, newPiece);
		}
		
		nextPlayer();
		nextTurn();
		
		if (isCurrentPlayerInCheck())
		{
			if (isCurrentPlayerInCheckmate())
			{
				gameOver = true;
				ioProvider.announceGameOver(getWinnerColor());
				return;
			}
			
			ioProvider.announceCheck();
		}
		else if (isCurrentPlayerInStalemate())
		{	
			gameOver = true;
			ioProvider.announceDraw(ReasonForDraw.STALEMATE);
		}
	}
	
	public boolean isGameOver()
	{
		return gameOver;
	}
	
	public PieceColor getCurrentPlayerColor()
	{
		return currentPlayerColor;
	}
	
	public PieceColor getLoserColor()
	{
		if (isGameOver()) 
			return getCurrentPlayerColor();			
		
		throw new RuntimeException(
				GlobalConstants.ErrorMessages.CANNOT_OBTAIN_LOSER);
	}
	
	public PieceColor getWinnerColor()
	{
		if (isGameOver())
			return getLoserColor() == PieceColor.WHITE ? PieceColor.BLACK : PieceColor.WHITE;
		
		throw new RuntimeException(
				GlobalConstants.ErrorMessages.CANNOT_OBTAIN_WINNER);
	}
	
	private Collection<Position> getReachablePositions(Position piecePosition)
	{	
		Collection<Position> reachablePositions = new ArrayList<>();
		
		if (checkForMyPieceAt(piecePosition.getRow(), piecePosition.getColumn()))
		{	
			Piece piece = board.getAt(piecePosition);
			
			if (piecesAndPositions.containsKey(piece))
				return piecesAndPositions.get(piece);
			
			Collection<Position> allPositions = piece.getReachablePositions(piecePosition, board);
			
			for (Position position : allPositions)
			{	
				Piece captured = board.setToEmpty(position);
				board.setAt(position, piece);
				board.setToEmpty(piecePosition);
				
				if (!isCurrentPlayerInCheck())
					reachablePositions.add(position);
				
				board.setAt(position, captured);
				board.setAt(piecePosition, piece);
			}
			
			if (getKingPosition().equals(piecePosition))
				reachablePositions.addAll(getCastlingPositions());
			
			if (canCurrentPlayerPerformEnPassant(piece, piecePosition))
				reachablePositions.add(enPassantCapturePosition);
			
			piecesAndPositions.put(piece, reachablePositions);
		}
		
		return reachablePositions;
	}
	
	private boolean isCurrentPlayerInCheck()
	{	
		Position flippedKingPosition = getKingPosition().flipOver(board);
		board.rotate();
		
		for (int row = 0; row < board.getHeight(); row++)
		{
			for (int col = 0; col < board.getWidth(); col++)
			{
				if (!checkForEnemyPieceAt(row, col))
					continue;
				
				Piece piece = board.getAt(row, col);
				
				for (Position position : piece.getReachablePositions(new Position(row, col), board))
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
	
	private boolean isCurrentPlayerInCheckmate()
	{	
		for (int row = 0; row < board.getHeight(); row++)
		{
			for (int col = 0; col < board.getWidth(); col++)
			{
				if (!checkForMyPieceAt(row, col))
					continue;
				
				Piece piece = board.getAt(row, col);
				Collection<Position> positions = piece.getReachablePositions(new Position(row, col), board);
				
				for (Position position : positions)
				{
					Piece captured = board.getAt(position);
					board.setAt(position, piece);
					board.setToEmpty(row, col);
	
					boolean checked = isCurrentPlayerInCheck();
					
					board.setAt(position, captured);
					board.setAt(row, col, piece);
					
					if (!checked)
						return false;
				}
			}
		}
		
		return true;
	}
	
	private King getKing()
	{
		return (King)board.getAt(getKingPosition());
	}
	
	private Position getKingPosition()
	{
		for	(int row = 0; row < board.getHeight(); row++)
		{
			for (int col = 0; col < board.getWidth(); col++)
			{
				Piece piece = board.getAt(row, col);
				
				boolean kingFound = checkForMyPieceAt(row, col)
						&& piece instanceof King;
				
				if (kingFound)
					return new Position(row, col);
			}
		}
		
		throw new RuntimeException(
				GlobalConstants.ErrorMessages.KING_NOT_FOUND);
	}
	
	private boolean isCurrentPlayerInStalemate()
	{
		for (int row = 0; row < board.getHeight(); row++)
		{
			for (int col = 0; col < board.getWidth(); col++)
			{
				if (!checkForMyPieceAt(row, col))
					continue;
				
				Collection<Position> positions = getReachablePositions(new Position(row, col));
					
				if (positions.size() != 0) 
					return false;
			}
		}
		
		return true;
	}
	
	private void disableEnPassant()
	{
		enPassantPawnPosition = null;
		enPassantCapturePosition = null;
	}
	
	private boolean canCurrentPlayerPerformEnPassant(Piece piece, Position piecePosition)
	{
		return checkForMyPieceAt(piecePosition.getRow(), piecePosition.getColumn())
				&& piece instanceof Pawn
				&& (piecePosition.moveBy(OffsetPair.TOP_LEFT).equals(enPassantCapturePosition)
				|| piecePosition.moveBy(OffsetPair.TOP_RIGHT).equals(enPassantCapturePosition));
	}
	
	private Collection<Castle> calculatePossibleCastles()
	{
		Collection<Castle> castles = new ArrayList<>();
		
		King king = getKing();
		Position kingPosition = getKingPosition();
		
		if (king.isMoved())
			return castles;
		
		int row = board.getHeight() - 1;
		
		for (int col = 0; col < board.getWidth(); col++)
		{
			Piece piece = board.getAt(row, col);
			
			boolean rookFound = checkForMyPieceAt(row, col)
					&& piece.getClass().equals(Rook.class) && !piece.isMoved();
			
			if (!rookFound)
				continue;
			
			Rook rook = (Rook)piece;
			
			boolean rookLeftFromKing = col < kingPosition.getColumn();
			
			OffsetPair rookOffset = rookLeftFromKing ? OffsetPair.RIGHT : OffsetPair.LEFT;
			
			boolean allInsideCellsFree = true;
			
			Position rookPosition = new Position(row, col);
			
			while (true)
			{
				rookPosition = rookPosition.moveBy(rookOffset);
				
				if (!board.isEmptyAt(rookPosition))
				{
					if (!rookPosition.equals(kingPosition))
						allInsideCellsFree = false;												
					
					break;
				}
			}
			
			if (!allInsideCellsFree)
				continue;
			
			OffsetPair kingOffset = rookLeftFromKing ? OffsetPair.LEFT : OffsetPair.RIGHT;
			Position rookCastlingPosition = kingPosition.moveBy(kingOffset);
			
			board.setToEmpty(kingPosition);
			board.setAt(rookCastlingPosition, king);
			
			if (!isCurrentPlayerInCheck())
			{
				board.setToEmpty(row, col);
				board.setAt(rookCastlingPosition, rook);
				
				Position kingCastlingPosition = rookCastlingPosition.moveBy(kingOffset);
				
				if (board.isEmptyAt(kingCastlingPosition))
				{
					board.setAt(kingCastlingPosition, king);
					
					if (!isCurrentPlayerInCheck())
					{
						Position oldRookPosition = new Position(row, col);
						
						Castle castle = new Castle(
								rook, rookCastlingPosition, 
								kingCastlingPosition, oldRookPosition);
						
						castles.add(castle);
					}
					
					board.setToEmpty(kingCastlingPosition);
				}
			}
			
			board.setToEmpty(rookCastlingPosition);
			board.setAt(kingPosition, king);
			board.setAt(row, col, rook);
		}
		
		return castles;
	}
	
	private boolean isValidCastlingPosition(Position kingCastlingPosition)
	{
		for (Castle castle : possibleCastles)
		{
			if (castle.getNewKingPosition().equals(kingCastlingPosition))
				return true;			
		}
		
		return false;
	}
	
	private Collection<Position> getCastlingPositions()
	{
		Collection<Position> castlingPositions = new ArrayList<Position>();
		
		for (Castle castle : possibleCastles)
			castlingPositions.add(castle.getNewKingPosition());
		
		return castlingPositions;
	}
	
	private void performCastling(Position kingCastlingPosition)
	{
		for (Castle castle : possibleCastles)
		{
			if (castle.getNewKingPosition().equals(kingCastlingPosition))
			{
				board.setToEmpty(castle.getOldRookPosition());
				board.setAt(castle.getNewRookPosition(), castle.getRook());
				return;
			}
		}
		
		throw new IllegalArgumentException(
				GlobalConstants.ErrorMessages.CANNOT_PERFORM_CASTLING);
	}
	
	private void nextPlayer()
	{
		currentPlayerColor = currentPlayerColor == PieceColor.WHITE 
				? PieceColor.BLACK : PieceColor.WHITE;
	}
	
	private void nextTurn()
	{
		board.rotate();
		piecesAndPositions.clear();
		ioProvider.redrawBoard();
		possibleCastles = calculatePossibleCastles();
	}
	
	private boolean checkForMyPieceAt(int row, int column)
	{
		return !board.isEmptyAt(row, column) 
			&& board.getAt(row, column).getColor() == getCurrentPlayerColor();
	}
	
	private boolean checkForEnemyPieceAt(int row, int column)
	{
		return !board.isEmptyAt(row, column) 
			&& board.getAt(row, column).getColor() != getCurrentPlayerColor();
	}
}
