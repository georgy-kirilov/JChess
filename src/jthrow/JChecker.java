package jthrow;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JChecker 
{
	public static final String EMAIL_REGEX_PATTERN = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
	
	private JChecker() { }
	
	public static boolean isNull(Object obj)
	{
		return obj == null;
	}
	
	public static boolean isNullOrEmpty(String str)
	{
		return isNull(str) || str.isEmpty();
	}
	
	public static boolean isNullOrWhiteSpace(String str)
	{
		return isNull(str) || str.trim().length() == 0;
	}
	
	public static <T extends Comparable<T>> boolean isLessThan(T min, T actual)
	{
		return actual.compareTo(min) < 0;
	}
	
	public static <T extends Comparable<T>> boolean isGreaterThan(T max, T actual)
	{
		return actual.compareTo(max) > 0;
	}
	
	public static <T extends Comparable<T>> boolean isInRange(T min, T actual, T max)
	{
		return !isLessThan(min, actual) && !isGreaterThan(max, actual);
	}
	
	public static <T extends Comparable<T>> boolean isOutOfRange(T min, T actual, T max)
	{
		return !isInRange(min, actual, max);
	}
	
	public static boolean areEqual(Object a, Object b)
	{
		return a.equals(b);
	}
	
	public static <T extends Comparable<T>> boolean areEqual(T a, T b)
	{
		return a.compareTo(b) == 0;
	}
	
	public static boolean matchesPattern(String regexPattern, String text)
	{
		Matcher matcher = Pattern.compile(regexPattern).matcher(text);
		return matcher.matches();
	}
	
	public static boolean isValidEmail(String value)
	{
		return matchesPattern(EMAIL_REGEX_PATTERN, value);
	}
}
