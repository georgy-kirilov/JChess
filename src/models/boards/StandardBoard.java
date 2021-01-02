package models.boards;

import models.pieces.*;
import common.Position;
import enums.PieceColor;

public class StandardBoard extends BaseBoard
{
	private static final int STANDARD_HEIGHT = 8;
	private static final int STANDARD_WIDTH = 8;
	
	public StandardBoard()
	{
		super(STANDARD_HEIGHT, STANDARD_WIDTH);
		this.initializePieces();		
	}
	
	private void initializePieces()
	{
		int whitePawnsRow = 6, blackPawnsRow = 1;
		Position currentPawnPosition = new Position();
		
		for	(int i = 0; i < this.getWidth(); i++)
		{
			currentPawnPosition.setColumn(i);
			
			currentPawnPosition.setRow(blackPawnsRow);
			this.setAt(currentPawnPosition, new Pawn(PieceColor.BLACK));
			
			currentPawnPosition.setRow(whitePawnsRow);
			this.setAt(currentPawnPosition, new Pawn(PieceColor.WHITE));
		}
		
		int blackKingRow = 0;
		
		this.setAt(new Position(blackKingRow, 0), new Rook(PieceColor.BLACK));
		this.setAt(new Position(blackKingRow, 1), new Knight(PieceColor.BLACK));
		this.setAt(new Position(blackKingRow, 2), new Bishop(PieceColor.BLACK));
		this.setAt(new Position(blackKingRow, 3), new Queen(PieceColor.BLACK));
		this.setAt(new Position(blackKingRow, 4), new King(PieceColor.BLACK));
		this.setAt(new Position(blackKingRow, 5), new Bishop(PieceColor.BLACK));
		this.setAt(new Position(blackKingRow, 6), new Knight(PieceColor.BLACK));
		this.setAt(new Position(blackKingRow, 7), new Rook(PieceColor.BLACK));
		
		int whiteKingRow = 7;
		
		this.setAt(new Position(whiteKingRow, 0), new Rook(PieceColor.WHITE));
		this.setAt(new Position(whiteKingRow, 1), new Knight(PieceColor.WHITE));
		this.setAt(new Position(whiteKingRow, 2), new Bishop(PieceColor.WHITE));
		this.setAt(new Position(whiteKingRow, 3), new Queen(PieceColor.WHITE));
		this.setAt(new Position(whiteKingRow, 4), new King(PieceColor.WHITE));
		this.setAt(new Position(whiteKingRow, 5), new Bishop(PieceColor.WHITE));
		this.setAt(new Position(whiteKingRow, 6), new Knight(PieceColor.WHITE));
		this.setAt(new Position(whiteKingRow, 7), new Rook(PieceColor.WHITE));
	}
}
