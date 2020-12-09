package jthrow.services;

class MessageFormats
{
	private MessageFormats() { }
	
	public static final String CANNOT_BE_NULL = "%s cannot be null";
	
	public static final String LESS_THAN = "%s cannot be less than %s";
	
	public static final String GREATER_THAN = "%s cannot be greater than %s";
	
	public static final String EQUAL_TO = "%s cannot be equal to %s";
	
	public static final String OUT_OF_RANGE = "%s was outside the range [%s, %s]";
	
	public static final String CANNOT_BE_EMPTY = "%s cannot be empty";
	
	public static final String DOES_NOT_MATCH_PATTERN = "%s did not match the given pattern: %s";
	
	public static final String INVALID_PARAMETER_STATE = "%s had invalid inner state";
	
	public static final String CANNOT_BE_WHITE_SPACE = "%s cannot be white space";
	
	public static final String INVALID_EMAIL = "Invalid email address";
}
