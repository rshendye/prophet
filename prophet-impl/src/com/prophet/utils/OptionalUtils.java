package com.prophet.utils;

import java.util.Optional;


/**
 * utility class to handle optionals
 * @author rshendye
 *
 */
public class OptionalUtils {
	
	/**
	 * Returns an optional of the given value. 
	 * @param value Non-null value which needs to be encapsulated in an optional.
	 * @return
	 */
	public static <T> Optional<T> getOptional(T value) {
		return Optional.ofNullable(value);
	}
}
