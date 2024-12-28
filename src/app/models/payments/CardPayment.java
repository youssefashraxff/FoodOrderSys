package app.models.payments;

import app.models.fileHandling.fileHandle;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class CardPayment{
    static Scanner input = new Scanner(System.in);

    private String userID;
    private String paymentMethod;
    private String cardNumber;
    private String expiryDate;
    private String cvv;
    private String cardHolderName;

    public CardPayment(String userID,String paymentMethod,String cardNumber, String expiryDate, String cvv, String cardHolderName) {
        this.paymentMethod = paymentMethod;
        this.userID = userID;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
        this.cardHolderName = cardHolderName;
    }
    public CardPayment() {

    }
    public CardPayment(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    public String getCardType(){
        return paymentMethod;
    }
    public String getCardNumber() {
        return cardNumber;
    }
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    public String getExpiryDate() {
        return expiryDate;
    }
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }
    public String getCvv() {
        return cvv;
    }
    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
    public String getCardHolderName() {
        return cardHolderName;
    }
    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    //Validation Methods
    private  boolean validateCardNumber(String input_CardNumber) {
        return input_CardNumber!=null && input_CardNumber.matches("\\d{16}");
    }
    private  boolean validateExpiryDate(String input_expiryDate) {
        if( input_expiryDate==null || !input_expiryDate.matches("^(0[1-9]|1[0-2])/\\d{2}$")){
            return false;
        }
        String[] expDate =input_expiryDate.split("/");
        int year = Integer.parseInt(expDate[1])+2000;
        int month = Integer.parseInt(expDate[0]);
        LocalDate expiry = LocalDate.of(year, month, 1).plusMonths(1).minusDays(1);
        return expiry.isAfter(LocalDate.now());
    }
    private  boolean validateCvv(String input_cvv) {
        return input_cvv!=null && input_cvv.matches("\\d{3}");
    }

    private  boolean processPayment(String input_CardNumber,String input_expiryDate,String input_cvv,String cardHolderName) {
        System.out.println("Validating credit card details...");
        if (!validateCardNumber(input_CardNumber)) {
            System.out.println("Invalid card number.");
            return false;
        }
        if (!validateExpiryDate(input_expiryDate)) {
            System.out.println("Invalid or expired card.");
            return false;
        }
        if (!validateCvv(input_cvv)) {
            System.out.println("Invalid CVV.");
            return false;
        }

        // Simulate payment processing
        System.out.println("Processing credit card payment for " + cardHolderName + "...");
        return true; // Payment successful
    }
    public CardPayment add_CreditCard_info(String TempUserId) {
        String input_CardType;
        String input_CardNumber;
        String input_ExpiryDate;
        String input_Cvv;
        String input_CardHolder;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Card Type: ");
        input_CardType = scanner.nextLine(); // This works correctly now

        do {
            System.out.println("Enter Card Number: ");
            input_CardNumber = scanner.nextLine();
            if (!validateCardNumber(input_CardNumber)) {
                System.out.println("Invalid Card Number. Please enter a valid 16-digit number.");
            }
        } while (!validateCardNumber(input_CardNumber));

        do {
            System.out.println("Enter Expiry Date (MM/YY): ");
            input_ExpiryDate = scanner.nextLine();
            if (!validateExpiryDate(input_ExpiryDate)) {
                System.out.println("Invalid Expiry Date. Please enter a valid MM/YY format.");
            }
        } while (!validateExpiryDate(input_ExpiryDate));

        do {
            System.out.println("Enter CVV: ");
            input_Cvv = scanner.nextLine();
            if (!validateCvv(input_Cvv)) {
                System.out.println("Invalid CVV. Please enter a 3-digit number.");
            }
        } while (!validateCvv(input_Cvv));

        System.out.println("Enter Card Holder Name: ");
        input_CardHolder = scanner.nextLine();

        return new CardPayment(TempUserId, input_CardType, input_CardNumber, input_ExpiryDate, input_Cvv, input_CardHolder);
    }
    public static boolean displayCardInfo(String userID){
        ArrayList<CardPayment> userCards = fileHandle.readPaymentMethodFromFile(userID);

        int userChoice;
        if (userCards.isEmpty()) {
            System.out.println("No payment found.");
        }
        else {
            for(CardPayment u : userCards){
                String tempCardNumber = u.getCardNumber();
                tempCardNumber = tempCardNumber.substring(12,16);

                System.out.println("\nCard Holder Name: " + u.getCardHolderName());
                System.out.println("Card Type: "+ u.getCardType());
                System.out.println("Card Number: **** **** **** " + tempCardNumber);
                System.out.println("=================================");
            }
        }
        while(true){
            System.out.println("\nAdd payment method? ");
            System.out.println("1. Yes\n2. No");
            userChoice = input.nextInt();
            if (userChoice == 1) {
                return true;
            }
            else if (userChoice == 2) {
                return false;
            }
            else {
                System.out.println("Invalid choice.");
            }
        }
    }
    public static CardPayment chooseCardForOrder(ArrayList<CardPayment> userCards) {
        Scanner scanner = new Scanner(System.in);
        String chosenCardNumber;
        CardPayment chosenCard = null;

        // Debug: Print all available card numbers
        System.out.println("Available cards:");
        for (CardPayment card : userCards) {
            System.out.println("Card: **** **** **** "+card.getCardNumber().substring(12));
        }

        while (chosenCard == null) {
            System.out.println("Choose card (Enter last 4-digits): ");
            chosenCardNumber = scanner.nextLine().trim(); // Trim to remove unwanted spaces

            for (CardPayment card : userCards) {

                if (card.getCardNumber().endsWith(chosenCardNumber)) {
                    System.out.println("Payment successfully chosen.");
                    chosenCard = card;
                    break; // Break the loop once the card is found
                }
            }

            if (chosenCard == null) {
                System.out.println("Invalid card number. Please enter a valid card number.");
            }
        }
        return chosenCard;
    }
    public void displayOrderCardPaymentInfo(){
        System.out.println(getCardType()+" ending with "+getCardNumber().substring(12));
    }
}
