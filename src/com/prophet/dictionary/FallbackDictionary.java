package com.prophet.dictionary;

import java.util.Set;

import com.google.common.collect.ImmutableSet;

/**
 * Dictionary for fall back questions
 * @author rshendye
 *
 */
public class FallbackDictionary {
  
  /**
   * Questions that can be used as conversation starters 
   */
  public static final Set<String> CONVERSATION_STARTER_QUESTIONS = ImmutableSet.<String>of(
      "How are you doing? ", 
      "How have you been? ", 
      "How's your day?");
  
  /**
   * Prefixes which can be appended to the conversation starters
   */
  public static final Set<String> STARTER_PREFIXES = ImmutableSet.<String>of(
      "So, ", 
      "Anyway.. ", 
      "By the way, ");
}
