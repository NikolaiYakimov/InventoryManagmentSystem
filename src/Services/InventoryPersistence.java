package Services;

import Interfaces.ItemType;
import classes.ElectronicsItem;
import classes.FragileItem;
import classes.GroceryItem;
import classes.InventoryItem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryPersistence {
    private final static String FILE_PATH="D:\\JavaProjects\\InventoryManagmentSystem\\src\\database.txt";

    //With objectStreams
//    public void saveInventoryToFile(String file){
//        try (ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream(file))){
//            out.writeObject(inventoryItems);
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//    }
//    @SuppressWarnings("unchecked")
//    public void loadInventoryFromFile(String file){
//        try (ObjectInputStream inputStream=new ObjectInputStream(new FileInputStream(file))){
//
//            inventoryItems=(List<classes.InventoryItem>) inputStream.readObject() ;
//        } catch (ClassNotFoundException | IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

//
//
//
//
//    public void displayListOfItems(){
//        System.out.println("-".repeat(15)+" Inventory List "+"-".repeat(15));
//        for(classes.InventoryItem item:inventoryItems){
//            if(item instanceof classes.ElectronicsItem elItem){
//                elItem.displayDescription();
//            } else if (item instanceof classes.GroceryItem grItem) {
//                grItem.displayDescription();
//            } else if (item instanceof classes.FragileItem frItem) {
//                frItem.displayDescription();
//            }
//
//        }
//    }

    public static void saveInventoryToFile(List<InventoryItem> inventoryItems) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (InventoryItem item : inventoryItems) {
                StringBuilder sb = new StringBuilder();
                sb.append(item.getIdItem()).append(",")
                        .append(item.getName()).append(",")
                        .append(item.getQuantity()).append(",")
                        .append(item.getPrice()).append(",")
                        .append(item.getCategory()).append(",")
                        .append(item.isBreakable()).append(",")
                        .append(item.isPerishable());

                if (item instanceof GroceryItem groceryItem) {
                    sb.append(",").append(groceryItem.getExpirationDate());
                } else if (item instanceof ElectronicsItem electronicsItem) {
                    sb.append(",").append(electronicsItem.getWarrantyPeriod());
                } else if (item instanceof FragileItem fragileItem) {
                    sb.append(",").append(fragileItem.getWeight());
                }
                writer.write(sb.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<InventoryItem> loadInventoryFromFile() {
         List<InventoryItem> inventoryItems = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String itemID = parts[0];
                String name = parts[1];
                int quantity = Integer.parseInt(parts[2]);
                double price = Double.parseDouble(parts[3]);
                ItemType category = ItemType.valueOf(parts[4]);
                boolean breakable = Boolean.parseBoolean(parts[5]);
                boolean perishable = Boolean.parseBoolean(parts[6]);
                InventoryItem item = null;
                if (category == ItemType.Electronics) {
                    int warrantyPeriod = Integer.parseInt(parts[7]);
                    item = new ElectronicsItem(name, itemID, quantity, price, warrantyPeriod);
                } else if (category == ItemType.Grocery) {
                    String expirationDate = parts[7];
                    item = new GroceryItem(name, itemID, quantity, price, expirationDate);
                } else if (category == ItemType.Fragile) {
                    double weight = Double.parseDouble(parts[7]);
                    item = new FragileItem(name, itemID, quantity, price, weight);
                }
                item.setBreakable(breakable);
                item.setPerishable(perishable);
                inventoryItems.add(item);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return inventoryItems;
    }
}
