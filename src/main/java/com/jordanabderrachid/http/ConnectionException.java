package com.jordanabderrachid.http;

/**
 * This exception is thrown when the connection to the remote api fails.
 *
 * @author jordanabderrachid
 */
public class ConnectionException extends Exception {

  public ConnectionException(String message) {
    super(message);
  }
}
