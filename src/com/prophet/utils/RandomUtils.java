package com.prophet.utils;

import java.util.Random;
import java.util.function.UnaryOperator;

/**
 * Utility class for random number generation and manipulation
 */
public class RandomUtils {
	private static final Random RANDOM = new Random();
	private static final int UPPER_LIMIT = 100;
	
	/**
	 * Performs the given function, if a randomly generated number between
	 * 0 and 100 is lesser than threshold. ie threshold% of the time
	 * 
	 * @param threshold
	 * @param unaryOperator function to be applied to
	 * @param unaryOperator
	 */
	public static <T> void performWithProbability(int threshold, UnaryOperator<T> unaryOperator, T target) {
		if (RANDOM.nextInt(UPPER_LIMIT) < threshold) {
			unaryOperator.apply(target);
		}
	}
}
