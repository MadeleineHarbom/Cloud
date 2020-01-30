package made;

import java.util.HashMap;


public class LoginService {
	
	HashMap<String, String> users = new HashMap<>();
	
	public LoginService() {
		Storage.users.add(new User("made", "Madeleine"));
		Storage.users.add(new User("slyngel", "Steffen"));
		Storage.users.add(new User("nukku", "Bertil"));
		Storage.users.add(new User("chico", "Thomas"));
	}
	
	public boolean authenticate(String userID, String password) {
		if (password == null || password.length() < 1) {
			System.out.print("\n not authenticated");
			return false;
		}
		System.out.print("\n authenticated");
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
