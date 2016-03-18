package com.prophet.analyzers;

import static com.prophet.dictionary.Constants.*;
import static com.prophet.utils.OptionalUtils.getOptional;
import static com.prophet.utils.LambdaUtils.constantFunction;

/**
 * This class determines if the given query is a question. A Question can be of the following types:
 * 1. Personal question to prophet
 * 2. Command or order to fetch some information. 
 * 
 * @author rshendye
 *
 */
public class QuestionAnalyzer implements QueryAnalyzer {

	/**
	 * Returns the probability with which the query can be a question asked to prophet 
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
