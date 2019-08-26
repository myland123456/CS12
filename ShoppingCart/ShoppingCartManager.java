import java.util.*;

public class ShoppingCartManager {

	public static Scanner scnr = new Scanner(System.in);
	
    public static void main(String[] args) {
    	
        System.out.println("Enter Customer's Name:");
        String name = scnr.nextLine();
        System.out.println("Enter Today's Date:");
        String date = scnr.nextLine();
        System.out.println();
        System.out.println("Customer Name: " + name);
        System.out.println("Today's Date: " + date);
        System.out.println();

        ShoppingCart cart = new ShoppingCart(name, date);

        printMenu();

        while (true) {
            
            System.out.println("Choose an option:");

            String userChoice = scnr.nextLine();

            if (userChoice.equals("q")) {
                break;
            }

            if (userChoice.equals("a")) {
                System.out.println("ADD ITEM TO CART");
                ItemToPurchase item = getItem();
                cart.addItem(item);
                printMenu();
            }

            if (userChoice.equals("d")) {
                System.out.println("REMOVE ITEM FROM CART");

                System.out.println("Enter name of item to remove:");
                String itemName = scnr.nextLine();
                cart.removeItem(itemName);
                printMenu();
            }

            if (userChoice.equals("c")) {
                System.out.println("CHANGE ITEM QUANTITY");
                System.out.println("Enter the item name:");
                String itemName = scnr.nextLine();
                System.out.println("Enter the new quantity:");
                int count = scnr.nextInt();
                cart.modifyItem(itemName);
                printMenu();
            }

            if (userChoice.equals("i")) {
                System.out.println("OUTPUT ITEMS' DESCRIPTIONS");
                cart.printDescriptions();
                printMenu();
            }

            if (userChoice.equals("o")) {
                System.out.println("OUTPUT SHOPPING CART");
                cart.printTotal();
                printMenu();
            }
            else {
                
            }
        }
    }

    public static void printMenu() {
        System.out.print("MENU\n" +
                "a - Add item to cart\n"+
                "d - Remove item from cart\n" +
                "c - Change item quantity\n" +
                "i - Output items' descriptions\n" +
                "o - Output shopping cart\n" +
                "q - Quit\n" +
                "\n");
    }

    private static ItemToPurchase getItem() {
        ItemToPurchase item = new ItemToPurchase();

        System.out.println("Enter the item name:");
        String name = scnr.nextLine();
        item.setName(name);

        System.out.println("Enter the item description:");
        String description = scnr.nextLine();
        item.setDescription(description);

        System.out.println("Enter the item price:");
        int price = scnr.nextInt();
        item.setPrice(price);

        System.out.println("Enter the item quantity:");
        int count = scnr.nextInt();
        item.setQuantity(count);

        System.out.println();
        
        return item;
    }
}
