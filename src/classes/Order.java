package classes;

import Services.InventoryManagementSystem;

import java.util.HashMap;
import java.util.Map;

public class Order {

    private Map<InventoryItem, Integer> orderItem = new HashMap<>();

    //Add item to orderItem
    public void addItemToOrder(InventoryItem item, int quantity) {
        if (orderItem.containsKey(item)) {
            int totalQuantity = orderItem.get(item) + quantity;
            if (item.getQuantity() >= totalQuantity)
                orderItem.put(item, totalQuantity);
        } else {
            orderItem.put(item, quantity);
        }
    }

    //Calculate total cost of the order
    public double calculateTotalCost() {
        double total = 0;
        for (Map.Entry<InventoryItem, Integer> entry : orderItem.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }

    //Place order
    public void placeOrder( Payment payment) {
        if (payment.processOrderPayment(calculateTotalCost())) {
            for (Map.Entry<InventoryItem, Integer> entry : orderItem.entrySet()) {
                InventoryItem inventoryItem = entry.getKey();
                int orderQuantity = entry.getValue();

                if (inventoryItem.getQuantity() >= orderQuantity) {
                    inventoryItem.setQuantity(inventoryItem.getQuantity() - orderQuantity);
                    System.out.println("Your order of " + orderQuantity + " units of " + inventoryItem.getName() + " is placed!");

                }
            }
        }
    }

}
