package jthrow.models;

public interface Predicate<T>
{
	boolean invoke(T parameter);
}
