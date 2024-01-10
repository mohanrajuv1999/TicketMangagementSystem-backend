package com.project.ticketmntsys.contoller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import com.project.ticketmntsys.entity.User;
import com.project.ticketmntsys.entity.resetPassword;
import com.project.ticketmntsys.service.AdminServicesImpl;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/admin")
public class AdminContoller {

	@Autowired
	private AdminServicesImpl adminServicesImpl;

	@PostMapping("/createUser")
	//@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<User> createUser(@RequestBody @Valid User user) {
//		user.setId(0);
//		user.setFirstname("Mohanraju");
//		user.setLastname("V");
//		user.setEmail("mohanrajuv1999@gmail.com");
//		user.setRole("ADMIN");
//		user.setUsername("mohanrajuv1999@gmail.com");
//		user.setPassword("admin123");
	return adminServicesImpl.createUsers(user);
	}

	@GetMapping("/users")
	//@PreAuthorize("hasRole('ADMIN')")
	public List<User> manageUsers() {
		return adminServicesImpl.findAllUsers();
	}

	@PutMapping("/update/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public User update(@PathVariable int id, @RequestBody User user) {
		return adminServicesImpl.updateUser(id, user);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable int id) {
		adminServicesImpl.deleteUser(id);
		return "delete succesfully";
	}
	
	@GetMapping("/get/{gmail}")
	public User getUser(@PathVariable String gmail) {
		return adminServicesImpl.Userdeatils(gmail);
		
	}
	@PutMapping("/get/updatepassword/{gmail}")
	public String resetPassword(@PathVariable String gmail,@RequestBody resetPassword resetPassword) {
		return adminServicesImpl.passwordReset(gmail,resetPassword);
		
	}

	@GetMapping("/hi")
	//@PreAuthorize("hasRole('ADMIN')")
	public User object() {

		User user = new User();

		return new User();
	}

}
