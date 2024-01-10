package com.project.ticketmntsys.auth;

public class JwtResponse {

	
	private String tokenType = "Bearer ";
	private String role;

	public JwtResponse( String tokenType, String role) {
		super();
		
		this.tokenType = tokenType;
		this.role = role;
	}

	public JwtResponse() {
		// TODO Auto-generated constructor stub
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
