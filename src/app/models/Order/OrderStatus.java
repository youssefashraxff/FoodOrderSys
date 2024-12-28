package app.models.Order;

public class OrderStatus implements Runnable {
    private String status;


    public OrderStatus(String status) {
        this.status = "java.java.models.Order Placed";
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void run() {
        try {
            String[] statuses = {
                    "java.java.models.Order Confirmed",
                    "Your Food Is Being Prepared",
                    "java.java.models.Order Is Out For Delivery",
                    "Your java.java.models.Order Has Been Delivered"
            };

            for (String newStatus : statuses) {
                Thread.sleep(4500); // Wait 4.5 seconds for each status update
                setStatus(newStatus);
                System.out.println("java.java.models.Order Status Updated to: " + newStatus);
            }
        } catch (InterruptedException e) {
            System.err.println("Status update interrupted.");
        }
    }
}
