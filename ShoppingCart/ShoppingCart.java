import java.util.*;

public class ShoppingCart {

    private String customerName;
    private String currentDate;

    private List<ItemToPurchase> cartItems = new ArrayList<ItemToPurchase>();

    public ShoppingCart() {
        customerName = "none";
        currentDate = "January 1, 2016";
    }

    public ShoppingCart(String name, String date) {
        customerName = name;
        currentDate = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getDate() {
        return currentDate;
    }

    public void addItem(ItemToPurchase ItemToPurchase) {
        cartItems.add(ItemToPurchase);
    }

    public void removeItem(String name) {
        if (cartItems.size() == 0) {
            System.out.println("Item not found in cart. Nothing removed.");
            return;
        }

        for (ItemToPurchase item : cartItems) {
            if (name.equals(item.getName())) {
                cartItems.remove(item);
                return;
            }
        }
        System.out.println("Item not found in cart. Nothing removed.\n");

    }

    public void modifyItem(String name) {
        if (cartItems.size() == 0) {
            System.out.println("Item not found in cart. Nothing modified.\n");
            return;
        }
        
        ItemToPurchase newItem = null;
        for (ItemToPurchase cartItem : cartItems) {
            if (cartItem.getName().equals(name)) {
                newItem = cartItem;
            }
        }
        if (newItem == null) {
            System.out.println("Item not found in cart. Nothing modified.\n");
            return;
        }
        if (newItem.getPrice() != 0) {
            System.out.println("modify the price :" + newItem.getPrice() + " to zero .");
            newItem.setPrice(0);
        }

        if (newItem.getQuantity() != 0) {
            System.out.println("modify the quantity :" + newItem.getQuantity() + " to zero .");
            newItem.setQuantity(0);
        }
        if (!"none".equals(newItem.getDescription())) {
            System.out.println("modify the description :" + newItem.getDescription() + " to none .");
            newItem.setDescription("none");
        }


    }

    public int getNumItemsInCart() {
        return cartItems.size();
    }

    public int getCostOfCart() {
        int result = 0;

        for (ItemToPurchase cartItem : cartItems) {
            result += cartItem.getPrice() * cartItem.getQuantity();
        }
        return result;
    }

    public void printTotal() {
        System.out.println(customerName + "\'s Shopping Cart - " + currentDate + "\n" +
                "Number of Items: " + cartItems.size() + "\n");

        if (cartItems.size() == 0) {
            System.out.println("SHOPPING CART IS EMPTY\n\n" + "Total: $0\n");
            return;
        }        
        
        for (ItemToPurchase cartItem : cartItems) {
            System.out.println(cartItem.getName() + " " + cartItem.getQuantity() + " @ $" + cartItem.getPrice() + " = $" + cartItem.getQuantity() * cartItem.getPrice());
        }
        System.out.println("\nTotal: $" + getCostOfCart() + "\n");
    }

    public void printDescriptions() {
        if (cartItems.size() == 0) {
            System.out.println(" Item not found in cart , you can choice a to add items .");
            return;
        }
        System.out.println(customerName + "\'s Shopping Cart - " + currentDate + "\n");
        System.out.println("Item Descriptions");

        for (ItemToPurchase cartItem : cartItems) {
            cartItem.printItemDescription();
        }
        System.out.println();
    }
}