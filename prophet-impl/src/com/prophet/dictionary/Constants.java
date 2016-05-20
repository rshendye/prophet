package com.prophet.dictionary;

import com.google.common.collect.ImmutableSet;
import java.util.Set;


public class Constants {
	
	// punctuations
	public static final String SPACE = " ";
	public static final String EXCLAMATION_MARK = "!";
	public static final String QUESTION_MARK = "?";
	public static final String PERIOD = ".";

	// default responses
	public static final String STARTER_STRING = "Welcome!";
	public static final String DECLINE_PERSONAL_ANSWER = "That's way too personal for me to answer!";
	public static final String CLEAN_SPEECH_WARNING = "Please do not use such language! Did you forget your etiquette?";
	
	// Errors 
	public static final String EXIT = "exit";
	public static final String EXIT_RESPONSE = "Bye Bye!";
	public static final String ERROR_IN_PROCESSING_STRING = "Error in processing input. try again!";
	public static final String ERROR_IN_GENRATING_RESPONSE = "Erorr in generating response";

	public static final Set<String> SWEAR_WORDS = ImmutableSet.of(
			"fuck",
			"bitch",
			"ass",
			"asshole",
			"swine",
			"slut",
			"prostitute",
			"jerk"
	);
}
