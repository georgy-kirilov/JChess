package common;

import jthrow.JThrower;

public class Position 
{
	private static final int MIN_VALUE = 0;
	
	private int row;
	private int column;
	
	public Position(int row, int column)
	{
		this.setRow(row);
		this.setColumn(column);
	}
	
	public void setRow(int row)
	{
		JThrower.throwIf(row, "Row").isLessThan(MIN_VALUE);
		this.row = row;
	}
	
	public void setColumn(int column)
	{
		JThrower.throwIf(column, "Column").isLessThan(MIN_VALUE);
		this.column = column;
	}
	
	public int getRow()
	{
		return this.row;
	}
	
	public int getColumn()
	{
		return this.column;
	}
	
	public String toString()
	{
		return String.format("Row: %d | Column: %d", this.getRow(), this.getColumn());
	}
}
