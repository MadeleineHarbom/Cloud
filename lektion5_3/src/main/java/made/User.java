package made;

import java.io.Serializable;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String password;
	private String userLoginName;
	
	public User(String userLoginName, String pass) {
		
		this.userLoginName = userLoginName;
		this.password = pass;
	}
	
	public String getUserName() {
		return userLoginName;
	}
	public void setPass(String userName) {
		this.password = userName;
	}
	public String getPass() {
		return password;
	}
	public void setLoginName(String userID) {
		this.userLoginName = userID;
	}
	
	public String toString() {
		return  this.password + " " + this.userLoginName;
	}
	

}
