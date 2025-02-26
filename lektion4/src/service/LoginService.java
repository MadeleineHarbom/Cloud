package service;

import java.util.HashMap;

import model.User;

public class LoginService {
	
	HashMap<String, String> users = new HashMap<>();
	
	public LoginService() {
		users.put("made", "Madeleine");
		users.put("slyngel", "Steffen");
		users.put("nukku", "Bertil");
		users.put("chico", "Thomas");
	}
	
	public boolean authenticate(String userID, String password) {
		if (password == null || password.length() < 1) {
			return false;
		}
		return true;
	}
	
	public User getUser(String loginName) {
		try {
			User user = new User(loginName, users.get(loginName));
			System.out.print(user.toString());
		return user;
		}
		catch (Exception e) {
			System.out.print("No user created");
			return new User("faillogin", "failname");
		}
		
		
	}

}
