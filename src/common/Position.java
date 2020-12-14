package common;

import jthrow.JThrower;

public class Position 
{
	private int row;
	private int column;
	
	public Position(int row, int column)
	{
		this.setRow(row);
		this.setColumn(column);
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
		return this.row;
	}
	
	public int getColumn()
	{
		return this.column;
	}
	
	public boolean equals(Position position)
	{
		boolean areEqual = position != null && position.getRow() == this.getRow() 
				&& position.getColumn() == this.getColumn();
		
		return areEqual;
	}
	
	public String toString()
	{
		String format = "%s: %d | %s: %d";
		
		String positionInfo = String.format(format, ParameterNames.ROW, this.getRow(), 
				ParameterNames.COLUMN, this.getColumn());
		
		return positionInfo;
	}
}
