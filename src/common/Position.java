package common;

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
	
	@Override
	public String toString()
	{
		String format = "row: %d | column: %d";
		String positionInfo = String.format(format, this.getRow(), this.getColumn());
		return positionInfo;
	}
}
