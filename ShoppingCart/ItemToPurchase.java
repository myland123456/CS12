import java.util.*;

//Parts of this program has been done with helps of my parent

public class ItemToPurchase {

    private String itemName;
    private int itemPrice;
    private int itemQuantity;
    private String itemDescription;

    public ItemToPurchase() {
        itemName = "none";
        itemPrice = 0;
        itemQuantity = 0;
    }

    public ItemToPurchase(String name, String desc, int price, int count) {
        itemName = name;
        itemDescription = desc;
        itemPrice = price;
        itemQuantity = count;
    }

    public void setName(String name) {
        itemName = name;
    }

    public String getName() {
        String name = itemName;
        return name;
    }

    public void setDescription(String desc) {
        itemDescription = desc;
    }

    public String getDescription() {
        String desc = itemDescription;
        return desc;
    }

    public void setPrice(int price) {
        itemPrice = price;
    }

    public int getPrice() {
        int price = itemPrice;
        return price;
    }

    public void setQuantity(int count) {
        itemQuantity = count;
    }

    public int getQuantity() {
        int count = itemQuantity;
        return count;
    }

    public void printItemCost() {
        int itemTotal = itemQuantity * itemPrice;
        System.out.println(itemName + " " + itemQuantity + " @ $" + itemPrice + " = $" + itemTotal);
    }

    public void printItemDescription() {
        System.out.println(itemName + ": " + itemDescription);
    }
}
