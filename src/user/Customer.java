package user;
import fileHandling.fileHandle;
import payments.*;
import Order.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Customer extends User {
    static Scanner input = new Scanner(System.in);

    private final String UserID;
    private final String email;
    private final String deliveryAddress;

    private static final List<Customer> customers = fileHandle.readUsersFromFile();
    private ArrayList<CardPayment> UserCardPayments = new ArrayList<>();
    private CardPayment OrderCardPayment;
    private cart Usercart = null;
    private order UserOrder = null;
    private Review UserReview = new Review();

    public Customer(String UserId, String username, String email, String password, String deliveryAddress) {
        super(username, password);
        this.UserID = UserId;
        this.email = email;
        this.deliveryAddress = deliveryAddress;
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
        OrderStatus orderStatus = new OrderStatus("Order Placed");
        Thread orderTrackingThread = new Thread(orderStatus);
        System.out.println("Order tracking started. You will see updates shortly...");
        orderTrackingThread.run();

        UserOrder.setOrderReview(UserReview.collectReview(this.UserOrder.getRestaurantName(),this.getUsername()));

    }
    public void displayOrder(){

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
}


