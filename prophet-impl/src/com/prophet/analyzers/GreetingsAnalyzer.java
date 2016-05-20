package com.prophet.analyzers;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static com.prophet.dictionary.GreetingsDictionary.STD_GREETINGS;
import static com.prophet.utils.AnalyserUtils.getProbabilityOfExactMatch;
import static com.prophet.utils.AnalyserUtils.getProbabilityOfPartialMatch;


public class GreetingsAnalyzer implements QueryAnalyzer {
	ExecutorService _executorService;
	
	public GreetingsAnalyzer() {
		_executorService = Executors.newCachedThreadPool();
	}

	/**
	 * Returns the probability with which the query has a greetings intent. Tries to 
	 * check if there is a perfect match with the dictionary keywords or a partial match. 
	 * In case of perfect match, probability is 1. In case of partial matches, the 
	 * probability returned is proportional to how much a std greeting matches the query. 
	 * 
	 * @return returns the probability of match or -1 if there was an error
	 */
	@Override
	public double getProbabilityOfIntent(final String query) {
		// query matches a word in dictionary
		Callable<Double> exactMatchChecker = () -> getProbabilityOfExactMatch(STD_GREETINGS,
				(t) -> query.equalsIgnoreCase(t));

		// check for partial matches
		Callable<Double> partialMatchChecker = () -> getProbabilityOfPartialMatch(STD_GREETINGS,
				(t) -> query.contains(t.toLowerCase()), (t) -> (double)query.length() / t.length());
		
		Future<Double> exactMatchFuture = _executorService.submit(exactMatchChecker);
		Future<Double> partialMatchFuture = _executorService.submit(partialMatchChecker);
		
		try {
			return Math.max(exactMatchFuture.get(), partialMatchFuture.get());
		} catch (InterruptedException | ExecutionException e) {
		    System.out.println("Error in fetching match values for greetings query. ");
			e.printStackTrace();
			return -1; 
		}
	}
}
