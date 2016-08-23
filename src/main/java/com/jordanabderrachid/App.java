package com.jordanabderrachid;

import com.google.gson.JsonSyntaxException;

import com.jordanabderrachid.cli.Parser;
import com.jordanabderrachid.csv.CSVRecord;
import com.jordanabderrachid.csv.CSVWriter;
import com.jordanabderrachid.csv.WriteException;
import com.jordanabderrachid.http.BadResponseException;
import com.jordanabderrachid.http.ConnectionException;
import com.jordanabderrachid.http.InvalidCityException;
import com.jordanabderrachid.http.Requester;
import com.jordanabderrachid.model.Suggestion;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.List;

public class App 
{
  private static final String DEFAULT_OUTPUT_FILENAME = "./out.csv";

  public static void main(String[] args) {
    Logger logger = LogManager.getLogger(App.class);

    String city = Parser.parseCity(args);
    logger.info("Calling API for city : " + city);

    try {
      String responseBody = Requester.getSuggestions(city);
      List<CSVRecord> suggestions = Suggestion.fromJSON(responseBody);
      CSVWriter.toFile(DEFAULT_OUTPUT_FILENAME, suggestions);
    } catch (InvalidCityException e) {

    } catch (ConnectionException e) {

    } catch (BadResponseException e) {

    } catch (JsonSyntaxException e) {

    } catch (WriteException e) {

    }
  }
}
