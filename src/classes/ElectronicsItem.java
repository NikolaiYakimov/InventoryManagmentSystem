package classes;

import Interfaces.ItemType;

public class ElectronicsItem extends InventoryItem {
    private final int warrantyPeriod;

    public ElectronicsItem(String name, String idItem, int quantity, double price, int warrantyPeriod) {
        super(name, idItem, quantity);
        this.warrantyPeriod = warrantyPeriod;
        setPrice(price);
        setCategory(ItemType.Electronics);
    }


    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    @Override
    public String getItemDetails() {
        return super.getIdItem() + " | " + super.getItemDetails() + " | Warranty Period: " + warrantyPeriod + " months!";
    }

}
