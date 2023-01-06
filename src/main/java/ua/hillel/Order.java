package ua.hillel;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Podvorchan Ruslan 26.12.2022
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Order {

  private String name;
  private String productName;
  private double price;
  private int amount;
   public static Order averagePriceAndSumQuantity(Order first, Order second) {
    first.setPrice((first.getPrice() + second.getPrice()) / 2);
    first.setAmount(first.getAmount() + second.getAmount());
    return first;
  }
  @Override
  public String toString() {
    return productName + ";" + price + ";" + amount + ";";
  }

}
