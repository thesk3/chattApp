package com.chrisvz.rands.chrisvzrands.vo;

public class UserAdd {

	private int userID;
	private int listID;
	private String name;
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getListID() {
		return listID;
	}
	public void setListID(int listID) {
		this.listID = listID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "UserAdd [userID=" + userID + ", listID=" + listID + ", name=" + name + "]";
	}
	
}
