package user;

public class Admin extends User {
    String adminID;
    public Admin(String adminID, String username, String password) {
        super(username, password);
        this.adminID = adminID;
    }
    //Getters and Setters

    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }
}
