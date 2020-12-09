package jthrow;

import jthrow.models.StringValidationObject;
import jthrow.models.NullableValidationObject;
import jthrow.models.ComparableValidationObject;

public class JThrower 
{
	private JThrower() { }
	
	public static <T> NullableValidationObject<T> throwIf(T parameter)
	{
		return new NullableValidationObject<T>(parameter);
	}
	
	public static <T> NullableValidationObject<T> throwIf(T parameter, String parameterName)
	{
		return new NullableValidationObject<T>(parameter, parameterName);
	}
	
	public static <T extends Comparable<T>> ComparableValidationObject<T> throwIf(T parameter)
	{
		return new ComparableValidationObject<T>(parameter);
	}
	
	public static <T extends Comparable<T>> ComparableValidationObject<T> throwIf(T parameter, String parameterName)
	{
		return new ComparableValidationObject<T>(parameter, parameterName);
	}
	
	public static StringValidationObject throwIf(String parameter)
	{
		return new StringValidationObject(parameter);
	}
	
	public static StringValidationObject throwIf(String parameter, String parameterName)
	{
		return new StringValidationObject(parameter, parameterName);
	}
}
