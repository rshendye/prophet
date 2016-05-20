package com.prophet.dictionary;

/**
 * Enum to identify the type of question being asked 
 * @author rshendye
 *
 */
public enum QuestionType {
	/**
	 * Represents a personal question asked to com.prophet.
	 * eg. How are you?
	 */
	PERSONAL_QUESTION,
	
	/**
	 * Command or execution requested to com.prophet
	 * eg. What is the time?
	 */
	COMMAND,
	
	/**
	 * Represents the default question type, to be used 
	 * when the tone of question and its intent cannot be determined. 
	 * Typically we should respond with a conversation starter
	 */
	DEFAULT
}
