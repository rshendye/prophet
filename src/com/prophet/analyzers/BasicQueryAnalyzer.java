package com.prophet.analyzers;

import com.google.common.collect.ImmutableMap;
import com.prophet.datatypes.Pair;
import com.prophet.dictionary.Intent;
import java.util.AbstractMap;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Stream;

import static com.prophet.dictionary.Intent.*;

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
				.put(Intent.QUESTION, new QuestionAnalyzer())
				.put(Intent.STATEMENT, new StatementAnalyzer());

		
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
				.map(intent -> new Pair<>(intent, INTENT_TO_ANALYZER_MAP.get(intent)))
				.filter(x -> x.getFirst() != null && x.getSecond() != null)
				.map(x -> new Pair<>(x.getFirst(), x.getSecond().getProbabilityOfIntent(query)))
				.filter(pair -> pair.getSecond() > 0)
				.max(Comparator.comparing(x -> x.getSecond()))
				.map(Pair::getFirst)
				.orElse(DEFAULT);		// default to conversation starter if we cannot predict the intent deterministically
	}
}

