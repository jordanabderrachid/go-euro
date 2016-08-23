package com.jordanabderrachid.csv;

import com.jordanabderrachid.http.RequesterTest;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class CSVWriterTest
  extends TestCase
{
  public CSVWriterTest(String testName) { super(testName); }

  public static Test suite() { return new TestSuite(RequesterTest.class); }

  public void testPlaceholder() {}
}
