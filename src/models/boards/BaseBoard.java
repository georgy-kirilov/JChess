package models.boards;

import common.Position;
import models.pieces.Piece;
import validation.ThrowHelper;

public abstract class BaseBoard implements Board
{	
	private static final int MIN_HEIGHT = 4;
	private static final int MAX_HEIGHT = 16;
	private static final int MIN_WIDTH = 4;
	private static final int MAX_WIDTH = 16;
	
	private final int height;
	private final int width;
	
	private final Piece[][] cells;
	
	public BaseBoard(int height, int width)
	{
		ThrowHelper.throwIfOutOfRange(MIN_HEIGHT, MAX_HEIGHT, height);
		ThrowHelper.throwIfOutOfRange(MIN_WIDTH, MAX_WIDTH, width);
	
		this.height = height;
		this.width = width;
		
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
	public Piece getAt(Position position)
	{
		return this.getAt(position.getRow(), position.getColumn());
	}
	
	@Override
	public Piece getAt(int row, int column)
	{
		return this.cells[row][column];
	}

	@Override
	public void setAt(Position position, Piece piece)
	{
		this.setAt(position.getRow(), position.getColumn(), piece);
	}

	@Override
	public void setAt(int row, int column, Piece piece)
	{
		this.cells[row][column] = piece;
	}
	
	@Override
	public Piece setToEmpty(Position position) 
	{
		Piece oldPiece = this.getAt(position);
		this.setAt(position, EMPTY_CELL);
		return oldPiece;
	}
	
	@Override
	public Piece setToEmpty(int row, int column) 
	{
		Piece oldPiece = this.getAt(row, column);
		this.setAt(row, column, EMPTY_CELL);
		return oldPiece;
	}
	
	@Override
	public boolean isEmptyAt(Position position) 
	{
		return this.getAt(position) == EMPTY_CELL;
	}

	@Override
	public boolean isEmptyAt(int row, int column)
	{
		return this.getAt(row, column) == EMPTY_CELL;
	}
	
	@Override
	public boolean isPositionInside(Position position)
	{
		int maxRows = this.getHeight() - 1;
		int maxColumns = this.getWidth() - 1;
		
		return ThrowHelper.isInRange(0, maxRows, position.getRow()) && 
				ThrowHelper.isInRange(0, maxColumns, position.getColumn());
	}
	
	@Override
	public void rotate() 
	{	
		for (int i = 0; i < 2; i++)
		{
			for (int row = 0; row < this.getHeight() / 2; row++)
	        {
	            for (int col = row; col < this.getWidth() - row - 1; col++) 
	            { 
	                Piece temp = this.getAt(row, col);
	  
	                int newCol = this.getHeight() - 1 - row;
	                int newRow = this.getWidth() - 1 - col;
	                
	                this.cells[row][col] = this.cells[col][newCol]; 
	                this.cells[col][newCol] = this.cells[newCol][newRow];
	                this.cells[newCol][newRow] = this.cells[newRow][row]; 
	                this.cells[newRow][row] = temp;
	            } 
	        } 
		}
	} 
	
	private void initializeAsEmpty()
	{	
		for (int row = 0; row < this.getHeight(); row++)
		{
			for (int col = 0; col < this.getWidth(); col++)
			{
				this.setToEmpty(row, col);
			}
		}
	}
}
