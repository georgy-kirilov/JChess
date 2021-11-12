package models.boards;

import java.util.Random;

import enums.PieceColor;
import models.pieces.Bishop;
import models.pieces.King;
import models.pieces.Knight;
import models.pieces.Pawn;
import models.pieces.Queen;
import models.pieces.Rook;

public class RandomBoard extends BaseBoard
{
	private static final int BOARD_HEIGHT = 8;
	private static final int BOARD_WIDTH = 8;
	
	public RandomBoard()
	{
		super(BOARD_HEIGHT, BOARD_WIDTH);
		initialize();
	}

	@Override
	public void initialize() 
	{
		int whiteKingRow = getHeight() - 1, blackKingRow = 0;
		int whitePawnsRow = whiteKingRow - 1, blackPawnsRow = blackKingRow + 1;
		
		Random random = new Random();
		
		initializePawns(whitePawnsRow, blackPawnsRow);
		initializeBishops(random, whiteKingRow, blackKingRow);
		initializeQueens(random, whiteKingRow, blackKingRow);
		initializeKnights(random, whiteKingRow, blackKingRow);
		initializeKingsAndRooks(whiteKingRow, blackKingRow);
	}
	
	private void initializePawns(int whitePawnsRow, int blackPawnsRow)
	{
		for (int col = 0; col < getWidth(); col++)
		{
			setAt(whitePawnsRow, col, new Pawn(PieceColor.WHITE));
			setAt(blackPawnsRow, col, new Pawn(PieceColor.BLACK));
		}
	}
	
	private void initializeBishops(Random random, int whiteKingRow, int blackKingRow)
	{
		int column = random.nextInt(getWidth());
		
		// Dark
		
		while (column % 2 == 0)
		{
			column = random.nextInt(getWidth());
		}
		
		setAt(whiteKingRow, column, new Bishop(PieceColor.WHITE));
		setAt(blackKingRow, column, new Bishop(PieceColor.BLACK));
		
		// Light
		
		column = random.nextInt(getWidth());
		
		while (column % 2 != 0)
		{
			column = random.nextInt(getWidth());
		}
		
		setAt(whiteKingRow, column, new Bishop(PieceColor.WHITE));
		setAt(blackKingRow, column, new Bishop(PieceColor.BLACK));
	}
	
	private void initializeQueens(Random random, int whiteKingRow, int blackKingRow)
	{
		int column = random.nextInt(getWidth());
		
		while (!isEmptyAt(whiteKingRow, column))
		{
			column = random.nextInt(getWidth());
		}

		setAt(whiteKingRow, column, new Queen(PieceColor.WHITE));
		setAt(blackKingRow, column, new Queen(PieceColor.BLACK));
	}
	
	private void initializeKnights(Random random, int whiteKingRow, int blackKingRow)
	{
		int column = random.nextInt(getWidth());
		
		while (!isEmptyAt(whiteKingRow, column))
		{
			column = random.nextInt(getWidth());
		}
		
		setAt(whiteKingRow, column, new Knight(PieceColor.WHITE));
		setAt(blackKingRow, column, new Knight(PieceColor.BLACK));
		
		column = random.nextInt(getWidth());
		
		while (!isEmptyAt(whiteKingRow, column))
		{
			column = random.nextInt(getWidth());
		}
		
		setAt(whiteKingRow, column, new Knight(PieceColor.WHITE));
		setAt(blackKingRow, column, new Knight(PieceColor.BLACK));
	}
	
	private void initializeKingsAndRooks(int whiteKingRow, int blackKingRow)
	{
		boolean placingKing = false;
		
		for (int col = 0; col < getWidth(); col++)
		{
			if (isEmptyAt(whiteKingRow, col))
			{
				if (placingKing)
				{
					setAt(whiteKingRow, col, new King(PieceColor.WHITE));
					setAt(blackKingRow, col, new King(PieceColor.BLACK));
				}
				else
				{
					setAt(whiteKingRow, col, new Rook(PieceColor.WHITE));
					setAt(blackKingRow, col, new Rook(PieceColor.BLACK));
				}
				
				placingKing = !placingKing;
			}
		}
	}
}
