package com.prophet.analyzers;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

import com.google.common.collect.ImmutableMap;
import com.prophet.statics.Intent;

/**
 * Simple class that aims at analyzing the type of query
 */
public class BasicQueryAnalyzer {
	// TODO: inject these values via config 
	// or have the 
	private static final Map<Intent, QueryAnalyzer> INTENT_TO_ANALYZER_MAP;
	static {
		ImmutableMap.Builder<Intent, QueryAnalyzer> intentToAnalyzerBuilder = ImmutableMap.<Intent, QueryAnalyzer>builder()
			.put(Intent.GREETINGS, new GreetingsAnalyzer())
			.put(Intent.QUESTION, new QuestionAnalyzer());
		
		INTENT_TO_ANALYZER_MAP = intentToAnalyzerBuilder.build();
	}
	
	private final String query;
	
	public BasicQueryAnalyzer(String query) {
		this.query = query;
	}
	
	/**
	 * Checks the probability of each intent and returns the intent 
	 * with highest probability for current query
	 * @return null if there has been an error
	 */
	public Intent getQueryIntent() {
		return Stream.of(Intent.values())
		  .map(intent -> new AbstractMap.SimpleEntry<Intent, QueryAnalyzer>(intent, INTENT_TO_ANALYZER_MAP.get(intent)))
		  .filter(x -> x.getKey() != null && x.getValue() != null)
		  .map(x -> new AbstractMap.SimpleEntry<Intent, Double>(x.getKey(), x.getValue().getProbabilityOfIntent(query)))
		  .filter(entry -> entry.getValue() > 0)
		  .max(Comparator.comparing(x -> x.getValue()))
		  .map(AbstractMap.SimpleEntry::getKey)
		  .orElse(null);		// default to conversation starter if we cannot predict the intent deterministically
	}
}

