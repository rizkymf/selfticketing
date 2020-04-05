package com.lawencon.ticketing.dao.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.dao.TicketDao;
import com.lawencon.ticketing.model.Ticket;

@Repository("ticket_repo_jpa")
public class TicketImpl implements TicketDao {

	@Autowired
	TicketRepo ticketRepo;

	@Override
	public List<Ticket> showAllTicket() {
		return ticketRepo.findAll();
	}

	@Override
	public Ticket showTicketById(Long id) {
		return ticketRepo.findById(id).orElse(null);
	}

	@Override
	public List<Ticket> showTicketByType(Ticket ticket) {
		return ticketRepo.showTicketByType(ticket.getTypeId().getTypeId());
	}

	@Override
	public void newTicket(Ticket ticket) {
		ticketRepo.save(ticket);
	}

	@Override
	public Ticket updateTicket(Ticket ticket) {
		ticket = ticketRepo.save(ticket);
		return ticket;
	}

	@Override
	public void deleteAllTicket() {
		ticketRepo.deleteAll();
	}

	@Override
	public void deleteTicketById(Long id) {
		ticketRepo.deleteById(id);
	}
	
	
}
