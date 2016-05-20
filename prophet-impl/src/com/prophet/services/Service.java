package com.prophet.services;

/**
 * This interface is responsible for accepting an input from the user and providing an appropriate response.
 * The input can be specific like a task in mind, or more generic like a greeting or command.
 *
 * Every type of service a unique technique to provide the response.
 */
public interface Service {

  /**
   * Process the input query and return an output.
   * @param query
   * @return
   */
  String processQuery(final String query);
}
