package com.jordanabderrachid.csv;

/**
 * Represents an object that can be serialized to a csv record.
 *
 * @author jordanabderrachid
 */
public interface CSVRecord {

  /**
   * Get the csv header.
   *
   * @return the csv header
   */
  String getCSVHeader();

  /**
   * Get the csv record in string format.
   *
   * @return the csv record in string format
   */
  String getCSVString();
}
