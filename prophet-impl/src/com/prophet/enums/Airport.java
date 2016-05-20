package com.prophet.enums;

/**
 * String constants for airport acronymns
 */
public enum Airport {
  SFO("San Francisco"),
  LAX("Los Angeles"),
  EWR("Liberty Airprt");

  private String name;

  Airport(String name) {
    this.name = name;
  }
}
