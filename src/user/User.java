package user;
import fileHandling.fileHandle;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {

    String username;
    String password;
    String email;
    String deliveryAddress;
    static List<User> users = fileHandle.readUsersFromFile();
    public static int counter = 0;

    public User(String username,String email,String password,String deliveryAddress) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.deliveryAddress = deliveryAddress;
        counter++;
    }
//Getters and Setters
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }
    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public static boolean checkUsername(String username) {
        boolean check = false;

        for (User user : users) {
            if ((user.getUsername()).equals(username)) {
                check = true;
            }
        }
        return check;
    }

    public static boolean checkEmail(String email) {
        boolean check = false;
        for (User user : users) {
            if ((user.getEmail()).equals(email)) {
                check = true;
            }
        }
        return check;
    }

    public static boolean checkLogin(String username, String password) {
        boolean check = false;
        for (User user : users) {
            if ((user.getUsername()).equals(username) && (user.getPassword()).equals(password)) {
                check = true;
                break;
            }
        }
        return check;
    }

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public static void addUser(String username, String password, String email, String deliveryAddress)
    {
        users.add(new User(username, password, email, deliveryAddress));
        fileHandle.writeUsersToFile(users);
    }
}


