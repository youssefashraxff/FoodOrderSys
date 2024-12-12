package Order;
import payments.*;
import user.User;

import java.util.Date;
import java.util.Scanner;

public class order {
    private cart orderCart;
    private int userID;
    private String DeliveryAddress;
    private String RestaurantName;
    private String DeliveryTime;
    private CardPayment OrderPayment;
    private String transactionID;

    public order(int userID, String DeliveryAddress, CardPayment OrderPayment , cart orderCart,String DeliveryTime) {
        this.orderCart = orderCart;
        this.OrderPayment = OrderPayment;
        this.userID = userID;
        this.DeliveryAddress = DeliveryAddress;
        this.DeliveryTime = DeliveryTime;
        this.RestaurantName = orderCart.getRestaurantName();
    }
    public void displayOrder(){
        System.out.println("\nOrder added successfully\n");
        System.out.println("User ID: " + userID);
        System.out.println("Restaurant Name: " + RestaurantName);
        System.out.println("Delivery Address: " + DeliveryAddress);
        System.out.println("Delivery Time: " + DeliveryTime);
        System.out.println("Order Payment: " + OrderPayment);
        System.out.println("Order : ");
        orderCart.displayOrder();
    }

    public order(){}

    public static String promptDeliveryTime() {
          String[] timeSlots = {
                "12:00 PM - 2:00 PM",
                "2:00 PM - 4:00 PM",
                "4:00 PM - 6:00 PM",
                "6:00 PM - 8:00 PM"
        };

        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a delivery time slot for today:");

        for (int i = 0; i < timeSlots.length; i++) {
            System.out.println((i + 1) + ". " + timeSlots[i]);
        }
        String inputDelTime;
        int choice;
        while (true) {
            System.out.print("Enter your choice (1-" + timeSlots.length + "): ");
            choice = scanner.nextInt();
            if (choice >= 1 && choice <= timeSlots.length) {
                inputDelTime = timeSlots[choice - 1];
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
        return inputDelTime;
    }

    public order(cart orderCart, User loggenInUser) {
        this.orderCart = orderCart;
    }

    public cart getOrderCart() {
        return orderCart;
    }
    public void setOrderCart(cart orderCart) {
        this.orderCart = orderCart;
    }

    public int getUserID() {
        return userID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getDeliveryAddress() {
        return DeliveryAddress;
    }
    public void setDeliveryAddress(String deliveryAddress) {
        DeliveryAddress = deliveryAddress;
    }

    public String getRestaurantName() {
        return RestaurantName;
    }
    public void setRestaurantName(String restaurantName) {
        RestaurantName = restaurantName;
    }

    public String getDeliveryDate() {
        return DeliveryTime;
    }
    public void setDeliveryDate(String deliveryDate) {
        DeliveryTime = deliveryDate;
    }

    public CardPayment getOrderPayment() {
        return OrderPayment;
    }
    public void setOrderPayment(CardPayment orderPayment) {
        OrderPayment = orderPayment;
    }

    public String getTransactionID() {
        return transactionID;
    }
    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }
}