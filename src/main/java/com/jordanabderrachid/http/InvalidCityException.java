package com.jordanabderrachid.http;

/**
 * This exception is thrown when the requested city is invalid.
 *
 * @author jordanabderrachid
 */
public class InvalidCityException extends Exception {

  public InvalidCityException(String message) {
    super(message);
  }
}
