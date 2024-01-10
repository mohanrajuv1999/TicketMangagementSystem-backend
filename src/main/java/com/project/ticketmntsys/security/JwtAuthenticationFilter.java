package com.project.ticketmntsys.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.project.ticketmntsys.entity.Role;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtService jwtService;
	@Autowired
	private MyUserDetails myUserDetails;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String authHeader = request.getHeader("Authorization");
		final String jwt;
		final String userName;
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
		}

		if (authHeader != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			jwt = authHeader.substring(7);
			System.out.println(jwt);
			userName = jwtService.extractUsername(jwt);
			System.out.println(userName);

			UserDetails userDetails = myUserDetails.loadUserByUsername(userName);
			System.out.println(userDetails.getUsername());

			System.out.println(userDetails.getAuthorities());

			if (jwtService.validateToken(jwt, userDetails)) {
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
						null, userDetails.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
			filterChain.doFilter(request, response);
		}
	}

//	private UserDetails getUserDetail(String token) {
//		Claims claims = jwtService.parseClaims(token);
//		String subject = (String) claims.get(Claims.SUBJECT);
//		String roles = (String) claims.get("roles");
//
//		roles = roles.replace("[", "").replace("]", "");
//		String[] roleNames = roles.split(",");
//
//		for (String aRoleName : roleNames) {
//			myUserDetails.addRole(new Role(aRoleName));
//		}
//
//		String[] jwtSubject = subject.split(",");
//
//		userDetails.setId(Integer.parseInt(jwtSubject[0]));
//		userDetails.setEmail(jwtSubject[1]);
//
//		return userDetails;
//	}
}
