package com.jordanabderrachid.model;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import com.jordanabderrachid.csv.CSVRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

  public Suggestion() {}

  public int getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public String getType() {
    return this.type;
  }

  public double getLatitude() {
    return this.position.getLatitude();
  }

  public double getLongitude() {
    return this.position.getLongitude();
  }

  public String getCSVHeader() {
    return CSV_HEADER;
  }

  public String getCSVString() {
    return String.format(Locale.ROOT, TO_CSV_FORMAT, this.getId(), this.getName(), this.getType(), this.getLatitude(), this.getLongitude());
  }

  public static List<CSVRecord> fromJSON(String json) throws JsonSyntaxException {
    Gson gson = new Gson();

    return gson.fromJson(json, new TypeToken<ArrayList<Suggestion>>(){}.getType());
  }
}

class GeoPosition {
  @SerializedName(value = "latitude")
  private double latitude;

  @SerializedName(value = "longitude")
  private double longitude;

  public GeoPosition() {}

  public double getLatitude() {
    return this.latitude;
  }

  public double getLongitude() {
    return this.longitude;
  }
}
