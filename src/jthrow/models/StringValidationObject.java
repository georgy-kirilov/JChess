package jthrow.models;

import jthrow.JChecker;
import jthrow.services.MessageFactory;

public class StringValidationObject extends ComparableValidationObject<String>
{
	public StringValidationObject(String parameter) 
	{
		super(parameter);
	}

	public StringValidationObject(String parameter, String parameterName) 
	{
		super(parameter, parameterName);
	}
	
	public StringValidationObject isNull()
	{
		super.isNull();
		return this;
	}
	
	public StringValidationObject isNullOrEmpty()
	{
		this.isNull();
		
		if (JChecker.isNullOrEmpty(this.getParameterValue()))
		{
			throw new IllegalArgumentException
				(MessageFactory.cannotBeEmpty(this.getParameterName()));
		}
		
		return this;
	}
	
	public StringValidationObject isNullOrWhiteSpace()
	{
		this.isNull();
		
		if (JChecker.isNullOrWhiteSpace(this.getParameterValue()))
		{
			throw new IllegalArgumentException
				(MessageFactory.cannotBeWhiteSpace(this.getParameterName()));
		}
		
		return this;
	}
	
	public StringValidationObject isLessThan(String value)
	{
		super.isLessThan(value);
		return this;
	}
	
	public StringValidationObject isGreaterThan(String value)
	{
		super.isGreaterThan(value);
		return this;
	}
	
	public StringValidationObject isEqualTo(String value)
	{
		super.isEqualTo(value);
		return this;
	}
	
	public StringValidationObject isOutOfRange(String min, String max)
	{
		super.isOutOfRange(min, max);
		return this;
	}
	
	public StringValidationObject has(Predicate<String> predicate)
	{
		super.has(predicate);
		return this;
	}
	
	public StringValidationObject doesNotMatchPattern(String regexPattern)
	{
		if (!JChecker.matchesPattern(regexPattern, this.getParameterValue()))
		{
			throw new IllegalArgumentException
				(MessageFactory.doesNotMatchPattern(this.getParameterName(), regexPattern));
		}
		
		return this;
	}
	
	public StringValidationObject isInvalidEmail()
	{
		if (!JChecker.isValidEmail(this.getParameterValue()))
		{
			throw new IllegalArgumentException(MessageFactory.invalidEmailAddress());
		}
		
		return this;
	}
}
