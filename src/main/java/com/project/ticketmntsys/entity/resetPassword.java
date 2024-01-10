package com.project.ticketmntsys.entity;

public class resetPassword {

	private String oldPassword;
	
	private String newPassword;

	private String confirmPasswoord;
	
	public resetPassword(String oldPassword, String newPassword, String confirmPasswoord) {
		super();
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
		this.confirmPasswoord = confirmPasswoord;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPasswoord() {
		return confirmPasswoord;
	}

	public void setConfirmPasswoord(String confirmPasswoord) {
		this.confirmPasswoord = confirmPasswoord;
	}

	
	
	
	
}
