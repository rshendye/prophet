package com.prophet.responders;

/**
 * Base class for all responders.
 */
public interface Responder {
	/**
	 * This method takes in user's input and returns an appropriate response
	 * TODO: Change input type to something more complex like a list of 
	 *       tokens 
	 * 
	 * @param input
	 * @return
	 */
	public String getResponse(final String input);
}
