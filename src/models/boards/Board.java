package models.boards;

import common.Position;
import models.pieces.Piece;

public interface Board 
{
	int getHeight();

	int getWidth();
	
	Piece getAt(Position position);
	
	void setAt(Position position, Piece piece);
	
	boolean isPositionInside(Position position);
}
