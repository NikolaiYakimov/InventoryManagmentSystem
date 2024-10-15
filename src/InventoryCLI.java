import Services.InventoryPersistence;
import classes.InventoryItem;
import Services.InventoryManagementSystem;
import classes.Order;
import classes.Payment;

import java.io.IOException;
import java.util.*;

public class InventoryCLI {
    protected static InventoryManagementSystem inventory =new InventoryManagementSystem() ;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Add Item");
            System.out.println("2. Delete Item");
            System.out.println("3. List Items");
            System.out.println("4. Place Order");
            System.out.println("5. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (choice) {
                case 1:
                    inventory.addItem();
                    break;
                case 2:
                    inventory.deleteItemById();
                    break;
                case 3:
                    inventory.displayListOfItems();
                    break;
                case 4:
                    placeOrder(scanner);
                    break;
                case 5:
                    InventoryPersistence.saveInventoryToFile(inventory.getInventoryItems());
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }


    private static void placeOrder(Scanner scanner) {
        Order order=new Order();
        while (true){
            System.out.println("Enter item ID to add the item to the order");
            String itemId=scanner.nextLine();
            if(itemId.equalsIgnoreCase("done"))
                break;
//
            InventoryItem itemToAdd=null;
            for(InventoryItem item: inventory.getInventoryItems()){
                if(item.getIdItem().equals(itemId)){
                    itemToAdd=item;
                    break;
                }
            }
            if(itemToAdd==null){
                System.out.println("Interfaces.Item not found!");
                continue;
            }

            System.out.println("Enter quantity of the item");
            int quantity=Integer.parseInt(scanner.nextLine());

            if(quantity> itemToAdd.getQuantity()){
                System.out.println("Not enough stock available for " + itemToAdd.getName());
            }else {
                order.addItemToOrder(itemToAdd,quantity);
            }
        }

        if(order.calculateTotalCost()==0){
            System.out.println("No items in the order");
            return;
        }

        System.out.println("Total cost: "+ order.calculateTotalCost());
        // classes.Payment processing
        System.out.println("Enter payment amount: ");
        double paymentAmount = Double.parseDouble(scanner.nextLine());

        Payment payment = new Payment(paymentAmount);

        order.placeOrder(payment);
    }
}
