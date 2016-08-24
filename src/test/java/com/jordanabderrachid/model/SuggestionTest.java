package com.jordanabderrachid.model;

import com.google.gson.JsonSyntaxException;
import com.jordanabderrachid.csv.CSVRecord;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SuggestionTest {

  @Test
  public void testGetCSVHeader() {
    Suggestion suggestion = new Suggestion();
    assertEquals(suggestion.getCSVHeader(), "_id,name,type,latitude,longitude");
  }

  @Test
  public void testGetCSVString() {
    String rawJSON = "[{\"_id\":379604,\"name\":\"Lyon\",\"type\":\"location\",\"geo_position\":{\"latitude\":45.748460,\"longitude\":4.846710}}]";
    List<CSVRecord> suggestions = Suggestion.fromJSON(rawJSON);

    assertEquals(suggestions.get(0).getCSVString(), "379604,Lyon,location,45.748460,4.846710");
  }

  @Test(expected = JsonSyntaxException.class)
  public void testFailJSONParsing() {
    String rawJSON = "foo";

    Suggestion.fromJSON(rawJSON);
  }

  @Test
  public void testFromJSONEmptyArray() {
    String rawJSON = "[]";
    List<CSVRecord> suggestions = Suggestion.fromJSON(rawJSON);

    assertTrue(suggestions.isEmpty());
  }

  @Test
  public void testFromJSONSingleObject() {
    String rawJSON = "[{\"_id\":379604,\"name\":\"Lyon\",\"type\":\"location\",\"geo_position\":{\"latitude\":45.748460,\"longitude\":4.846710}}]";
    Suggestion suggestion = (Suggestion)Suggestion.fromJSON(rawJSON).get(0);

    assertEquals(suggestion.getId(), 379604);
    assertEquals(suggestion.getName(), "Lyon");
    assertEquals(suggestion.getType(), "location");
    assertEquals(suggestion.getLatitude(), 45.748460, 0.0000001);
    assertEquals(suggestion.getLongitude(), 4.846710, 0.0000001);
  }
}
