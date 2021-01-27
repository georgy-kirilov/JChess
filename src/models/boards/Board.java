package models.boards;

import common.Position;
import models.pieces.Piece;

public interface Board
{
	int getHeight();

	int getWidth();
	
	Piece getAt(Position position);
	
	Piece getAt(int row, int column);
	
	void setAt(Position position, Piece piece);
	
	void setAt(int row, int column, Piece piece);
	
	Piece setToEmpty(Position position);
	
	Piece setToEmpty(int row, int column);
	
	boolean isEmptyAt(Position position);
	
	boolean isEmptyAt(int row, int column);
	
	boolean isPositionInside(Position position);
	
	void rotate();
	
	void initialize();
	
	Piece EMPTY_CELL = null;
}
