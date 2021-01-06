package models.boards;

import common.Position;
import models.pieces.Piece;

public interface Board
{
	int getHeight();

	int getWidth();
	
	Piece getAt(Position position);
	
	void setAt(Position position, Piece piece);
	
	Piece setToEmpty(Position position);
	
	boolean isEmptyAt(Position position);
	
	boolean isPositionInside(Position position);
	
	Piece getAt(int row, int column);
	
	Piece EMPTY_CELL = null;
	
	void flipBoard();
}
