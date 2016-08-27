package com.jordanabderrachid.http;

/**
 * This exception is thrown when the api responds with an unsupported response, such as a bad content type.
 *
 * @author jordanabderrachid
 */
public class BadResponseException extends Exception {

  public BadResponseException(String message) {
    super(message);
  }
}
