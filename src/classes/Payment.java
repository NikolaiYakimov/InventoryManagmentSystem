package classes;

public class Payment {
    private double amount;

    public Payment(double amount){
        this.amount=amount;
    }

    public boolean processOrderPayment(double orderPrice){

        if(amount>=orderPrice){
            System.out.println("classes.Order placed successfully!");
            return true;
        }else {
            System.out.println("Not enough money. classes.Order not Complete");
            return false;
        }
    }
}
