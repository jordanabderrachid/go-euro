package com.jordanabderrachid.http;

import org.junit.Test;

import static org.junit.Assert.*;

import java.net.URL;

public class URLBuilderTest {

  @Test
  public void testFromCityWithoutSpace() {
    try {
      URL url = URLBuilder.fromCity("Berlin");
      assertEquals("http://api.goeuro.com/api/v2/position/suggest/en/Berlin", url.toString());
    } catch (InvalidCityException e) {
      fail("should not throw invalid city exception");
    }
  }

  @Test
  public void testFromCityWithSpace() {
    try {
      URL url = URLBuilder.fromCity("Los Angeles");
      assertEquals("http://api.goeuro.com/api/v2/position/suggest/en/Los%20Angeles", url.toString());
    } catch (InvalidCityException e) {
      fail("should not throw invalid city exception");
    }
  }
}
