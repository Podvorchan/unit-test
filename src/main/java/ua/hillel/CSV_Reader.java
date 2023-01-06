package ua.hillel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import lombok.NoArgsConstructor;

/**
 * @author Podvorchan Ruslan 26.12.2022
 */
@NoArgsConstructor
public class CSV_Reader {

  public List<String> ordersReader(String path) {
    try {
      return Files.readAllLines(Paths.get(path));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
