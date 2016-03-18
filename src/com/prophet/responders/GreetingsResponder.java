package com.prophet.responders;

import static com.prophet.dictionary.Constants.EXCLAMATION_MARK;
import static com.prophet.dictionary.Constants.SPACE;
import static com.prophet.dictionary.GreetingsDictionary.POLITE_QUESTIONS;
import static com.prophet.dictionary.GreetingsDictionary.STD_GREETINGS;

import com.prophet.dictionary.Intent;
import static com.prophet.utils.LambdaUtils.*;
import com.prophet.utils.RandomUtils;

/**
 * Responder for queries with {@link Intent} GREETINGS
 */
public class GreetingsResponder implements Responder {
  private static final int PROBABILITY_OF_POLITE_QUESTIONS = 30;

	@Override
	public String getResponse(final String input) {
		// simple loop and return the first standard greeting that does not match the input
		StringBuilder stringBuilder = new StringBuilder(STD_GREETINGS.stream()
		    .filter(greeting -> (input == null) || !input.equalsIgnoreCase(greeting))
		    .findAny()
		    .get());
		
		addExclamation(stringBuilder);
		addPoliteQuestion(stringBuilder);
		
		return stringBuilder.toString();
	}

	/**
	 * Adds a polite question to the conversation with some probability
	 * @param stringBuilder
	 */
	private void addPoliteQuestion(StringBuilder stringBuilder) {
		RandomUtils.performWithProbability(70, getStringBuilderAppender(
		    SPACE, POLITE_QUESTIONS.stream().findFirst().get()), stringBuilder);
	} 

	/**
	 * Adds an exclamation mark with some probability
	 * @param stringBuilder
	 */
	private void addExclamation(StringBuilder stringBuilder) {
		RandomUtils.performWithProbability(PROBABILITY_OF_POLITE_QUESTIONS, 
		    getStringBuilderAppender(EXCLAMATION_MARK), stringBuilder);
	}

}
