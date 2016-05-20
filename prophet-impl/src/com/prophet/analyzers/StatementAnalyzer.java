package com.prophet.analyzers;

import com.prophet.enums.Intent;
import java.util.Comparator;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static com.prophet.dictionary.StatementDictionary.STATEMENT_RESPONSES;
import static com.prophet.dictionary.StatementDictionary.THANKS_KEYWORDS;
import static com.prophet.utils.AnalyserUtils.getProbabilityOfExactMatch;
import static com.prophet.utils.AnalyserUtils.getProbabilityOfPartialMatch;


/**
 * Generates a response for intent of types {@link Intent}.STATEMENT
 */
public class StatementAnalyzer implements QueryAnalyzer {

  ExecutorService _executorService;

  public StatementAnalyzer() {
    _executorService = Executors.newCachedThreadPool();
  }

  /**
   * Returns 1 if the query exactly matches one of the expected responses,
   * else the probability returned is proportional to the portion matched in the
   * query.
   * @param query
   * @return
   */
  @Override
  public double getProbabilityOfIntent(final String query) {
    //TODO: Add context and return the matched values in it. NO point calculating this again and again.
    // query matches a word in dictionary
    Callable<Double> exactMatchChecker = () -> STATEMENT_RESPONSES.entrySet()
        .stream()
        .map(entry -> getProbabilityOfExactMatch(entry.getKey(), (t) -> query.equalsIgnoreCase(t)))
        .max(Comparator.naturalOrder())
        .orElse(0d);

    // check for partial matches
    Callable<Double> partialMatchChecker = () ->  STATEMENT_RESPONSES.entrySet()
        .stream()
        .map(entry -> getProbabilityOfPartialMatch(THANKS_KEYWORDS,
            (t) -> query.contains(t.toLowerCase()),
            (t) -> (double) query.length() / t.length()))
        .max(Comparator.naturalOrder())
        .orElse(0d);

    // TODO: Do we need to check both? just partial should be enough
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
