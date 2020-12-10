package models.boards;

import common.Position;
import models.pieces.Piece;

public interface Board
{
	int getHeight();

	int getWidth();
	
	Piece getEmptyCellValue();
	
	Piece getAt(Position position);
	
	void setAt(Position position, Piece piece);
	
	Piece setToEmpty(Position position);
	
	boolean isPositionInside(Position position);
}
