package Order;
import payments.*;
import user.Customer;

import java.util.Scanner;

public class order {
    private cart orderCart;
    private int userID;
    private String DeliveryAddress;
    private String RestaurantName;
    private String DeliveryTime;
    private CardPayment OrderPayment;
    private String transactionID;

    public order(int userID, String DeliveryAddress, CardPayment OrderPayment ,cart orderCart,String DeliveryTime) {
        this.orderCart = orderCart;
        this.OrderPayment = OrderPayment;
        this.userID = userID;
        this.DeliveryAddress = DeliveryAddress;
        this.DeliveryTime = DeliveryTime;
        this.RestaurantName = orderCart.getRestaurantName();
    }
    public void displayOrder(){
        System.out.println("\nOrder added successfully\n");
        System.out.println("Customer ID: " + userID);
        System.out.println("Restaurant Name: " + RestaurantName.toUpperCase());
        System.out.println("Delivery Address: " + DeliveryAddress);
        System.out.println("Delivery Time: " + DeliveryTime);
        System.out.print("Order Payment: ");
        OrderPayment.displayOrderCardPaymentInfo();
        System.out.println("Order : ");
        orderCart.displayOrder();
        System.out.println("\n\n");


        OrderStatus orderStatus = new OrderStatus("Order Placed");
        Thread orderTrackingThread = new Thread(orderStatus);
        System.out.println("Order tracking started. You will see updates shortly...");
        orderTrackingThread.start();
        Review.collectReview();
        Review.displayReviews();
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

    public order(cart orderCart, Customer loggenInCustomer) {
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

