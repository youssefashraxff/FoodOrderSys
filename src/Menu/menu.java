package Menu;

public class menu{
    private String name;
    private String description;
    private double price;

    public menu(String name, String description, double price) {
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
