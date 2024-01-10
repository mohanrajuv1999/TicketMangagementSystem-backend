package com.project.ticketmntsys.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.project.ticketmntsys.entity.Status;
import com.project.ticketmntsys.entity.Ticket;
import com.project.ticketmntsys.entity.User;

public interface TicketService {
	
	public Ticket raiseTicket(Ticket ticket);

	public User findUserByEmail(String email);

	public List<Ticket> findAllTicket();

	public Ticket findByid(int id);

	public List<Ticket> listOfTicket();

	ResponseEntity<Ticket> TicketAssign(int id, String assignTo);

	Ticket ticketUpdate(int id, Ticket ticket);

	ResponseEntity<Ticket> ticketDelete(int id);

	Ticket ticketPriority(int id, String priority);

	List<Status> ticketUpdates(int id);

	Ticket ticketStatus(int id, Status status);

}
