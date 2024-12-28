package app.models.Order;

import app.models.fileHandling.fileHandle;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Review {

    private  String RestaurantName;
    private  String CustomerName;
    private  String comment;
    private  double rating;

    public Review(String RestaurantName,String CustomerName,String comment, double rating) {
        this.RestaurantName = RestaurantName;
        this.CustomerName = CustomerName;
        this.comment = comment;
        this.rating = rating;
    }
    public Review() {}

    public String getComment() {
        return comment;
    }
    public double getRating() {
        return rating;
    }
    public String getRestaurantName() {
        return RestaurantName;
    }
    public String getCustomerName() {
        return CustomerName;
    }

    public Review collectReview(String RestaurantName, String CustomerName) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Please enter your review:");
            String review = scanner.nextLine();

            boolean validInput = false;

            while (!validInput) {
                System.out.println("Please rate the restaurant out of 5:");
                try {
                    double ratingInput = scanner.nextDouble();
                    scanner.nextLine();
                    if (ratingInput < 1 || ratingInput > 5) {
                        System.out.println("Invalid rating. Please provide a rating between 1 and 5.");
                    } else {
                        validInput = true;

                        fileHandle.writeReviewToFile(RestaurantName, CustomerName, review, ratingInput);

                        System.out.println("Thank you! Your review has been saved.");
                        return new Review(RestaurantName, CustomerName, review, ratingInput);
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a numeric value for the rating.");
                    scanner.nextLine();
                }
            }
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
        return null;
    }
}
