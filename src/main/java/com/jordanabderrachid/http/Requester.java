package com.jordanabderrachid.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;

public class Requester {

  private static final String URL_TEMPLATE = "http://api.goeuro.com/api/v2/position/suggest/en/%s";

  private static final int CONNECT_TIMEOUT = 1000; // 1 sec.
  private static final int READ_TIMEOUT = 1000; // 1 sec.

  private static final String ACCEPT_HEADER_KEY = "Accept";
  private static final String EXPECTED_CONTENT_TYPE = "application/json;charset=UTF-8";

  public static String getSuggestions(String city)
    throws InvalidCityException, ConnectionException, BadResponseException
  {
    try {
      URL url = buildURL(city);

      URLConnection connection = url.openConnection();

      connection.setConnectTimeout(CONNECT_TIMEOUT);
      connection.setReadTimeout(READ_TIMEOUT);

      connection.setRequestProperty(ACCEPT_HEADER_KEY, EXPECTED_CONTENT_TYPE);
      connection.setRequestProperty("User-Agent", "curl/7.43.0");

      connection.connect();

      if (!connection.getContentType().equals(EXPECTED_CONTENT_TYPE)) {
        throw new BadResponseException("bad response content type");
      }

      InputStream is = connection.getInputStream();
      String body = IOUtils.toString(is, StandardCharsets.UTF_8);
      is.close();

      return body;
    } catch (InvalidCityException e) {
      throw e;
    } catch (IOException e) {
      // TODO log
      throw new ConnectionException("failed to connect to remote API");
    }
  }

  /**
   * build the request url according to the provided city.
   * @param city the name of the city, eg: "Los Angeles"
   * @return the url to call
   */
  private static URL buildURL(String city) throws InvalidCityException {
    try {
      String cityURLEncoded = URLEncoder.encode(city, StandardCharsets.UTF_8.toString()).replace("+", "%20");
      String stringFormattedURL = String.format(URL_TEMPLATE, cityURLEncoded);
      URL url = new URL(stringFormattedURL);

      return url;
    } catch (UnsupportedEncodingException | MalformedURLException e) {
      // TODO log error
      throw new InvalidCityException("failed to create url");
    }
  }
}
