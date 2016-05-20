package com.prophet.enums;

/**
 * This enum captures the high level intent of the query
 */
public enum Intent {
	/**
	 * Represents a greeting. eg Hi
	 */
	GREETINGS,
	
	/**
	 * Represents an intent of the user to ask question. 
	 * eg how are you ? or Do you know the time? 
	 */
	QUESTION,

	/**
	 * This includes response to our previous statement. Includes any assertive statement.
	 * eg I'm good, thanks for asking.
	 */
	STATEMENT,

	/**
	 * Represents cases where we cannot determine the user's intent
	 * with certainty.
	 */
	DEFAULT
}
