package Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Review {
    private static final List<Review> reviews = new ArrayList<>();

    private static String comment;
    private static double rating;

    public Review(String comment, double rating) {
        this.comment = comment;
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public static Review collectReview() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Please enter your review:");
            String comment = scanner.nextLine();

            double rating = 0.0;
            boolean validInput = false;

            // Validate numeric input for rating
            while (!validInput) {
                System.out.println("Please rate the restaurant out of 5:");
                String ratingInput = scanner.nextLine();
                try {
                    rating = Double.parseDouble(ratingInput);
                    if (rating < 1 || rating > 5) {
                        System.out.println("Invalid rating. Please provide a rating between 1 and 5.");
                    } else {
                        validInput = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a numeric value for the rating.");
                }
            }
            System.out.println("Thank you! Your review has been saved.");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
        return new Review(comment, rating);
    }

    public static void displayReviews() {
        System.out.println("\nAll Reviews:");
        for (Review review : reviews) {
            System.out.println("Comment: " + review.comment);
            System.out.println("Rating: " + review.rating + "/5.0");
            System.out.println("--------------------");
        }
    }
}
