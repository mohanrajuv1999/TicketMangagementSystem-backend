package com.project.ticketmntsys.entity;

public class AuthRequest {
	
	private String username;
	private String password;
	
	
	public AuthRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	public String getUserName() {
		return username;
	}
	public void setUserName(String userName) {
		this.username = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
