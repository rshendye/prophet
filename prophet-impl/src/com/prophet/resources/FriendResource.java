package com.prophet.resources;

import com.prophet.services.InterpreterService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.commons.lang.StringUtils;

import static com.prophet.dictionary.Constants.*;


/**
 * This resource should be used when you want to chat with Prophet as a person,
 * and strike up a conversation.
 */
public class FriendResource {
	
	public static void main(String args[]) throws IOException {
		// TODO: needs to be injected
		final InterpreterService interpreterService = new InterpreterService();
		
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(STARTER_STRING);
		while (true) {
			String query = bufferedReader.readLine();

      // exit case
			if (EXIT.equalsIgnoreCase(query)) {
				// TODO: return a random return response 
				System.out.println(EXIT_RESPONSE);
				break;
			}

      // empty query
      if (StringUtils.isEmpty(query)) {
				System.out.println("C'mon, you gotta say something!");
			}

      // check for swear words
      boolean hasBadWords = SWEAR_WORDS.stream()
          .map(s -> s.equalsIgnoreCase(query))
          .filter(Boolean.TRUE::equals)
          .findAny()
          .isPresent();

      if (hasBadWords) {
        System.out.println(CLEAN_SPEECH_WARNING);
      }

      // lose the case to simplify string matching
      System.out.println(interpreterService.processQuery(query.toLowerCase()));
		}
	}
}
