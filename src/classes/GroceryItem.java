package classes;

import Interfaces.ItemType;

public class GroceryItem extends InventoryItem {
    private final String expirationDate;

    public GroceryItem(String name, String idItem, int quantity,double price,String expirationDate) {
        super(name, idItem, quantity);
        setCategory(ItemType.Grocery);
        setPrice(price);
        this.expirationDate=expirationDate;

    }

    public String getExpirationDate(){
        return expirationDate;
    }

    @Override
    public String getItemDetails(){
        return super.getIdItem()+" | "+super.getItemDetails()+" | Expiration Date: "+expirationDate;
    }


}
