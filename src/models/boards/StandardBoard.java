package models.boards;

import models.pieces.Piece;

public class StandardBoard extends BaseBoard
{
	private static final int STANDARD_HEIGHT = 8;
	private static final int STANDARD_WIDTH = 8;
	private static final Piece EMPTY_CELL_VALUE = null;
	
	public StandardBoard()
	{
		super(STANDARD_HEIGHT, STANDARD_WIDTH, EMPTY_CELL_VALUE);
		
		//this.initializePieces();
	}
	
	private void initializePieces()
	{
		//TODO: Implement initializePieces
		throw new UnsupportedOperationException();
	}
}
