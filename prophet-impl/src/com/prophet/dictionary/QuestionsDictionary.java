package com.prophet.dictionary;

import com.google.common.collect.ImmutableSet;
import java.util.Set;


public class QuestionsDictionary {
	// Set of strings that can identify if this is a personal question 
	public static final Set<String> PERSONAL_QUESTION_DIRECTIVES = ImmutableSet.of(
			"you",
			"your",
			"what's up",
			"ssup");
	
	public static final Set<String> WH_QUESTION_DIRECTIVE_SET = ImmutableSet.of(
			"what",
			"what's",
			"who",
			"when",
			"where",
			"how",
			"who's",
			"who'r");
}
