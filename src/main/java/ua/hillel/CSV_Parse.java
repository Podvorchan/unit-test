package ua.hillel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Podvorchan Ruslan 26.12.2022
 */
public class CSV_Parse {

  public List<String[]> parse(List<String> lines, String separator, int skipLines) {
    if (lines == null || separator == null) {
      List<String[]> list = new ArrayList<>();
      list.add(new String[0]);
      return list;
    }
    return lines.stream()
        .skip(skipLines)
        .map(l -> l.split(separator))
        .collect(Collectors.toList());
  }

}
