package com.prophet.statics;

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
	QUESTION;
}
