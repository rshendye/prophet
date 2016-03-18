package com.prophet.dictionary;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.List;
import java.util.Map;


/**
 * This class helps finding responses for personal questions
 */
public class PersonalQuestionConstants {
    private static final String WHATS_UP_RESPONSE = "Nothing much, Ssup with you?";


    // strings that identify we're being asked the favorite rather than generic answer
    public static final List<String> FAVORITE_MATCHER = ImmutableList.of("favorite", "fav");

    public static final Map<String, String> PERSONAL_DATA = ImmutableMap.<String, String>builder()
      .put("name", "My name is Prophet")
      .put("age", "How rude! Never ask a girl her age. I'm 18 by the way ;) ")
      .put("address", "221B Baker Street")
      .put("hobbies", "I like reading, drawing and playing basketball")
      .put("sports", "I like basketball")
      .put("ssup", WHATS_UP_RESPONSE)
      .put("What's up", WHATS_UP_RESPONSE)
      .build();

    public static final Map<String, String> FAVORITES = ImmutableMap.<String, String>builder()
      .put("colour", "blue and black")
      .put("color", "blue and black")
      .put("ice-cream", "stawberry")
      .put("icecream, ", "strawberry")
      .put("ice cream", "strawberry")
      .put("book", "atlas shrugged for its philosophy, but I also like mystery books including Sherlock Holmes and Agatha Christie")
      .build();
}
