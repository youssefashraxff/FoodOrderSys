package app.models.user;
import app.models.Order.OrderStatus;
import app.models.Order.Review;
import app.models.Order.cart;
import app.models.Order.order;
import app.models.fileHandling.fileHandle;
import app.models.payments.CardPayment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Customer extends User {
    static Scanner input = new Scanner(System.in);

    private final String UserID;
    private String email;
    private String deliveryAddress;
    private String phoneNumber;

    private ArrayList<order> orders = new ArrayList<>();
    private ArrayList<CardPayment> UserCardPayments = new ArrayList<>();
    private CardPayment OrderCardPayment;
    private cart Usercart = null;
    private order UserOrder = null;
    private Review UserReview = new Review();

    public Customer(String UserId, String username,String lastName ,String email, String password,String phoneNumber ,String deliveryAddress) {
        super(username, lastName,password);
        this.UserID = UserId;
        this.email = email;
        this.deliveryAddress = deliveryAddress;
        this.phoneNumber = phoneNumber;
        Usercart = new cart();
        UserOrder = new order();
        OrderCardPayment = new CardPayment();
    }

    //Getters and Setters
    public String getUserID() {
        return UserID;
    }
    public String getEmail() {
        return email;
    }
    public String getDeliveryAddress() {
        if(this.deliveryAddress.equals("null")){
            return "";
        }
        else return deliveryAddress;
    }
    public String getDeliveryAddressForFile(){
        return deliveryAddress;
    }
    //Customer Methods
    public void addPaymentMethod(){
        CardPayment tempCard = new CardPayment();
        UserCardPayments.add(tempCard.add_CreditCard_info(this.UserID));
        fileHandle.writePaymentMethodToFile(UserCardPayments,this.getUserID());
    }
    public boolean displayPaymentMethods(){
        return CardPayment.displayCardInfo(this.UserID);
    }
    public void addCartToOrder(){
        String DeliveryTime;
        int inputPayment;
        DeliveryTime=order.promptDeliveryTime();
        while (true){
            System.out.println("\nPayment Method:\n1. Cash\n2. Credit/Debit Card");
            inputPayment = input.nextInt();
            if(inputPayment == 1){
                OrderCardPayment = new CardPayment("Cash");
                break;
            }
            else if(inputPayment == 2){
                if(displayPaymentMethods()){
                    addPaymentMethod();
                }
                else{
                    UserCardPayments = fileHandle.readPaymentMethodFromFile(UserID);
                    OrderCardPayment=CardPayment.chooseCardForOrder(UserCardPayments);
                }
                break;
            }
            else{
                System.out.println("Invalid input. Please try again.");
            }
        }
        System.out.println("\n\n"+ Usercart.getRestaurantName()+"\n\n");
        UserOrder = new order(this.username,this.deliveryAddress,this.OrderCardPayment,this.Usercart,DeliveryTime);
        UserOrder.displayOrder();
        Usercart.removeCart();
    }
    public void postOrder(){
        orders.add(UserOrder);
        OrderStatus orderStatus = new OrderStatus("java.java.models.Order Placed");
        Thread orderTrackingThread = new Thread(orderStatus);
        System.out.println("java.java.models.Order tracking started. You will see updates shortly...");
        orderTrackingThread.run();

        UserOrder.setOrderReview(UserReview.collectReview(this.UserOrder.getRestaurantName(),this.getUsername()));

    }
    public void displayOrder(){
        if(orders.isEmpty()){
            System.out.println("No orders found. Please try again.");
        }
        else{
            for(order order : orders){
                order.displayOrder_start();
            }
        }
    }
    public cart getCart() {
        if (Usercart == null) {
            Usercart = new cart();
        }
        return Usercart;
    }
    public void setCart(cart cart) {
        this.Usercart = cart;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber=phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    @Override
    public boolean equals(Object o) {
        Customer c = (Customer) o;
        return this.UserID.equals(c.UserID);
    }
}


