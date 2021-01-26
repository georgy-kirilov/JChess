package common;

import models.pieces.Rook;

public class Castle
{
	private Rook rook;
	private Position newRookPosition;
	private Position newKingPosition;
	private Position oldRookPosition;
	
	public Castle(
			Rook rook,
			Position newRookPosition,
			Position newKingPosition, 
			Position oldRookPosition)
	{
		Helper.throwIfNull(rook);
		this.rook = rook;
		
		Helper.throwIfNull(newRookPosition);
		this.newRookPosition = newRookPosition;
		
		Helper.throwIfNull(newKingPosition);
		this.newKingPosition = newKingPosition;
		
		Helper.throwIfNull(oldRookPosition);
		this.oldRookPosition = oldRookPosition;
	}
	
	public Rook getRook()
	{
		return rook;
	}
	
	public Position getNewRookPosition()
	{
		return newRookPosition;
	}
	
	public Position getNewKingPosition()
	{
		return newKingPosition;
	}
	
	public Position getOldRookPosition()
	{
		return oldRookPosition;
	}
}
