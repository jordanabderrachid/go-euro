package com.jordanabderrachid.cli;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ParserTest
  extends TestCase
{
  /**
   * Create the test case
   *
   * @param testName name of the test case
   */
  public ParserTest(String testName) { super(testName); }

  /**
   *
   * @return the suite of test being tested
   */
  public static Test suite() { return new TestSuite( ParserTest.class); }

  /**
   * test static `parseCity` method. This method parse the CLI arguments and extract the name of the requested city.
   */
  public void testParseCity() {
    assertEquals(Parser.parseCity(new String[]{}), "");
    assertEquals(Parser.parseCity(new String[]{"Berlin"}), "Berlin");
    assertEquals(Parser.parseCity(new String[]{"Los", "Angeles"}), "Los Angeles");
  }
}
