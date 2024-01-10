package com.project.ticketmntsys.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.project.ticketmntsys.entity.User;
import com.project.ticketmntsys.entity.resetPassword;

public interface AdminServices {
	
	public ResponseEntity<User> createUsers(User user);
	
	public User findUserByEmail(String email);

	List<User> findAllUsers();

	User updateUser(Integer id, User user);

	String deleteUser(int id);

	User Userdeatils(String gmail);

	String passwordReset(String gmail, resetPassword resetPassword);

}
