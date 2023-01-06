package ua.hillel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Podvorchan Ruslan 26.12.2022
 */
public class Calculation {
  public static List<Order> toOrderList(List<String[]> wordsList) {
    if (wordsList == null) {
      return Collections.singletonList(new Order());
    }
    return wordsList.stream().map(data -> {
      if (data.length != 4) {
        throw new IllegalArgumentException(
            "Array length does not match the number of fields");
      }
      Order order = new Order();
      order.setName(data[0]);
      order.setProductName(data[1]);
      order.setPrice(Double.parseDouble(data[2]));
      order.setAmount(Integer.parseInt(data[3]));
      return order;
    }).collect(Collectors.toList());
  }


  public static Map<String, List<Order>> groupByName(List<Order>  order) {
    if ( order == null) {
      return new HashMap<>();
    }
    return  order.stream()
        .collect(Collectors.groupingBy(Order::getName));
  }

  public static List<String> mergeProduct(List<Order>  order) {
    if ( order == null) {
      return new ArrayList<>();
    }
    return  order.stream()
        .collect(Collectors.toMap(Order::getProductName
            , Function.identity(), Order::averagePriceAndSumQuantity))
        .values().stream()
        .map(Order::toString)
        .collect(Collectors.toList());
  }

}
