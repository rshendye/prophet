package com.prophet.responders;

import com.google.common.collect.ImmutableMap;
import com.prophet.datatypes.Pair;
import com.prophet.enums.QuestionType;
import java.util.Map;
import java.util.Optional;
import org.apache.commons.lang.StringUtils;

import static com.prophet.dictionary.Constants.ERROR_IN_GENRATING_RESPONSE;
import static com.prophet.dictionary.Constants.PERIOD;
import static com.prophet.dictionary.Constants.QUESTION_MARK;
import static com.prophet.enums.QuestionType.COMMAND;
import static com.prophet.enums.QuestionType.DEFAULT;
import static com.prophet.enums.QuestionType.PERSONAL_QUESTION;
import static com.prophet.dictionary.QuestionsDictionary.PERSONAL_QUESTION_DIRECTIVES;
import static com.prophet.dictionary.QuestionsDictionary.WH_QUESTION_DIRECTIVE_SET;
import static com.prophet.utils.LambdaUtils.constantFunction;
import static com.prophet.utils.OptionalUtils.getOptional;


/**
 * Class to determine the response when a question is asked 
 * 1. Strips the question part out of the input. 
 * 2. Identifies what kind of question it it and delegates the task of finding response 
 *    to the appropriate responder. 
 *    a. The question can be a personal one e.g how are you?
 *    b. Or a command to fetch some information e.g What is the time?
 * @author rshendye
 *
 */
public class BasicQuestionResponder implements Responder {
  // TODO: see if this can be set via spring configs. 
  private static final Map<QuestionType, Responder> QUESTION_TYPE_TO_RESPONDER_MAP; 
  static {
    QUESTION_TYPE_TO_RESPONDER_MAP = ImmutableMap.<QuestionType, Responder>builder()
        .put(PERSONAL_QUESTION, new PersonalQuestionResponder())
        .put(COMMAND, new CommandResponder())
        .put(DEFAULT, new FallbackResponder())
        .build();
    
  }

	@Override
	public String getResponse(final String input) {
		return getOptional(input)
			.map(this::extractQuestionFromInput)
			.map(question -> new Pair<>(question, getQuestionType(question)))
			.map(pair -> new Pair<>(pair.getFirst(), QUESTION_TYPE_TO_RESPONDER_MAP.get(pair.getSecond())))
			.filter(Pair::hasSecond)
			.map(pair -> pair.getSecond().getResponse(pair.getFirst()))
			.orElse(ERROR_IN_GENRATING_RESPONSE);
	}

	/**
	 * filters the actual question from the input and strips the other part. 
	 * eg I'm fine, thank you. How are you? will return "How are you"
	 * 
	 * @param input which is a question, and must end with question mark 
	 * @return the actual question being asked. returns empty string in case of errors
	 */
	private String extractQuestionFromInput(String input) {
		int indexOfPeriod = input.lastIndexOf(PERIOD);
		
		return getOptional(input)
			.filter(str -> str.contains(QUESTION_MARK))
			.map(str -> str.substring(0, str.indexOf(QUESTION_MARK) + 1))	
			.map(str -> indexOfPeriod == -1 ? str : str.substring(indexOfPeriod + 1))			
			.map(String::trim)
			.orElse(StringUtils.EMPTY);
	}
	
	/**
	 * Determines the type of question being asked. 
	 * 
	 * TODO: consider adding context here which can be used to generate the response. 
	 * 
	 * @param question
	 * @return Question Type that the question most likely belongs to
	 */
	private QuestionType getQuestionType(String question) {
		// check if it is a personal Question
		Optional<Boolean> personalQuestionOptional = PERSONAL_QUESTION_DIRECTIVES.stream()
			.map(question::contains)
			.filter(Boolean.TRUE::equals)
			.findAny();
		
		if (personalQuestionOptional.isPresent()) {
			return PERSONAL_QUESTION;
		}
		
		// check if it is a command. Verify that a wh-question is asked
		return WH_QUESTION_DIRECTIVE_SET.stream()
			.map(question::contains)
			.filter(Boolean.TRUE::equals)
			.findAny()
			.map(constantFunction(COMMAND))
			.orElse(DEFAULT);				// default when we fail to determine the question type
	}
}
