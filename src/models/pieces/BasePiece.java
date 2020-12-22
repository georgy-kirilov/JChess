package models.pieces;

import common.Position;
import enums.PieceColor;
import jthrow.JThrower;
import models.boards.Board;

public abstract class BasePiece implements Piece
{
	private final PieceColor color;
	private boolean moved;
	
	public BasePiece(PieceColor color)
	{	
		JThrower.throwIf(color, "color").isNull();
		this.color = color;
		
		this.moved = false;
	}
	
	public PieceColor getColor()
	{
		return this.color;
	}
	
	public boolean isMoved()
	{
		return this.moved;
	}
	
	public void move()
	{
		this.moved = true;
	}
	
	public abstract Iterable<Position> getAllReachablePositions(Position currentPosition, Board board);
}
