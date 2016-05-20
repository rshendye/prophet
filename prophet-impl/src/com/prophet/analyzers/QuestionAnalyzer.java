package com.prophet.analyzers;

import static com.prophet.dictionary.Constants.QUESTION_MARK;
import static com.prophet.utils.LambdaUtils.constantFunction;
import static com.prophet.utils.OptionalUtils.getOptional;


/**
 * This class determines if the given query is a question. A Question can be of the following types:
 * 1. Personal question to com.prophet
 * 2. Command or order to fetch some information. 
 * 
 * @author rshendye
 *
 */
public class QuestionAnalyzer implements QueryAnalyzer {

	/**
	 * Returns the probability with which the query can be a question asked to com.prophet
	 * 
	 * For now we simply check if the query ends with a question mark. 
	 */
	@Override
	public double getProbabilityOfIntent(final String query) {
		return getOptional(query)
			.filter(s -> s.endsWith(QUESTION_MARK))
			.map(constantFunction(1d))
			.orElse(0d);
	}

}
