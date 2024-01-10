package com.project.ticketmntsys.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.project.ticketmntsys.entity.Ticket;
import com.project.ticketmntsys.entity.User;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

	@Query("from Ticket t where t.assigned_to=:email")
	public List<Ticket> findByassigned_to(@Param("email") String email);


	
}
