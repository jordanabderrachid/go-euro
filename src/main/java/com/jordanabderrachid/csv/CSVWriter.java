package com.jordanabderrachid.csv;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.List;

public class CSVWriter {
  private static final String LINE_SEPARATOR = System.lineSeparator();

  public static String toFile(String filename, List<CSVRecord> records) throws WriteException {
    File file = FileSystems.getDefault().getPath(filename).toFile();

    try {
      FileWriter fileWriter = new FileWriter(file);

      fileWriter.write(records.get(0).getCSVHeader());
      fileWriter.write(LINE_SEPARATOR);

      for (CSVRecord record : records) {
        fileWriter.write(record.getCSVString());
        fileWriter.write(LINE_SEPARATOR);
      }

      fileWriter.close();

      return file.getAbsolutePath();
    } catch (IOException e) {
      // TODO log error message
      System.out.println(e.getMessage());
      throw new WriteException("failed to create csv file");
    }
  }
}
