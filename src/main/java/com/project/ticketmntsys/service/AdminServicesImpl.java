package com.project.ticketmntsys.service;

import java.util.List;

import org.hibernate.annotations.Bag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import com.project.ticketmntsys.entity.User;
import com.project.ticketmntsys.entity.resetPassword;
import com.project.ticketmntsys.repository.UserRepository;

@Service
public class AdminServicesImpl implements AdminServices {

	@Autowired
	private UserRepository userrepositiry;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public ResponseEntity<User> createUsers(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userrepositiry.save(user);
		return new ResponseEntity<>(user, HttpStatus.OK);

	}

	@Override
	public User findUserByEmail(String email) {
		return userrepositiry.findByEmail(email);
	}

	@Override
	public List<User> findAllUsers() {

		return userrepositiry.findAll();
	}

	@Override
	public User updateUser(Integer id, User user) {

		User user1 = userrepositiry.findById(id).get();// .orElse(new Employee());
		
		user1.setFirstname(user.getFirstname());
		user1.setLastname(user.getLastname());
		user1.setEmail(user.getEmail());
		User updateduser = userrepositiry.save(user1);

		return updateduser;
	}

	@Override
	public String deleteUser(int id) {
		
		 userrepositiry.deleteById(id);
		 
		 return "deleted Sucessfull";
	}
	
	@Override
	public User Userdeatils(String gmail) {
		return userrepositiry.findByEmail(gmail);
	}

	public User UserdeatilsByGmail(String gmail) {
		
		return null;
	}

	@Override
	public String passwordReset(String gmail, resetPassword resetPassword) {
		
		if(resetPassword.getNewPassword().equals(resetPassword.getConfirmPasswoord()))
		{
		User user = userrepositiry.findByEmail(gmail);
		   
		boolean pass = bCryptPasswordEncoder.matches(resetPassword.getOldPassword(), user.getPassword());
		
		if(pass=true)
		{
			user.setPassword(bCryptPasswordEncoder.encode(resetPassword.getNewPassword()));
			 userrepositiry.save(user);
			 return "Password updated";
		}
		}
		return "Not updated";
		
	}

}
