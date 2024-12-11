package Order;

import user.User;

import java.util.ArrayList;

public class cart extends order_procedure {

    public  ArrayList<order_procedure> cart_items=new ArrayList<>();
    private double total_price;

    public cart(String ItemName, String Description, double Price, int Quantity) {
        super(ItemName, Description, Price, Quantity);
        cart_items.add(new order_procedure(ItemName, Description, Price, Quantity));
        //total_price=calculateTotalPrice(cart_items);
    }
    public cart(){
        super();
    }
    // Method to display all ordered items
    public  void displayCartItems() {
        System.out.println("\nYour Order:");
        for (order_procedure item : cart_items) {
            System.out.println("Item: " + item.getItemName());
            System.out.println("Description: " + item.getDescription());
            System.out.println("Price: $" + item.getPrice());
            System.out.println("Quantity: "+item.getQuantity());
            System.out.println("---------------------------");
        }

        System.out.println("Total amount: $" + calculateTotalPrice());
    }
    public void displayCartMenu(User loggedInUser) {
        if (loggedInUser.Usercart.cart_items.isEmpty()) {
            System.out.println("Cart is empty!");
        } else {
            displayCartItems();
        }
    }
    public void addItem(order_procedure item) {
        cart_items.add(item);
        //calculateTotalPrice(cart_items);
    }

    /*public void removeItem(order_procedure item) {
        cart_items.remove(item);
        calculateTotalPrice();
    }*/

    private double calculateTotalPrice() {
        total_price = 0.0; // Reset total price
        for (order_procedure item : cart_items) {
            total_price += item.getPrice() * item.getQuantity();
        }
        return total_price;
    }

    public double getTotalPrice() {
        return total_price;
    }

    public  ArrayList<order_procedure> getItems() {
        return cart_items;
    }
}
