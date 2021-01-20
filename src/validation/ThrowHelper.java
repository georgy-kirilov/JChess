package validation;

public class ThrowHelper
{
	public static void throwIfNull(Object obj)
	{
		if (obj == null)
			throw new NullPointerException("Parameter cannot be null");
	}
	
	public static boolean isInRange(int min, int max, int actual)
	{
		return min <= actual && actual <= max;
	}
	
	public static void throwIfOutOfRange(int min, int max, int actual)
	{
		if (!isInRange(min, max, actual))
			throw new IllegalArgumentException("Parameter was out of range");
	}
}
