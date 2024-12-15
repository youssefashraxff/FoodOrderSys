package Order;
import payments.*;
import user.Customer;

import java.util.Scanner;

public class order {
    private cart orderCart;
    private String username;
    private String DeliveryAddress;
    private String RestaurantName;
    private String DeliveryTime;
    private CardPayment OrderPayment;
    private String transactionID;
    private Review orderReview;

    public order(String username, String DeliveryAddress, CardPayment OrderPayment ,cart orderCart,String DeliveryTime) {
        this.orderCart = orderCart;
        this.OrderPayment = OrderPayment;
        this.username = username;
        this.DeliveryAddress = DeliveryAddress;
        this.DeliveryTime = DeliveryTime;
        this.RestaurantName = orderCart.getRestaurantName();
    }
    public void displayOrder(){
        System.out.println("\nOrder added successfully\n");
        System.out.println("Customer name: " + username);
        System.out.println("Restaurant Name: " + RestaurantName.toUpperCase());
        System.out.println("Delivery Address: " + DeliveryAddress);
        System.out.println("Delivery Time: " + DeliveryTime);
        System.out.print("Order Payment: ");
        if(OrderPayment.getCardType().equals("Cash")){
            System.out.println(OrderPayment.getCardType());
        }else{
            OrderPayment.displayOrderCardPaymentInfo();
        }
        System.out.println("Order : ");
        orderCart.displayOrder();
        System.out.println("\n\n");
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
    public Review getOrderReview() {
        return orderReview;
    }
    public void setOrderReview(Review orderReview) {
        this.orderReview = orderReview;
    }
}

