package com.jordanabderrachid.model;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import com.jordanabderrachid.csv.CSVRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * This class represents a suggestion, it contains an id, a name, a type and a position. It implements the
 * CSVRecord interface to be displayed in CSV format.
 *
 * @see com.jordanabderrachid.csv.CSVRecord
 * @author jordanabderrachid
 */
public class Suggestion implements CSVRecord {
  private static final String CSV_HEADER = "_id,name,type,latitude,longitude";

  private static final String TO_CSV_FORMAT = "%d,%s,%s,%f,%f";

  @SerializedName(value = "_id")
  private int id;

  @SerializedName(value = "name")
  private String name;

  @SerializedName(value = "type")
  private String type;

  @SerializedName(value = "geo_position")
  private GeoPosition position;

  /**
   * Get the id of the suggestion.
   *
   * @return the id of the suggestion
   */
  public int getId() {
    return this.id;
  }

  /**
   * Get the name of the suggestion.
   *
   * @return the name of the suggestion
   */
  public String getName() {
    return this.name;
  }

  /**
   * Get the type of the suggestion.
   *
   * @return the type of the suggestion
   */
  public String getType() {
    return this.type;
  }

  /**
   * Get the latitude of the suggestion.
   *
   * @return the latitude of the suggestion
   */
  public double getLatitude() {
    return this.position.getLatitude();
  }

  /**
   * Get the longitude of the suggestion.
   *
   * @return the longitude of the suggestion
   */
  public double getLongitude() {
    return this.position.getLongitude();
  }

  /**
   * Get the csv header of the suggestion.
   *
   * @return the csv header of the suggestion
   */
  public String getCSVHeader() {
    return CSV_HEADER;
  }

  /**
   * Get the csv string of the suggestion.
   *
   * @return the csv string of the suggestion
   */
  public String getCSVString() {
    return String.format(Locale.ROOT, TO_CSV_FORMAT, this.getId(), this.getName(), this.getType(), this.getLatitude(), this.getLongitude());
  }

  /**
   * Deserialize a json payload in a list of suggestion.
   *
   * @param json - the json payload
   * @return the list of suggestions
   * @throws JsonSyntaxException
   */
  public static List<CSVRecord> fromJSON(String json) throws JsonSyntaxException {
    Gson gson = new Gson();

    return gson.fromJson(json, new TypeToken<ArrayList<Suggestion>>(){}.getType());
  }
}

/**
 * This class represents a geo position, it is nested is the Suggestion class.
 *
 * @see com.jordanabderrachid.model.Suggestion
 * @author jordanabderrachid
 */
class GeoPosition {
  @SerializedName(value = "latitude")
  private double latitude;

  @SerializedName(value = "longitude")
  private double longitude;

  /**
   * Get the latitude of the geo position.
   *
   * @return the latitude of the geo position
   */
  public double getLatitude() {
    return this.latitude;
  }

  /**
   * Get the longitude of the geo position.
   *
   * @return the longitude of the geo position
   */
  public double getLongitude() {
    return this.longitude;
  }
}
