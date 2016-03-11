package com.prophet.responders;

import static com.prophet.statics.FallbackDictionary.*;

import static com.prophet.utils.RandomUtils.*;
import static com.prophet.utils.LambdaUtils.*;
import static com.prophet.statics.Constants.*;

/**
 * This responder generates a fallback response to be used as a conversation filler
 * when we fail to identify the intent or categorize the question being asked and cannot 
 * generate a response with certainty. 
 * 
 * @author rshendye
 *
 */
public class FallbackResponder implements Responder {
  private static final int PROBABLITY_OF_ADDING_PREFIX = 25;

  @Override
  public String getResponse(String input) {
    StringBuilder responseBuiler = new StringBuilder();
    performWithProbability(PROBABLITY_OF_ADDING_PREFIX, 
        (StringBuilder s) -> s.append(STARTER_PREFIXES.stream().findAny().get()), 
        responseBuiler);
        
    getStringBuilderAppender(SPACE, CONVERSATION_STARTER_QUESTIONS.stream().findAny().get())
      .apply(responseBuiler);
    
    return responseBuiler.toString();
  }

}
