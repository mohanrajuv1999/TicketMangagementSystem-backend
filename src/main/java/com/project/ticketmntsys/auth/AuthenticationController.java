package com.project.ticketmntsys.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ticketmntsys.entity.AuthRequest;
import com.project.ticketmntsys.entity.User;

@RestController
@RequestMapping(path = "/acess")
public class AuthenticationController {

	@Autowired
  private  AuthenticationService authenticationService;
	

  @PostMapping("/register")
  public ResponseEntity<String> register(
      @RequestBody User user
  ) {
    return ResponseEntity.ok(authenticationService.register(user));
  }
	
  @PostMapping(path = "/login")
  public ResponseEntity<JwtResponse> authenticate(
      @RequestBody AuthRequest auth
  ) {
	  JwtResponse jwtResponse = authenticationService.login(auth);
    return ResponseEntity.ok(jwtResponse);
  }


}
