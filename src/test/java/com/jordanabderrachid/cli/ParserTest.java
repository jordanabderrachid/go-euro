package com.jordanabderrachid.cli;

import static org.junit.Assert.*;
import org.junit.Test;

public class ParserTest {
  /**
   * test static `parseCity` method. This method parse the CLI arguments and extract the name of the requested city.
   */
  @Test
  public void testParseCity() {
    assertEquals(Parser.parseCity(new String[]{}), "");
    assertEquals(Parser.parseCity(new String[]{"Berlin"}), "Berlin");
    assertEquals(Parser.parseCity(new String[]{"Los", "Angeles"}), "Los Angeles");
  }
}
