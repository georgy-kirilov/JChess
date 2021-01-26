package models.boards;

import common.Helper;
import common.Position;

import models.pieces.Piece;

public abstract class BaseBoard implements Board
{	
	private final int MIN_HEIGHT = 5;
	private final int MAX_HEIGHT = 16;
	private final int MIN_WIDTH = 5;
	private final int MAX_WIDTH = 16;
	
	private final int height;
	private final int width;
	
	private final Piece[][] cells;
	
	public BaseBoard(int height, int width)
	{
		Helper.throwIfOutOfRange(MIN_HEIGHT, MAX_HEIGHT, height);
		this.height = height;
		
		Helper.throwIfOutOfRange(MIN_WIDTH, MAX_WIDTH, width);
		this.width = width;
	
		cells = new Piece[getHeight()][getWidth()];
		initializeAsEmpty();
	}

	@Override
	public int getHeight()
	{
		return height;
	}

	@Override
	public int getWidth()
	{
		return width;
	}

	@Override
	public Piece getAt(Position position)
	{
		return getAt(position.getRow(), position.getColumn());
	}
	
	@Override
	public Piece getAt(int row, int column)
	{
		return cells[row][column];
	}

	@Override
	public void setAt(Position position, Piece piece)
	{
		setAt(position.getRow(), position.getColumn(), piece);
	}

	@Override
	public void setAt(int row, int column, Piece piece)
	{
		cells[row][column] = piece;
	}
	
	@Override
	public Piece setToEmpty(Position position) 
	{
		Piece oldPiece = getAt(position);
		setAt(position, EMPTY_CELL);
		return oldPiece;
	}
	
	@Override
	public Piece setToEmpty(int row, int column) 
	{
		Piece oldPiece = getAt(row, column);
		setAt(row, column, EMPTY_CELL);
		return oldPiece;
	}
	
	@Override
	public boolean isEmptyAt(Position position) 
	{
		return getAt(position) == EMPTY_CELL;
	}

	@Override
	public boolean isEmptyAt(int row, int column)
	{
		return getAt(row, column) == EMPTY_CELL;
	}
	
	@Override
	public boolean isPositionInside(Position position)
	{
		int maxRows = getHeight() - 1;
		int maxColumns = getWidth() - 1;
		
		return Helper.isInRange(0, maxRows, position.getRow()) && 
				Helper.isInRange(0, maxColumns, position.getColumn());
	}
	
	@Override
	public void rotate() 
	{	
		for (int i = 0; i < 2; i++)
		{
			for (int row = 0; row < getHeight() / 2; row++)
	        {
	            for (int col = row; col < getWidth() - row - 1; col++) 
	            { 
	                Piece temp = getAt(row, col);
	  
	                int newCol = getHeight() - 1 - row;
	                int newRow = getWidth() - 1 - col;
	                
	                cells[row][col] = cells[col][newCol]; 
	                cells[col][newCol] = cells[newCol][newRow];
	                cells[newCol][newRow] = cells[newRow][row]; 
	                cells[newRow][row] = temp;
	            } 
	        } 
		}
	} 
	
	private void initializeAsEmpty()
	{	
		for (int row = 0; row < getHeight(); row++)
		{
			for (int col = 0; col < getWidth(); col++)
			{
				setToEmpty(row, col);
			}
		}
	}
}
