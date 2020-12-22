package common;

public class MovementOffsetPair
{
	public static final MovementOffsetPair UP = new MovementOffsetPair(-1, 0);
	public static final MovementOffsetPair RIGHT = new MovementOffsetPair(0, 1);
	public static final MovementOffsetPair DOWN = new MovementOffsetPair(1, 0);
	public static final MovementOffsetPair LEFT = new MovementOffsetPair(0, -1);
	public static final MovementOffsetPair TOP_RIGHT = new MovementOffsetPair(-1, 1);
	public static final MovementOffsetPair TOP_LEFT = new MovementOffsetPair(-1, -1);
	public static final MovementOffsetPair BOTTOM_RIGHT = new MovementOffsetPair(1, 1);
	public static final MovementOffsetPair BOTTOM_LEFT = new MovementOffsetPair(1, -1);
	
	private int rowOffset;
	private int columnOffset;
	
	public MovementOffsetPair(int rowOffset, int columnOffset)
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
