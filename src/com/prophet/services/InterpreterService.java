package com.prophet.services;

import static com.prophet.dictionary.Constants.ERROR_IN_PROCESSING_STRING;

import com.prophet.responders.FallbackResponder;
import com.prophet.responders.StatementResponder;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.prophet.analyzers.BasicQueryAnalyzer;
import com.prophet.responders.BasicQuestionResponder;
import com.prophet.responders.GreetingsResponder;
import com.prophet.responders.Responder;
import com.prophet.dictionary.Intent;

import static com.prophet.dictionary.Intent.*;

/**
 * This class accepts user input, analyzes it and returns an appropriate response
 */
public class InterpreterService {
	// TODO: wire this in using springs 
	// OR store this in the constants file
	private static final Map<Intent, Responder> INTENT_TO_RESPONDER_MAP;
	static {
		ImmutableMap.Builder<Intent, Responder> intentToResponderBuilder = ImmutableMap.<Intent, Responder>builder()
				.put(GREETINGS, new GreetingsResponder())
				.put(STATEMENT, new StatementResponder())
				.put(QUESTION, new BasicQuestionResponder())
				.put(DEFAULT, new FallbackResponder());
		
		INTENT_TO_RESPONDER_MAP = intentToResponderBuilder.build();
	}
	
	/**
	 * Accepts user input and returns an appropriate response. 
	 * 
	 * Contains 2 main parts:
	 * 1. Try and understand what the user is trying to say
	 * 2. Generate an appropriate answer
	 * 
	 * TODO: wire in all the responders 
	 * 
	 * @param query
	 * @return
	 */
	public String getResponse(final String query) {
		Preconditions.checkNotNull(query, "Query cannot be null.");
		
		return Optional.of(new BasicQueryAnalyzer(query))
				.map(BasicQueryAnalyzer::getQueryIntent)
				.map(INTENT_TO_RESPONDER_MAP::get)
				.filter(Objects::nonNull)
				.map(responder -> responder.getResponse(query))
				.orElse(ERROR_IN_PROCESSING_STRING);
	}
}
