package models.boards;

public class StandardBoard extends BaseBoard
{
	private static final int STANDARD_HEIGHT = 8;
	private static final int STANDARD_WIDTH = 8;
	
	public StandardBoard()
	{
		super(STANDARD_HEIGHT, STANDARD_WIDTH);
		
		//this.initializePieces();
	}
	
	private void initializePieces()
	{
		//TODO: Implement initializePieces
		throw new UnsupportedOperationException();
	}
}
