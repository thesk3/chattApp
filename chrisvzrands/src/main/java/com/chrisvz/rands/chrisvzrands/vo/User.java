package com.chrisvz.rands.chrisvzrands.vo;

public class User {

	private long userID;
	private String userName;
	public long getUserID() {
		return userID;
	}
	public void setUserID(long l) {
		this.userID = l;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public User(long userID, String userName) {
		super();
		this.userID = userID;
		this.userName = userName;
	}
	public User() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "User [userID=" + userID + ", userName=" + userName + "]";
	}
	
	
	
	
}
