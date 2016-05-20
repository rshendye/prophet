package com.prophet.responders;

import java.util.Optional;

import static com.prophet.dictionary.Constants.DECLINE_PERSONAL_ANSWER;
import static com.prophet.dictionary.PersonalQuestionConstants.FAVORITES;
import static com.prophet.dictionary.PersonalQuestionConstants.FAVORITE_MATCHER;
import static com.prophet.dictionary.PersonalQuestionConstants.PERSONAL_DATA;


/**
 * This class returns an appropriate response for a personal question. We always assume 
 * that the given question is categorized to be a personal question. It does not 
 * perform any validations of this fact.
 * 
 * @author rshendye
 *
 */
public class PersonalQuestionResponder implements Responder {

  @Override
  public String getResponse(final String personalQuestion) {
    String lowerCaseQuestion = personalQuestion.toLowerCase();

    // check if this is a favorite question.
    // 'fav sportsman' will be matched before substring 'sports' which is generic
    Optional<String> favQuestion = FAVORITE_MATCHER.stream()
        .filter(lowerCaseQuestion::contains)
        .flatMap(fav -> FAVORITES.entrySet().stream())
        .distinct()         // there should be a better way of doing this
        .filter(entry -> lowerCaseQuestion.contains(entry.getKey()))
        .map(entry -> String.format("My favorite %s is %s", entry.getKey(), entry.getValue()))
        .findAny();

    if (favQuestion.isPresent()) {
      return favQuestion.get();
    }

    return PERSONAL_DATA.keySet()
        .stream()
        .filter(lowerCaseQuestion::contains)
        .map(PERSONAL_DATA::get)
        .findAny()
        .orElse(DECLINE_PERSONAL_ANSWER);
  }
}
