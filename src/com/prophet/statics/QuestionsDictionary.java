package com.prophet.statics;

import java.util.Set;

import com.google.common.collect.ImmutableSet;

public class QuestionsDictionary {
	// Set of strings that can identify if this is a personal question 
	public static final Set<String> PERSONAL_QUESTION_DIRECTIVES = ImmutableSet.of(
		"you", 
		"your");
	
	public static final Set<String> WH_QUESTION_DIRECTIVE_SET = ImmutableSet.of(
		"what", 
		"who", 
		"when",
		"where",
		"how");
}
