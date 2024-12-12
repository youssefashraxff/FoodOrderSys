package payments;

public class userPayment {
    //private float amount;
    private String paymentMethod; // Cash or Credit Card

    public userPayment(String paymentMethod) {
        //this.amount = amount;
        this.paymentMethod = paymentMethod;
    }
    public userPayment() {}
    /*public float getAmount() {
        return amount;
    }*/
    public String getPaymentMethod() {
        return paymentMethod;
    }
}
