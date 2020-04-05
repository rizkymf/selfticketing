package com.lawencon.ticketing.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lawencon.ticketing.model.Ticket;

@Service
public interface TicketService {
	abstract List<Ticket> showAllTicket(String uname, String pwd) throws Exception;
	abstract Ticket showTicketById(Long id, String uname, String pwd) throws Exception;
	abstract List<Ticket> showTicketByType(Ticket ticket, String uname, String pwd) throws Exception;
	abstract void newTicket(Ticket ticket, String uname, String pwd) throws Exception;
	abstract Ticket updateTicket(Ticket ticket, String uname, String pwd) throws Exception;
	abstract void deleteAllTicket(String uname, String pwd) throws Exception;
	abstract void deleteTicketById(Long id, String uname, String pwd) throws Exception;
}
