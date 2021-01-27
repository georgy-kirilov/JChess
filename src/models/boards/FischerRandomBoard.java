package models.boards;

import enums.PieceColor;
import models.pieces.Pawn;

public class FischerRandomBoard extends BaseBoard
{
	private static final int BOARD_HEIGHT = 8;
	private static final int BOARD_WIDTH = 8;
	
	public FischerRandomBoard()
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
		
		for (int col = 0; col < getWidth(); col++)
		{
			setAt(whitePawnsRow, col, new Pawn(PieceColor.WHITE));
			setAt(blackPawnsRow, col, new Pawn(PieceColor.BLACK));
		}
		
		
	}
}
