package models.boards;

import models.pieces.*;
import common.Position;
import enums.PieceColor;

public class StandardBoard extends BaseBoard
{
	private static final int BOARD_HEIGHT = 8;
	private static final int BOARD_WIDTH = 8;
	
	public StandardBoard()
	{
		super(BOARD_HEIGHT, BOARD_WIDTH);
		initialize();
	}

	@Override
	public void initialize()
	{
		int whiteKingRow = getHeight() - 1, blackKingRow = 0;
		int whitePawnsRow = whiteKingRow - 1, blackPawnsRow = blackKingRow + 1;
		
		// Setting up both color pawns
		
		for	(int col = 0; col < getWidth(); col++)
		{
			setAt(whitePawnsRow, col, new Pawn(PieceColor.WHITE));
			setAt(blackPawnsRow, col, new Pawn(PieceColor.BLACK));
		}
		
		// Setting up the whites
		
		setAt(new Position(whiteKingRow, 0), new Rook(PieceColor.WHITE));
		setAt(new Position(whiteKingRow, 1), new Knight(PieceColor.WHITE));
		setAt(new Position(whiteKingRow, 2), new Bishop(PieceColor.WHITE));
		setAt(new Position(whiteKingRow, 3), new Queen(PieceColor.WHITE));
		setAt(new Position(whiteKingRow, 4), new King(PieceColor.WHITE));
		setAt(new Position(whiteKingRow, 5), new Bishop(PieceColor.WHITE));
		setAt(new Position(whiteKingRow, 6), new Knight(PieceColor.WHITE));
		setAt(new Position(whiteKingRow, 7), new Rook(PieceColor.WHITE));
		
		// Setting up the blacks
		
		setAt(new Position(blackKingRow, 0), new Rook(PieceColor.BLACK));
		setAt(new Position(blackKingRow, 1), new Knight(PieceColor.BLACK));
		setAt(new Position(blackKingRow, 2), new Bishop(PieceColor.BLACK));
		setAt(new Position(blackKingRow, 3), new Queen(PieceColor.BLACK));
		setAt(new Position(blackKingRow, 4), new King(PieceColor.BLACK));
		setAt(new Position(blackKingRow, 5), new Bishop(PieceColor.BLACK));
		setAt(new Position(blackKingRow, 6), new Knight(PieceColor.BLACK));
		setAt(new Position(blackKingRow, 7), new Rook(PieceColor.BLACK));
	}
}
