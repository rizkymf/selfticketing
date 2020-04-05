package com.lawencon.ticketing.dao;

import java.util.List;

import com.lawencon.ticketing.model.Ticket;

public interface TicketDao {
	
	abstract List<Ticket> showAllTicket();
	abstract Ticket showTicketById(Long id);
	abstract List<Ticket> showTicketByType(Ticket ticket);
	abstract void newTicket(Ticket ticket);
	abstract Ticket updateTicket(Ticket ticket);
	abstract void deleteAllTicket();
	abstract void deleteTicketById(Long id);

}
