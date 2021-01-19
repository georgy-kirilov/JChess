package common;

public class OffsetPair
{
	public static final OffsetPair UP = new OffsetPair(-1, 0);
	public static final OffsetPair RIGHT = new OffsetPair(0, 1);
	public static final OffsetPair DOWN = new OffsetPair(1, 0);
	public static final OffsetPair LEFT = new OffsetPair(0, -1);
	public static final OffsetPair TOP_RIGHT = new OffsetPair(-1, 1);
	public static final OffsetPair TOP_LEFT = new OffsetPair(-1, -1);
	public static final OffsetPair BOTTOM_RIGHT = new OffsetPair(1, 1);
	public static final OffsetPair BOTTOM_LEFT = new OffsetPair(1, -1);
	
	private int rowOffset;
	private int columnOffset;
	
	public OffsetPair(int rowOffset, int columnOffset)
	{
		this.rowOffset = rowOffset;
		this.columnOffset = columnOffset;
	}
	
	public int getRowOffset()
	{
		return this.rowOffset;
	}
	
	public int getColumnOffset()
	{
		return this.columnOffset;
	}
}
