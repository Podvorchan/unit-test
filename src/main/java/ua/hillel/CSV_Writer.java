package ua.hillel;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author Podvorchan Ruslan 26.12.2022
 */
public class CSV_Writer {

  public void write(String path, List<String> lines) {
    try {
      Files.write(Paths.get(path), lines);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}