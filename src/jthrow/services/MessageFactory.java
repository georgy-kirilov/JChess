package jthrow.services;

public class MessageFactory
{
	private MessageFactory() { }
	
	public static String cannotBeNull(String parameterName)
	{
		return String.format(MessageFormats.CANNOT_BE_NULL, parameterName);
	}
	
	public static String lessThan(String parameterName, String minValue)
	{
		return String.format(MessageFormats.LESS_THAN, parameterName, minValue);
	}
	
	public static String greaterThan(String parameterName, String maxValue)
	{
		return String.format(MessageFormats.GREATER_THAN, parameterName, maxValue);
	}
	
	public static String equalTo(String parameterName, String forbiddenValue)
	{
		return String.format(MessageFormats.EQUAL_TO, parameterName, forbiddenValue);
	}
	
	public static String outOfRange(String parameterName, String minValue, String maxValue)
	{
		return String.format(MessageFormats.OUT_OF_RANGE, parameterName, minValue, maxValue);
	}
	
	public static String cannotBeEmpty(String parameterName)
	{
		return String.format(MessageFormats.CANNOT_BE_EMPTY, parameterName);
	}
	
	public static String doesNotMatchPattern(String parameterName, String pattern)
	{
		return String.format(MessageFormats.DOES_NOT_MATCH_PATTERN, parameterName, pattern);
	}
	
	public static String invalidParameterState(String parameterName)
	{
		return String.format(MessageFormats.INVALID_PARAMETER_STATE, parameterName);
	}
	
	public static String cannotBeWhiteSpace(String parameterName)
	{
		return String.format(MessageFormats.CANNOT_BE_WHITE_SPACE, parameterName);
	}
	
	public static String invalidEmailAddress()
	{
		return String.format(MessageFormats.INVALID_EMAIL);
	}
}
