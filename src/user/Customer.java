package user;
import fileHandling.fileHandle;
import main.foodsys.foodSys;
import payments.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Order.*;

public class Customer extends User {
    static Scanner input = new Scanner(System.in);

    private String UserID;
    private String email;
    private final String deliveryAddress;

    private static final List<Customer> customers = fileHandle.readUsersFromFile();
    private ArrayList<CardPayment> UserCardPayments = new ArrayList<>();
    private CardPayment OrderCardPayment;
    private cart Usercart = null;
    private order UserOrder = null;

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
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
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
        UserOrder = new order(this.UserID,this.deliveryAddress,this.OrderCardPayment,this.Usercart,DeliveryTime);
        UserOrder.displayOrder();
        Usercart.removeCart();
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


