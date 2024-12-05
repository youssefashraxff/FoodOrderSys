package Restaurant_menu;

public class menuItems {
    private int Rest_Id;
    private String name;
    private String description;
    private double price;

    public menuItems(int Rest_Id, String name,  double price,String description) {
        this.Rest_Id = Rest_Id;
        this.name = name;
        this.description = description;
        this.price = price;
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
    public void setPrice(double price) {
        if (price >= 0 && price <= 1000) {
            this.price = price;
        }
    }
}
