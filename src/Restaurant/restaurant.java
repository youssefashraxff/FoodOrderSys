package Restaurant;

public class restaurant {
    private String name;
    private String address;
    private String contact;
    private double rating;
    private String category;



    public restaurant(String name, String address, String contact, double rating, String category) {
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.rating = rating;
        this.category = category;
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
}

