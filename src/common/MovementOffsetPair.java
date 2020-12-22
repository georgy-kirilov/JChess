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
	
	//This is only for the knight don't touch it
	public static final MovementOffsetPair Knight_2Up_1Left = new MovementOffsetPair(-2, -1);
	public static final MovementOffsetPair Knight_2Up_1Right = new MovementOffsetPair(-2, 1);
	public static final MovementOffsetPair Knight_1Up_2Left = new MovementOffsetPair(-1, -2);
	public static final MovementOffsetPair Knight_1Up_2Right = new MovementOffsetPair(-1, 2);
	public static final MovementOffsetPair Knight_2Down_1Left = new MovementOffsetPair(2, -1);
	public static final MovementOffsetPair Knight_2Down_1Right = new MovementOffsetPair(2, 1);
	public static final MovementOffsetPair Knight_1Down_2Left = new MovementOffsetPair(1, -2);
	public static final MovementOffsetPair Knight_1Down_2Right = new MovementOffsetPair(1, 2);
	
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
