package com.project.ticketmntsys.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.project.ticketmntsys.auth.JwtAuthenticationEntryPoint;
//import com.project.ticketmntsys.auth.JwtAuthenticationEntryPoint;
import com.project.ticketmntsys.repository.UserRepository;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class AppSecurityConfig 
{
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@Autowired
	private JwtAuthenticationEntryPoint authenticationEntryPoint;
		

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	    @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	      http
	          .csrf().disable().authorizeHttpRequests(auth->
	        		  auth
	        		  .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
	        		  .requestMatchers("/acess/login/**").permitAll()
	        		  .requestMatchers("/acess/register/**").permitAll()
	        		  .requestMatchers("api/ticket/**").hasAnyRole("ADMIN","USER")
	        		  .anyRequest().authenticated())
	                  .exceptionHandling(exception->exception.authenticationEntryPoint(authenticationEntryPoint))
	                  .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
  	                  http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	                  http.httpBasic(Customizer.withDefaults());
	                  http.cors();
	      return http.build();
	    }
}
