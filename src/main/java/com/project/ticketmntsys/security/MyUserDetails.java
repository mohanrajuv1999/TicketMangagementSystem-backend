package com.project.ticketmntsys.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.ticketmntsys.entity.Role;
import com.project.ticketmntsys.entity.User;
import com.project.ticketmntsys.repository.UserRepository;

import io.jsonwebtoken.Claims;

@Service
public class MyUserDetails implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	JwtService jwtService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);

		System.out.println("username = " + user.getUsername() + " ," + user.getPassword());

		 GrantedAuthority simpleGrantedAuthority = new
		 SimpleGrantedAuthority("ROLE_"+user.getRole());

		return new org.springframework.security.core.userdetails.User(username, user.getPassword(), Arrays.asList(simpleGrantedAuthority));
	}

//	public List<SimpleGrantedAuthority> getAuthority(User user) {
//		List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
//				.map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
//
//		return authorities;
//	}

//	Set<Role> role1 = user.getRoles();
//	System.out.println(role1);
//	String role2 = role1.toString();
//	String role3 = role2.replace("[", "").replace("]", "");
//	System.out.println(role3);

//	public Set<GrantedAuthority> getAuthority(User user) {
//		Set<Role> role1 = user.getRoles();
//		
//		System.out.println("role My"+user.getRoles());
//		  Set<GrantedAuthority> authorities = new HashSet<>();
//	        user.getRoles().forEach(role -> {
//	            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRole()));
//	        });
//	        return authorities;
//        
//    }	

//	private UserDetails getUserDetails(String token) {
//	    User userDetails = new User();
//	    Claims claims = jwtService.parseClaims(token);
//	    String subject = (String) claims.get(Claims.SUBJECT);
//	    String roles = (String) claims.get("roles");
//	     
//	    roles = roles.replace("[", "").replace("]", "");
//	    String[] roleNames = roles.split(",");
//	     
//	    for (String aRoleName : roleNames) {
//	        userDetails.addRole(new Role(aRoleName));
//	    }
//	     
//	    String[] jwtSubject = subject.split(",");
//	 
//	    userDetails.setId(Integer.parseInt(jwtSubject[0]));
//	    userDetails.setEmail(jwtSubject[1]);
//	 
//	    return userDetails;
//	}
}
