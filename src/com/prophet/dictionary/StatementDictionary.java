package com.prophet.dictionary;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import java.util.Map;
import java.util.Set;


/**
 * Dictionary for {@link com.prophet.analyzers.StatementAnalyzer}
 */
public class StatementDictionary {
  public static final Set<String> THANKS_KEYWORDS = ImmutableSet.of("Thanks", "Thank");
  public static final Set<String> THANKS_RESPONSES = ImmutableSet.of(
      "You're welcome!",
      "It's been my pleasure :)",
      "Pleasure is all mine",
      "Happy to help!");

  public static final Map<Set<String>, Set<String>> STATEMENT_RESPONSES =
      ImmutableMap.<Set<String>, Set<String>>builder()
        .put(THANKS_KEYWORDS, THANKS_RESPONSES)
        .build();
}
