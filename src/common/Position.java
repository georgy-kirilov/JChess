package common;

public class Position 
{
	private static final int OFFSET = 1;
	
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
		String format = "Row: %d | Column: %d";
		String positionInfo = String.format(format, this.getRow(), this.getColumn());
		return positionInfo;
	}
	
	public static Position moveUp(Position current)
	{
		return new Position(current.getRow() - OFFSET, current.getColumn()); 
	}
	
	public static Position moveDown(Position current)
	{
		return new Position(current.getRow() + OFFSET, current.getColumn());
	}
	
	public static Position moveLeft(Position current)
	{
		return new Position(current.getRow(), current.getColumn() - OFFSET);
	}
	
	public static Position moveRight(Position current)
	{
		return new Position(current.getRow(), current.getColumn() + OFFSET);
	}
	
	public static Position moveTopLeft(Position current)
	{
		return new Position(current.getRow() - OFFSET, current.getColumn() - OFFSET);
	}
	
	public static Position moveTopRight(Position current)
	{
		return new Position(current.getRow() - OFFSET, current.getColumn() + OFFSET);
	}
	
	public static Position moveBottomLeft(Position current) 
	{
		return new Position(current.getRow() + OFFSET, current.getColumn() - OFFSET);
	}
	
	public static Position moveBottomRight(Position current)
	{
		return new Position(current.getRow() + OFFSET, current.getColumn() + OFFSET);
	}
}
