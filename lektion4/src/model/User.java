package model;

public class User {
	private String userName;
	private String userLoginName;
	
	public User(String userLoginName, String userName) {
		this.userName = userName;
		this.userLoginName = userLoginName;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserID() {
		return userLoginName;
	}
	public void setUserID(String userID) {
		this.userLoginName = userID;
	}
	
	public String toString() {
		return  this.userName + " " + this.userLoginName;
	}
	

}
