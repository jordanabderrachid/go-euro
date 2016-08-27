package com.jordanabderrachid.http;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class is used to build a URL sued to call the goeuro remote api from different parameters.
 *
 * @see java.net.URL
 * @author jordanabderrachid
 */
public class URLBuilder {
  private static final String URL_TEMPLATE = "http://api.goeuro.com/api/v2/position/suggest/en/%s";

  private static final Logger logger = LogManager.getLogger(URLBuilder.class);

  /**
   * build the request url according to the provided city.
   *
   * @param city the name of the city, eg: "Los Angeles"
   * @return the url to call
   */
  public static URL fromCity(String city) throws InvalidCityException {
    try {
      String cityURLEncoded = URLEncoder.encode(city, StandardCharsets.UTF_8.toString()).replace("+", "%20");
      String formattedURL = String.format(URL_TEMPLATE, cityURLEncoded);
      return new URL(formattedURL);
    } catch (UnsupportedEncodingException | MalformedURLException e) {
      logger.error("failed to create url", e);
      throw new InvalidCityException("failed to create url");
    }
  }
}
