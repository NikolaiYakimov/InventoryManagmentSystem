package classes;

public class InventoryItem extends AbstractItem {
    private String idItem;
    private int quantity;

    public InventoryItem(String name, String idItem, int quantity) {
        super(name);
        this.idItem = idItem;
        this.quantity = quantity;
    }

    public String getIdItem() {
        return idItem;
    }

    public void setIdItem(String idItem) {
        this.idItem = idItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public double calculateValue() {
        return getPrice() * quantity;
    }


}
