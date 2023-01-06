package ua.hillel;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Podvorchan Ruslan 26.12.2022
 */
public class Main {

  public static void main(String[] args) {
    final String ORDER_1 = "src/main/resources/order_1.csv";
    final String ORDER_2 = "src/main/resources/order_2.csv";
    final String ATB = "src/main/resources/atb_res.csv";
    final String SILPO = "src/main/resources/silpo_res.csv";
    final String TITLE = "НАИМЕНОВАНИЕ;ЦЕНА;ШТ;";
    CSV_Reader reader = new CSV_Reader();
    CSV_Writer writer = new CSV_Writer();
    CSV_Parse csvParse = new CSV_Parse();
    Calculation calculation = new Calculation();

    List<Order> orders = new ArrayList<>();

    List<String> order1Lines = reader.ordersReader(ORDER_1);
    List<String[]> order1Words = csvParse.parse(order1Lines, ";", 1);
    orders.addAll(Calculation.toOrderList(order1Words));

    List<String> order2Lines = reader.ordersReader(ORDER_2);
    List<String[]> order2Words = csvParse.parse(order2Lines, ";", 1);
    orders.addAll(Calculation.toOrderList(order2Words));

    Map<String, List<Order>> orderMap = Calculation.groupByName(orders);

    List<String> atbProductsLines = Calculation.mergeProduct(orderMap.get("АТБ"));
    atbProductsLines.add(0, TITLE);

    List<String> silpoProductsLines = Calculation.mergeProduct(orderMap.get("Сильпо"));
    silpoProductsLines.add(0, TITLE);

    writer.write(ATB, atbProductsLines);
    writer.write(SILPO, silpoProductsLines);
  }


}


