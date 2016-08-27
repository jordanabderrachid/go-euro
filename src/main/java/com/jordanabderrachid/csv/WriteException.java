package com.jordanabderrachid.csv;

/**
 * This exception is thrown when the creation and the write to the CSV file fails.
 *
 * @author jordanabderrachid
 */
public class WriteException extends Exception {

  public WriteException(String message) {
    super(message);
  }
}
