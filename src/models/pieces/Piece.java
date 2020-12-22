package models.pieces;

import common.Position;
import enums.PieceColor;
import models.boards.Board;

public interface Piece 
{	
	PieceColor getColor();
	
	boolean isMoved();
	
	void move();
	
	Iterable<Position> getAllReachablePositions(Position currentPosition, Board board);
}
