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

  private static final int OK_EXIT_CODE = 0;

  private static final int KO_EXIT_CODE = 1;

  public static void main(String[] args) {
    Logger logger = LogManager.getLogger(App.class);

    logger.info("GoEuro CLI v{}", App.class.getPackage().getImplementationVersion());

    String city = Parser.parseCity(args);
    logger.info("calling API for city {}", city);

    try {
      String responseBody = Requester.getSuggestions(city);
      List<CSVRecord> suggestions = Suggestion.fromJSON(responseBody);

      if (suggestions.isEmpty()) {
        logger.info("no results found");
        System.exit(OK_EXIT_CODE);
      }

      String outputFilename = CSVWriter.toFile(DEFAULT_OUTPUT_FILENAME, suggestions);

      logger.info("wrote {} records to file {}", suggestions.size(), outputFilename);

      System.exit(OK_EXIT_CODE);
    } catch (InvalidCityException e) {
      System.exit(KO_EXIT_CODE);
    } catch (ConnectionException e) {
      logger.error("failed to connect to the API");
      System.exit(KO_EXIT_CODE);
    } catch (BadResponseException e) {
      logger.error("API returns an incorrect response");
      System.exit(KO_EXIT_CODE);
    } catch (JsonSyntaxException e) {
      logger.error("failed to parse API response");
      System.exit(KO_EXIT_CODE);
    } catch (WriteException e) {
      logger.error("failed to write output file");
      System.exit(KO_EXIT_CODE);
    }
  }
}
