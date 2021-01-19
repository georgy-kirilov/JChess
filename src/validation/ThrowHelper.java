package validation;

public class ThrowHelper
{
	public static void throwIfNull(Object obj)
	{
		if (obj == null)
			throw new NullPointerException("Parameter cannot be null");
	}
}
