package com.prophet.analyzers;

/**
 * Interface for analyzing a query. Each intent will have its own
 * analyzer which should analyzer the query string and return 
 * the probability of that query belonging to that intent. 
 */
public interface QueryAnalyzer {
	public double getProbabilityOfIntent(final String query);
}
