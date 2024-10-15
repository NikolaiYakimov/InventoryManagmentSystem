package Services;

import Interfaces.ItemType;
import classes.ElectronicsItem;
import classes.FragileItem;
import classes.GroceryItem;
import classes.InventoryItem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class InventoryManagementSystem {
    protected List<InventoryItem> inventoryItems;
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    public InventoryManagementSystem() {
        inventoryItems = InventoryPersistence.loadInventoryFromFile();
    }

    public List<InventoryItem> getInventoryItems() {
        return inventoryItems;
    }

    public void addItem() throws IOException {
        System.out.println("Enter item ID:");
        String itemID = bufferedReader.readLine();

        System.out.println("Enter name:");
        String name=bufferedReader.readLine();

        System.out.println("Enter Quantity: ");
        int quantity=Integer.parseInt(bufferedReader.readLine());

        System.out.println("Enter Price: ");
        double price=Double.parseDouble(bufferedReader.readLine());

        System.out.println("Select Category");
        for (ItemType type: ItemType.values()){
            System.out.println(type.ordinal()+1+" . "+ type);
        }
        int categoryChoice=Integer.parseInt(bufferedReader.readLine());
        ItemType category=ItemType.values()[categoryChoice-1];

        if (category == ItemType.Electronics) {
            System.out.println("Enter Warranty Period:");
            int warrantyPeriod = Integer.parseInt(bufferedReader.readLine());
            inventoryItems.add( new ElectronicsItem(name, itemID, quantity, price, warrantyPeriod));
        } else if (category == ItemType.Grocery) {
            System.out.println("Enter Expiration Date :");
            String expirationDate = bufferedReader.readLine();
            inventoryItems.add( new GroceryItem(name, itemID, quantity, price, expirationDate));
        } else if (category == ItemType.Fragile) {
            System.out.println("Enter Weight");
            double weight=Double.parseDouble(bufferedReader.readLine());
            inventoryItems.add( new FragileItem(name, itemID, quantity, price, weight));
        }

        System.out.println("You add the item successfully");
    }

    public void deleteItemById() throws IOException {
        System.out.println("Enter the Id of the item you want to delete: ");
        String itemId=bufferedReader.readLine();
        inventoryItems.removeIf(item->item.getIdItem().equals(itemId));
    }


    public void displayListOfItems(){
        System.out.println("-".repeat(15)+" Inventory List "+"-".repeat(15));
        for(InventoryItem item:inventoryItems){
            if(item instanceof ElectronicsItem elItem){
                elItem.displayDescription();
            } else if (item instanceof GroceryItem grItem) {
                grItem.displayDescription();
            } else if (item instanceof FragileItem frItem) {
                frItem.displayDescription();
            }

        }
    }


}
