import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ua.hillel.CSV_Parse;
import ua.hillel.CSV_Reader;
import ua.hillel.Calculation;
import ua.hillel.Order;

/**
 * @author Podvorchan Ruslan 26.12.2023
 */
public class UtilTest {

  private final String ORDER = "/path/to/file.csv";
  CSV_Reader csvReader = Mockito.mock(CSV_Reader.class);
  CSV_Parse csvParser = Mockito.mock(CSV_Parse.class);

  @Test
  void testGroupingByNameIsNull() {
    assertNotNull(Calculation.groupByName(null));
  }

  @Test
  void testToObjectListIsNull() {
    List<Order> orders = Calculation.toOrderList(null);

    assertNull(orders.get(0).getName());
    assertNull(orders.get(0).getProductName());
    assertEquals(0, orders.get(0).getPrice());
    assertEquals(0, orders.get(0).getAmount());
  }
  @Test
  void testGroupingByName() {
    List<Order> orders = Arrays.asList(new Order("АТБ", "Гречка", 31.25, 120),
        new Order("Сильпо", "Сахар", 21.40, 107));

    Map<String, List<Order>> ordersMap = Calculation.groupByName(orders);

    assertEquals("АТБ", ordersMap.get("АТБ").get(0).getName());
    assertEquals("Гречка", ordersMap.get("АТБ").get(0).getProductName());

    assertEquals("Сильпо", ordersMap.get("Сильпо").get(0).getName());
    assertEquals("Сахар", ordersMap.get("Сильпо").get(0).getProductName());
  }
  @Test
  void testToObjectListMatchesNumFields() {
    List<String[]> testList = new ArrayList<>();
    testList.add(new String[]{"АТБ"});

    Mockito.when(csvParser.parse(csvReader.ordersReader(ORDER), ";", 0)).thenReturn(testList);
    List<String[]> orderWords = csvParser.parse(csvReader.ordersReader(ORDER), ";", 0);

    assertThrows(IllegalArgumentException.class, () -> Calculation.toOrderList(orderWords));
  }

}
