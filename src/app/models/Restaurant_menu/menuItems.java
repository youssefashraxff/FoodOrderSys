package app.models.Restaurant_menu;

public class menuItems {
    private int Rest_Id;
    private String category;
    private String name;
    private String description;
    private double price;

    public menuItems(int Rest_Id, String name, double price,String description, String category) {
        this.Rest_Id = Rest_Id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {this.price = price;}
    public String getCategory() {return category;}
    public void setCategory(String category) {this.category = category;}
    public int getRest_Id() {return Rest_Id;}
}
