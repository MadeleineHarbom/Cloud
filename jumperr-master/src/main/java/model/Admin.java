package model;

public class Admin extends User {

    public Admin(String name, String email, String address, int telephoneNumber, String username, String password) {
        super(name, email, address, telephoneNumber, username, password);
        super.setAdmin(1);
    }

}
