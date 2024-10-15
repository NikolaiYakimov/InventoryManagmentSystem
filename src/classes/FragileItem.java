package classes;

import Interfaces.ItemType;

public class FragileItem extends InventoryItem {
    private final double weight;

    public FragileItem(String name, String idItem, int quantity,double price,double weight) {
        super(name, idItem, quantity);
        setPrice(price);
        setCategory(ItemType.Fragile);
        this.weight=weight;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String getItemDetails(){
        return super.getIdItem()+" | "+ super.getItemDetails()+String.format(" | Weight: %.2f kg",weight);
    }

}
