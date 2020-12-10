package models.pieces;

import java.util.ArrayList;

import common.Position;
import enums.PieceType;
import enums.PieceColor;
import models.boards.Board;

public interface Piece 
{
	PieceType getType();
	
	PieceColor getColor();
	
	boolean hasMoved();
	
	void move();
	
	ArrayList<Position> getAllReachablePositions(Position currentPosition, Board board);
}
