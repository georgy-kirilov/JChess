package models.pieces;

import java.util.Collection;

import common.Position;
import enums.PieceColor;
import models.boards.Board;

public interface Piece 
{	
	PieceColor getColor();
	
	boolean isMoved();
	
	void move();
	
	boolean canCaptureAt(Position position, Board board);
	
	boolean canMoveFreelyTo(Position position, Board board);
	
	Collection<Position> getReachablePositions(Position piecePosition, Board board);
}
