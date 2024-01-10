package com.project.ticketmntsys.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.ticketmntsys.entity.AuthRequest;
import com.project.ticketmntsys.entity.Role;
import com.project.ticketmntsys.entity.User;
import com.project.ticketmntsys.repository.UserRepository;
import com.project.ticketmntsys.security.JwtService;

@Service
public class AuthenticationService {
	@Autowired
	private UserRepository repository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private AuthenticationManager authenticationManager;

	public String register(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		repository.save(user);
		String jwtToken = jwtService.generateToken(user.getUsername());

		System.out.println(jwtToken);

		return jwtToken;

	}

	public JwtResponse login(AuthRequest auth) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(auth.getUserName(), auth.getPassword()));
		User user = repository.findByUsername(auth.getUserName());

		String jwtToken = jwtService.generateToken(user.getUsername());
		JwtResponse jwtResponse = new JwtResponse();
		jwtResponse.setTokenType(jwtToken);
		jwtResponse.setRole(user.getRole());

		return jwtResponse;
	}
}
