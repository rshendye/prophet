package com.prophet.responders;

import com.prophet.datatypes.Pair;
import java.util.Collection;
import java.util.stream.Stream;

import static com.prophet.dictionary.StatementDictionary.STATEMENT_RESPONSES;
import static com.prophet.dictionary.StatementDictionary.THANKS_KEYWORDS;
import static com.prophet.utils.AnalyserUtils.getProbabilityOfPartialMatch;
import static com.prophet.utils.StreamUtils.mapToEntryStream;


/**
 * Generates responses for statements
 */
public class StatementResponder implements Responder {

  @Override
  public String getResponse(String input) {
    return mapToEntryStream(STATEMENT_RESPONSES)
        .map(entry -> new Pair<>(entry.getKey(), getProbabilityOfPartialMatch(THANKS_KEYWORDS,
            (t) -> input.contains(t.toLowerCase()),
            (t) -> (double) input.length() / t.length())))
        .max((p1, p2) -> p1.getSecond().compareTo(p2.getSecond()))
        .map(pair -> STATEMENT_RESPONSES.get(pair.getFirst()))
        .map(Collection::stream)
        .orElseGet(Stream::empty)
        .findAny()
        .orElse("Error in producing response for statement");
  }
}
