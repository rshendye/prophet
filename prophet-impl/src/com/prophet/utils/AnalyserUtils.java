package com.prophet.utils;

import java.util.Collection;
import java.util.Comparator;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;


/**
 * Utility class for all Analyzers
 */
public class AnalyserUtils {
  private AnalyserUtils() {
    // restrict instantiation
  }

  /**
   * Determines if we have an exact match for a query in the given collection
   * Returns 1 if the query exists in the database else returns 0.
   *
   * @param matchDeterminer predicate which determines the match
   * @param dataSet Collection over which we try to find a perfect match
   * @return probabilty 1 if there exists an exact match, 0 otherwise.
   */
  public static <T> double getProbabilityOfExactMatch(final Collection<T> dataSet,
      final Predicate<T> matchDeterminer) {
    return dataSet.stream()
        .filter(matchDeterminer)
        .map(LambdaUtils.constantFunction(1d))
        .findAny()
        .orElse(0d);
  }

  /**
   * Determines if the query starts with a known data point.
   * Returns value of maximum match among all data sets.
   *
   * @param dataSet against which the query is to be matched
   * @param matchDeterminer predicate determining if there has been a match
   * @param probabilityMapper function determining what the probability should be for a given match
   * @return max Probability with which the query matches a point in the data set
   */
  public static <T> double getProbabilityOfPartialMatch(final Set<T> dataSet,
      final Predicate<T> matchDeterminer, final Function<T, Double> probabilityMapper) {
    return dataSet.stream()
        .filter(matchDeterminer)
        .map(probabilityMapper)
        .max(Comparator.naturalOrder())
        .orElse(0d);
  }
}
