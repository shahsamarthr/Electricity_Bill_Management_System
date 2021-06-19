package com.sam.Electricity_Bill_Management_System.Entity;

public class Userlogin {

	private String username;
	private String password;

	public Userlogin(String username, String password) {
		super();
		this.username = username;
		this.password = password;
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

	@Override
	public String toString() {
		return "Userlogin [username=" + username + ", password=" + password + "]";
	}

}
