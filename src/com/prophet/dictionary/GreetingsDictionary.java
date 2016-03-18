package com.prophet.dictionary;

import java.util.Set;

import com.google.common.collect.ImmutableSet;

/**
 * Class to store the constants and dictionary for greetings 
 */
public class GreetingsDictionary {

	// greeting constants without punctuation
	public static final Set<String> STD_GREETINGS = ImmutableSet.<String>builder()
	    .add("Hi")
	    .add("Hey")
			.add("Hello")
			.add("Aloha")
			.add("Hola")
			.add("Ssup")
			.add("Whats' up?")
			.build();
	
	// polite questions
	public static final Set<String> POLITE_QUESTIONS = ImmutableSet.<String>builder()
		.add(" How are you ?")
		.add(" Nice to meet you")
		.add(" Ssup!")
		.add(" What's on your mind ?")
		.add(" How may I help you today ?")
		.build();
}
