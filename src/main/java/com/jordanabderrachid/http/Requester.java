package com.jordanabderrachid.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

import org.apache.commons.io.IOUtils;

public class Requester {
  private static final int CONNECT_TIMEOUT = 1000; // 1 sec.
  private static final int READ_TIMEOUT = 1000; // 1 sec.

  private static final String ACCEPT_HEADER_KEY = "Accept";
  private static final String EXPECTED_CONTENT_TYPE = "application/json;charset=UTF-8";

  private static final String USER_AGENT_HEADER_KEY = "User-Agent";
  private static final String USER_AGENT_HEADER_FORMAT = "go-euro-cli/%s";

  private URL url;

  public Requester(URL url) {
    this.url = url;
  }

  public String call() throws ConnectionException, BadResponseException {
    try {
      URLConnection connection = this.url.openConnection();

      connection.setConnectTimeout(CONNECT_TIMEOUT);
      connection.setReadTimeout(READ_TIMEOUT);

      connection.setRequestProperty(ACCEPT_HEADER_KEY, EXPECTED_CONTENT_TYPE);

      String userAgent = String.format(Locale.ROOT, USER_AGENT_HEADER_FORMAT, Requester.class.getPackage().getImplementationVersion());
      connection.setRequestProperty(USER_AGENT_HEADER_KEY, userAgent);

      connection.connect();

      if (!connection.getContentType().equals(EXPECTED_CONTENT_TYPE)) {
        // TODO log
        throw new BadResponseException("bad response content type");
      }

      InputStream is = connection.getInputStream();
      String body = IOUtils.toString(is, StandardCharsets.UTF_8);
      is.close();

      return body;
    } catch (IOException e) {
      // TODO log
      throw new ConnectionException("failed to connect to remote API");
    }
  }
}
