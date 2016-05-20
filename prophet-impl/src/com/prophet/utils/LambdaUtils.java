package com.prophet.utils;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;


/**
 * Class to store all the lambdas 
 */
public class LambdaUtils {
	/**
	 * Returns a unary operator that appends the strings to the string builder
	 * @param strings
	 * @return
	 */
	public static UnaryOperator<StringBuilder> getStringBuilderAppender(final String ...strings) {
		return new UnaryOperator<StringBuilder>() {

			@Override
			public StringBuilder apply(StringBuilder stringBuilder) {
				Optional.ofNullable(strings)
				    .filter(ArrayUtils::isNotEmpty)
				    .map(Stream::of)
				    .orElse(Stream.empty())
				    .filter(StringUtils::isNotEmpty)
				    .forEach(stringBuilder::append);
				
				return stringBuilder;
			}
		};
	}
	
	/**
	 * Returns a function that returns the same return value for any input
	 * @param constantReturnValue value that is always returned by the function
	 * @return Function
	 */
	public static <T,R> Function<T, R> constantFunction(final R constantReturnValue) {
		return (T t) ->  constantReturnValue;
	}

	/**
	 * Returns a supplier which supplies a constant value every time it is invoked.
	 * @param constantValue value to be supplied by the supplier for all invocations.
	 * @return supplier
	 */
	public static <T> Supplier<T> constantSupplier(T constantValue) {
		return () -> constantValue;
	}
	
	/**
	 * Returns any one element from the given collection
	 * @param collection which must not be null or empty
	 * @return 
	 * @throws Exception 
	 */
	public static <T> T findAnyFromCollection(final Collection<T> collection) throws Exception {
	  if (CollectionUtils.hasNoValues(collection)) {
	    throw new Exception("Collection cannot be null");
	  }
	    
	  return collection.stream()
	      .findAny()
	      .get();
	}
	
	public static <T> Predicate<T> negate (Predicate<T> predicate) {
	  return (T t) -> !predicate.test(t);
	}
}
