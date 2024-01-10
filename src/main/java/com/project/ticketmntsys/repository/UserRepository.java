package com.project.ticketmntsys.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.project.ticketmntsys.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> 
{
	
	User findByUsername(String username);

	User findByEmail(String email);

	User save(boolean pass);

}