package common;

public class Helper
{
	private Helper() { }
	
	public static void throwIfNull(Object obj)
	{
		if (obj == null)
		{
			throw new NullPointerException(
					GlobalConstants.ErrorMessages.CANNOT_BE_NULL);			
		}
	}
	
	public static boolean isInRange(int min, int max, int actual)
	{
		return min <= actual && actual <= max;
	}
	
	public static void throwIfOutOfRange(int min, int max, int actual)
	{
		if (!isInRange(min, max, actual))
		{
			throw new IllegalArgumentException(
					GlobalConstants.ErrorMessages.OUT_OF_RANGE);			
		}
	}
	
	public static boolean isNullOrEmpty(String str)
	{
		return str == null || str.length() == 0;
	}
	
	public static boolean isQueenChar(char character)
	{	
		return character == 'Q' || character == 'q';
	}
	
	public static boolean isKnightChar(char character)
	{
		return character == 'K' || character == 'k';
	}
	
	public static boolean isBishopChar(char character)
	{
		return character == 'B' || character == 'b';
	}
	
	public static boolean isRookChar(char character)
	{
		return character == 'R' || character == 'r';
	}
}
