package models.pieces;

import java.util.ArrayList;

import common.Position;
import enums.PieceColor;
import enums.PieceType;
import jthrow.JThrower;
import models.boards.Board;

public abstract class Piece 
{
	private final PieceType type;
	private final PieceColor color;
	private boolean isMoved;
	
	public Piece(PieceType type, PieceColor color)
	{
		JThrower.throwIf(type, "Type").isNull();
		this.type = type;
			
		JThrower.throwIf(color, "Color").isNull();
		this.color = color;
		
		this.isMoved = false;
	}
	
	public PieceType getType()
	{
		return this.type;
	}
	
	public PieceColor getColor()
	{
		return this.color;
	}
	
	public boolean hasMoved()
	{
		return this.isMoved;
	}
	
	public void move()
	{
		this.isMoved = true;
	}
	
	public abstract ArrayList<Position> getAllFreePositions(Position currentPosition, Board board);	 
}
