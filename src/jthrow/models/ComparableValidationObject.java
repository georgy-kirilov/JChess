package jthrow.models;

import jthrow.JChecker;
import jthrow.custom.exceptions.OutOfRangeException;
import jthrow.services.MessageFactory;

public class ComparableValidationObject<T extends Comparable<T>> extends NullableValidationObject<T>
{
	public ComparableValidationObject(T parameter)
	{
		super(parameter);
	}
	
	public ComparableValidationObject(T parameter, String parameterName) 
	{
		super(parameter, parameterName);
	}
	
	public ComparableValidationObject<T> isNull()
	{
		super.isNull();
		return this;
	}
	
	public ComparableValidationObject<T> isLessThan(T value)
	{	
		if (JChecker.isLessThan(value, this.getParameterValue()))
		{
			throw new OutOfRangeException
				(MessageFactory.lessThan(this.getParameterName(), value.toString()));
		}
		
		return this;
	}
	
	public ComparableValidationObject<T> isGreaterThan(T value)
	{	
		if (JChecker.isGreaterThan(value, this.getParameterValue()))
		{
			throw new OutOfRangeException
				(MessageFactory.greaterThan(this.getParameterName(), value.toString()));
		}
		
		return this;
	}
	
	public ComparableValidationObject<T> isEqualTo(T value)
	{		
		if (JChecker.areEqual(this.getParameterValue(), value))
		{
			throw new IllegalArgumentException
				(MessageFactory.equalTo(this.getParameterName(), value.toString()));
		}
		
		return this;
	}
	
	public ComparableValidationObject<T> isOutOfRange(T min, T max)
	{
		if (JChecker.isOutOfRange(min, this.getParameterValue(), max))
		{
			throw new OutOfRangeException(MessageFactory.outOfRange
				(this.getParameterName(), min.toString(), max.toString()));
		}

		return this;
	}
	
	public ComparableValidationObject<T> has(Predicate<T> predicate)
	{
		super.has(predicate);
		return this;
	}
}
