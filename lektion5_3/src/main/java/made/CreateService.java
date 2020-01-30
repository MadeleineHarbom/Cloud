package made;

public class CreateService {
	
	public User createUser(String userName, String password) {
		User u = new User(userName, password);
		Storage.users.add(u);
		return u;
	}
}
