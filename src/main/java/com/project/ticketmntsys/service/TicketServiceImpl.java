package com.project.ticketmntsys.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.project.ticketmntsys.entity.Status;
import com.project.ticketmntsys.entity.Ticket;
import com.project.ticketmntsys.entity.User;
import com.project.ticketmntsys.repository.TicketRepository;
import com.project.ticketmntsys.repository.UserRepository;

@Service
public class TicketServiceImpl implements  TicketService{

	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private UserRepository userrepositiry;
	
	@Override
	public Ticket raiseTicket(Ticket ticket) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = ((UserDetails) auth.getPrincipal()).getUsername();
		Date currentDate = new Date();
		ticket.setStage("TICKET_RAISED");
		ticket.setDateOpened(currentDate);
		ticket.setCreatedBy(username);
		System.out.println(username);
		
		return ticketRepository.save(ticket);
	}

	@Override
    public User findUserByEmail(String email) {
        return userrepositiry.findByEmail(email);
    }

	@Override
	public List<Ticket> findAllTicket() {
		
		return ticketRepository.findAll();
		
	}

	@Override
	public Ticket findByid(int id) {
		
		return ticketRepository.findById(id).get();
	}

	@Override
	public List<Ticket> listOfTicket() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = ((UserDetails) auth.getPrincipal()).getUsername();
		User user = userrepositiry.findByUsername(username);
		if (user.getRole().equals("USER")) {
			return ticketRepository.findByassigned_to(user.getEmail());
		}
		return ticketRepository.findAll();
	}

	@Override
	public ResponseEntity<Ticket> TicketAssign(int id, String assignTo) {
		
		Ticket ticket = ticketRepository.findById(id).get();
		ticket.setStage("ASSIGNED");
		ticket.setassigned_to(assignTo);
		ticketRepository.save(ticket);
		return new ResponseEntity<Ticket>(HttpStatus.OK);
	}
	
	@Override
	public Ticket ticketStatus(int id, Status status) {
		Ticket ticket = ticketRepository.findById(id).get();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = ((UserDetails) auth.getPrincipal()).getUsername();
		status.setAuthor(username);
		ticket.addUpdate(status);
		ticket.setLastUpdated(new Date());
		return ticketRepository.save(ticket);
	}

	@Override
	public List<Status> ticketUpdates(int id) {
		Ticket ticket = ticketRepository.findById(id).get();
		return ticket.getUpdates();
	}

	@Override
	public Ticket ticketPriority(int id, String priority) {
		Ticket ticket = ticketRepository.findById(id).get();
		ticket.setPriority(priority);
		return ticketRepository.save(ticket);
		
	}

	@Override
	public ResponseEntity<Ticket> ticketDelete(int id) {
		Ticket ticket = ticketRepository.findById(id).get();
		 ticketRepository.delete(ticket);
		 return new ResponseEntity<Ticket>(HttpStatus.OK);
		
	}

	@Override
	public Ticket ticketUpdate(int id, Ticket ticket) {
		
		Ticket ticket1 = ticketRepository.findById(id).get();

		ticket1.setDescription(ticket.getDescription());
		ticket1.setStage(ticket.getStage());
		return ticketRepository.save(ticket1);
	}
	

}
