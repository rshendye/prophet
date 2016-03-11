package com.prophet.analyzers;

import static com.prophet.statics.GreetingsDictionary.*;

import java.util.Comparator;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


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
		Callable<Double> exactMatchChecker = getExactMatchCallable(query);
		
		// check for partial matches 
		Callable<Double> partialMatchChecker = getPartialMatchCallable(query);
		
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
	
	/**
	 * Determines if we have an exact match for the query. 
	 * Returns 1 if the query exists in the greetings database else returns 0.
	 * @param query
	 * @return
	 */
	private Callable<Double> getExactMatchCallable(final String query) {
		return new Callable<Double>() {

			@Override
			public Double call() throws Exception {
				return STD_GREETINGS.stream()
					.map(query::equalsIgnoreCase)	
					.filter(Boolean.TRUE::equals)
					.map(isGreeting -> 1.0d)
					.findAny()
					.orElse(0d);
			}
		};
	}

	/**
	 * Determines if the query starts with a greeting. The probability of match 
	 * depends on how much of the query is a greeting. Returns value of maximum match
	 * among all std greetings.
	 *  
	 * @param query
	 * @return
	 */
	private Callable<Double> getPartialMatchCallable(final String query) {
		return new Callable<Double>() {

			@Override
			public Double call() throws Exception {
				return STD_GREETINGS.stream()
					.filter(greeting -> query.toLowerCase().contains(greeting.toLowerCase()))
					.map(stdGreeting -> (double)query.length() / stdGreeting.length())
					.max(Comparator.naturalOrder())
					.orElse(0d);
			}
		};
	}
}
