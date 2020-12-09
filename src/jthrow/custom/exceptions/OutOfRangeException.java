package jthrow.custom.exceptions;

public class OutOfRangeException extends RuntimeException
{
	private static final long serialVersionUID = 1L;
	
	public OutOfRangeException(String message)
	{
		super(message);
	}
}
