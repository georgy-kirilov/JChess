package models.boards;

import common.Position;
import jthrow.JChecker;
import jthrow.JThrower;
import models.pieces.Piece;
import common.ParameterNames;

public abstract class BaseBoard implements Board
{	
	private static final int MIN_HEIGHT = 4;
	private static final int MAX_HEIGHT = 16;
	private static final int MIN_WIDTH = 4;
	private static final int MAX_WIDTH = 16;
	
	private final int height;
	private final int width;
	
	private final Piece emptyCellValue;
	private final Piece[][] cells;
	
	public BaseBoard(int height, int width, Piece emptyCellValue)
	{
		JThrower.throwIf(height, ParameterNames.HEIGHT)
				.isOutOfRange(MIN_HEIGHT, MAX_HEIGHT);
		
		JThrower.throwIf(width, ParameterNames.WIDTH)
				.isOutOfRange(MIN_WIDTH, MAX_WIDTH);
		
		this.height = height;
		this.width = width;
		this.emptyCellValue = emptyCellValue;
		
		this.cells = new Piece[this.getHeight()][this.getWidth()];
		this.initializeAsEmpty();
	}

	@Override
	public int getHeight()
	{
		return this.height;
	}

	@Override
	public int getWidth()
	{
		return this.width;
	}
	
	@Override
	public Piece getEmptyCellValue()
	{
		return this.emptyCellValue;
	}

	@Override
	public Piece getAt(Position position)
	{
		this.validatePosition(position);
		return this.cells[position.getRow()][position.getColumn()];
	}

	@Override
	public void setAt(Position position, Piece piece)
	{
		this.validatePosition(position);
		this.cells[position.getRow()][position.getColumn()] = piece;
	}

	@Override
	public Piece setToEmpty(Position position) 
	{
		Piece oldPiece = this.getAt(position);
		this.setAt(position, this.getEmptyCellValue());
		return oldPiece;
	}
	
	@Override
	public boolean isEmptyAt(Position position) 
	{
		return this.getAt(position) == this.getEmptyCellValue();
	}
	
	@Override
	public boolean isPositionInside(Position position)
	{
		try
		{
			this.validatePosition(position);			
		}
		catch (IllegalArgumentException e)
		{
			return false;
		}
		
		return true;
	}
	
	private void validatePosition(Position position)
	{
		int maxRows = this.getHeight() - 1;
		int maxColumns = this.getWidth() - 1;
		int minRowAndColumnValue = 0;
		
		JThrower.throwIf(position, ParameterNames.POSITION)
				.isNull()
				.has(pos -> JChecker.isOutOfRange(minRowAndColumnValue, pos.getRow(), maxRows))
				.has(pos -> JChecker.isOutOfRange(minRowAndColumnValue, pos.getColumn(), maxColumns));
	}
	
	private void initializeAsEmpty()
	{	
		for (int row = 0; row < this.getHeight(); row++)
		{
			for (int column = 0; column < this.getWidth(); column++)
			{
				this.cells[row][column] = this.getEmptyCellValue();
			}
		}
	}
}
