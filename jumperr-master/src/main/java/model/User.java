package model;

import java.io.Serializable;
import storage.LocalStorage;

public class User implements Serializable {

    private int id;
    private String name;
    private String email;
    private String address;
    private int telephoneNumber;
    private String username;
    private String password;
    private int admin;
    private String profilePicture;
    private int rating;

    public User(String name, String email, String address, int telephoneNumber, String username, String password) {
        this.id = generateUniqueID();
        this.name = name;
        this.email = email;
        this.address = address;
        this.telephoneNumber = telephoneNumber;
        this.username = username;
        this.password = password;
        this.admin = 0;
        setProfilePicture("avatar.png");
        setRating(0);
    }

    // bliver brugt når User-objekterne hentes fra databasen og skal oprettes som
    // User-objekter
    public User(int id, String name, String email, String address, int telephoneNumber, String username,
            String password, int admin) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.telephoneNumber = telephoneNumber;
        this.username = username;
        this.password = password;
        this.admin = admin;
        setProfilePicture("avatar.png");
        setRating(0);
    }

    public int generateUniqueID() {
        int id = 0;

        // hvis det er første bruger, får brugeren ID = 1 ellers får brugeren den sidste
        // brugeres id +1
        if (LocalStorage.getUsers().size() == 0) {
            id = 1;
        } else {
            int lastUserIndex = LocalStorage.getUsers().size() - 1;
            id = LocalStorage.getUsers().get(lastUserIndex).id + 1;
        }
        return id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(int telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    // et user-objekt er det samme hvis det har samme username
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        User other = (User) obj;
        if (username == null) {
            if (other.username != null) {
                return false;
            }
        } else if (!username.equals(other.username)) {
            return false;
        }
        return true;
    }

}
