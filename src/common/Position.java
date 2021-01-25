package common;

import models.boards.Board;

public class Position 
{
	public static final int DEFAULT_ROW = 0;
	public static final int DEFAULT_COLUMN = 0;
	
	private int row;
	private int column;
	
	public Position()
	{
		this(DEFAULT_ROW, DEFAULT_COLUMN);
	}
	
	public Position(int row, int column)
	{
		setRow(row);
		setColumn(column);
	}
	
	public void setRow(int row)
	{
		this.row = row;
	}
	
	public void setColumn(int column)
	{
		this.column = column;
	}
	
	public int getRow()
	{
		return row;
	}
	
	public int getColumn()
	{
		return column;
	}
	
	public boolean equals(Position position)
	{
		boolean areEqual = position != null && position.getRow() == getRow() 
				&& position.getColumn() == getColumn();
		
		return areEqual;
	}
	
	public Position moveBy(OffsetPair offsetPair)
	{
		return new Position(getRow() + offsetPair.getRowOffset(), 
				getColumn() + offsetPair.getColumnOffset());
	}
	
	public Position flipOver(Board board)
	{
		return new Position(board.getHeight() - 1 - getRow(), 
				board.getWidth() - 1 - getColumn());
	}
	
	@Override
	public String toString()
	{
		String format = "row: %d | column: %d";
		String positionInfo = String.format(format, getRow(), getColumn());
		return positionInfo;
	}
}
