package app.models.Restaurant;
import app.models.Order.Review;
import app.models.Restaurant_menu.menuItems;

import java.util.ArrayList;

public class restaurant {
    private int Id;
    private String name;
    private String address;
    private String contact;
    private double rating;
    private String category;
    private ArrayList<menuItems> items;
    private ArrayList<Review> reviews;

    public restaurant(int Id,String name, String address, String contact, double rating, String category) {
        this.Id = Id;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.rating = rating;
        this.category = category;
    }
    public int getId() {
        return Id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
    public double getRating() {
        return rating;
    }
    public void setRating(double rating) {
        if (rating >= 0 && rating <= 5) {
            this.rating = rating;
        }
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public ArrayList<menuItems> getItems() {
        return items;
    }

    public void viewMenu()
    {
        for(menuItems item:items)
        {
            System.out.println(item);
        }
    }
}

