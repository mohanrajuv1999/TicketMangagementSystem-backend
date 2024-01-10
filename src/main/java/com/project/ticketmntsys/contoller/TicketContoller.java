package com.project.ticketmntsys.contoller;

import java.util.Date;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ticketmntsys.entity.Status;
import com.project.ticketmntsys.entity.Ticket;
import com.project.ticketmntsys.entity.User;
import com.project.ticketmntsys.repository.StatusRepository;
import com.project.ticketmntsys.repository.TicketRepository;
import com.project.ticketmntsys.repository.UserRepository;
//import com.project.ticketmntsys.security.MyUserDetails;
import com.project.ticketmntsys.service.TicketServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/ticket")
public class TicketContoller {

	@Autowired
	private TicketServiceImpl ticketServiceImpl;

	@Autowired
	private TicketRepository ticketrepository;

	@Autowired
	private UserRepository userrepositiry;

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/createTicket")
	public Ticket createTicket(@RequestBody @Valid Ticket ticket) {
		return ticketServiceImpl.raiseTicket(ticket);
	}

	// @PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/all")
	public List<Ticket> ticketsListAll() {

		return ticketServiceImpl.listOfTicket();

	}

	@GetMapping("/ticket/{id}")
	public Ticket oneTicket(@PathVariable int id) {
		return ticketServiceImpl.findByid(id);
	}

	@PutMapping("/assign/{id}/{assignTo}")
	public ResponseEntity<Ticket> assignTicket(@PathVariable int id, @PathVariable String assignTo) {

		return ticketServiceImpl.TicketAssign(id, assignTo);
		
	}

	@PostMapping("/status/{id}")
	public Ticket statusUpdate(@PathVariable int id, @RequestBody Status status) {
		return ticketServiceImpl.ticketStatus(id, status);
	}

	@GetMapping("/ticket/{id}/status")
	public List<Status> statusGet(@PathVariable int id) {
		return ticketServiceImpl.ticketUpdates(id);
	}

	@PutMapping("/update/{id}")
	public Ticket updateTicket(@PathVariable int id, @RequestBody Ticket ticket) {
		return ticketServiceImpl.ticketUpdate(id,ticket);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Ticket> deleteTicket(@PathVariable int id) {
		
		return ticketServiceImpl.ticketDelete(id);
		
	}

	@PutMapping("/priority/{id}/{priority}")
	public Ticket priorityTicket(@PathVariable int id, @PathVariable String priority) {
		
		return ticketServiceImpl.ticketPriority(id,priority);
		
	}
}
