package common;

import models.pieces.Rook;

public class CastlePair
{
	private Rook rook;
	private Position rookPosition;
	private Position kingPosition;
	private Position initialRookPosition;
	
	public CastlePair(Rook rook, Position rookPosition, Position kingPosition, Position initialRookPosition)
	{
		this.rook = rook;
		this.rookPosition = rookPosition;
		this.kingPosition = kingPosition;
		this.initialRookPosition = initialRookPosition;
	}
	
	public Rook getRook()
	{
		return rook;
	}
	
	public Position getRookPosition()
	{
		return rookPosition;
	}
	
	public Position getKingPosition()
	{
		return kingPosition;
	}
	
	public Position getInitialRookPosition()
	{
		return initialRookPosition;
	}
}
