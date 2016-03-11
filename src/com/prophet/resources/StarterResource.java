package com.prophet.resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.prophet.services.InterpreterService;

import static com.prophet.statics.Constants.*;

/**
 * The starting point for interaction
 */
public class StarterResource {
	
	public static void main(String args[]) throws IOException {
		// TODO: needs to be injected
		final InterpreterService interpreterService = new InterpreterService();
		
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Welcome!");
		while (true) {
			String query = bufferedReader.readLine();
			if (EXIT.equalsIgnoreCase(query)) {
				// TODO: return a random return response 
				System.out.println(EXIT_RESPONSE);
				break;
			} else {
				System.out.println(interpreterService.getResponse(query));
			}
		}
	}
}
