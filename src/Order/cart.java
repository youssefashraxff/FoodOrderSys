package Order;

import Restaurant.displayMenu;
import user.Customer;

import java.util.ArrayList;
import java.util.Scanner;

public class cart extends order_procedure {
    Scanner input = new Scanner(System.in);

    private String RestaurantName;
    private ArrayList<order_procedure> cart_items=new ArrayList<>();
    private double total_price;

    public cart(String ItemName, String Description, double Price, int Quantity) {
        super(ItemName, Description, Price, Quantity);
        cart_items.add(new order_procedure(ItemName, Description, Price, Quantity));
    }
    public cart(){
        super();
    }
    // Method to display all ordered items
    public void displayCartItems() {
        System.out.println("\nYour Order:");
        for (order_procedure item : cart_items) {
            System.out.println(item.getQuantity()+" x "+item.getItemName());
            System.out.println("$"+item.getPrice()+" each");
        }
        System.out.println("---------------------------");
        System.out.println("Total amount: $" + calculateTotalPrice());
    }
    public boolean displayCartMenu() {
        boolean check1 = true;
        boolean check2 = true;
        boolean edits_On_Cart = false;
        boolean addToOrderCheck = false ;
        int userChoice2;
        int userChoice3;
        String removedItem;

        if (cart_items.isEmpty()) {
            System.out.println("Cart is empty!");
        } else {
            displayCartItems();
            while(check1){
                System.out.println("\n1. Remove item from cart\n2. Add cart to order\n3. Exit cart");
                userChoice3 = input.nextInt();
                if(userChoice3 == 1){
                    while(check2){
                        System.out.println("Enter item name: ");
                        removedItem = input.next();
                        if(removeItem(removedItem)){
                            System.out.println("Item removed successfully!\n");
                            System.out.println("Remove another item from cart?");
                            System.out.println("1. Yes\n2. No");
                            edits_On_Cart = true;
                            userChoice2 = input.nextInt();
                            if(userChoice2 == 1) {
                                if(cart_items.isEmpty()) {
                                    System.out.println("Cart is empty!");
                                    check1 = false;
                                    check2 = false;
                                }
                                else {
                                    displayCartItems();
                                }
                            }
                            else if(userChoice2 == 2) {
                                check2 = false;
                                break;
                            }
                        }
                    }
                    addToOrderCheck = false;
                }
                else if(userChoice3 == 2){
                    check1=false;
                    check2=false;
                    if (edits_On_Cart) {
                        displayCartItems();
                    }
                    addToOrderCheck = true;
                }
                else if(userChoice3 == 3){
                    check1 = false;
                }
                else {
                    System.out.println("Invalid choice");
                }
            }
        }
        return addToOrderCheck;
    }
    public void displayOrder(){
        for (order_procedure item : cart_items) {
            System.out.println(item.getQuantity()+" x "+item.getItemName());
            System.out.println("$"+item.getPrice()+" each");
        }
        System.out.println("---------------------------");
        System.out.println("Total amount: $" + calculateTotalPrice());
    }
    public void addItem(order_procedure item) {
        cart_items.add(item);
    }
    public boolean removeItem(String removedItem ) {
        int quantity;
        for (order_procedure item : cart_items) {
            if (item.getItemName().equalsIgnoreCase(removedItem)) {
                while (true){
                    System.out.println("Enter quantity to remove: ");
                    quantity = input.nextInt();
                    if(quantity > item.getQuantity()){
                        System.out.println("Quantity exceeds the maximum quantity!");
                    } else if (quantity == item.getQuantity()) {
                        cart_items.remove(item);
                        return true;
                    }
                    else {
                        item.setQuantity(item.getQuantity() - quantity);
                        return true;
                    }
                }
            }
        }
        System.out.println("Item not found!");
        return false;
    }
    public void removeCart(){
        cart_items.clear();
        total_price=0;
    }
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

    public String getRestaurantName() {
        return this.RestaurantName;
    }
    public void setRestaurantName(String RestaurantName) {
        this.RestaurantName = RestaurantName;
    }

    public  ArrayList<order_procedure> getItems() {
        return cart_items;
    }
}
