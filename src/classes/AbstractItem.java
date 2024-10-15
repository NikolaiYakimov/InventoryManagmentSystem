package classes;

import Interfaces.*;

public abstract class AbstractItem implements Item, Breakable, Categorizable, Perishable, Sellable {
    private String name;
    private ItemType category;
    private double price;
    private boolean breakable;
    private boolean perishable;

    public AbstractItem(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean isBreakable() {
        return breakable;
    }

    @Override
    public void handleBreakage() {
        if (breakable) {
            System.out.println("Interfaces.Item was broken");
        }

    }

    @Override
    public void setCategory(ItemType category) {
        this.category = category;
    }

    @Override
    public ItemType getCategory() {
        return category;
    }

    @Override
    public String getItemDetails() {
        return "Name " + name +" | Category: "+category+" | Price: "+price;
    }

    @Override
    public void displayDescription() {
        System.out.println(getItemDetails());
    }

    @Override
    public boolean isPerishable() {
        return perishable;
    }

    @Override
    public void handleExpiration() {
        if (perishable){
            System.out.println("Interfaces.Item was expired!");
        }
    }

    @Override
    public void setPrice(double price) {
        this.price=price;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public void setBreakable(boolean breakable) {
        this.breakable = breakable;
    }

    public void setPerishable(boolean perishable) {
        this.perishable = perishable;
    }
}
