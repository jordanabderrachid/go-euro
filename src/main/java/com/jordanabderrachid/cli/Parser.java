package com.jordanabderrachid.cli;

import static java.lang.String.join;

/**
 * This class is used to parse CLI arguments.
 *
 * @author jordanabderrachid
 */
public class Parser {

  /**
   * Parse the CLI arguments and returns the name of the requested city.
   *
   * @param args the CLI arguments
   * @return the name of the requested city
   */
  public static String parseCity(String[] args) {
    return String.join(" ", args);
  }
}

