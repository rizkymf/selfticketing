package com.lawencon.ticketing.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lawencon.ticketing.dao.TicketDao;
import com.lawencon.ticketing.model.Ticket;

@Transactional
@Service
public class TicketServiceImpl implements TicketService {
	
	@Autowired
	@Qualifier("ticket_repo_jpa")
	TicketDao ticketDao;
	
	@Autowired
	UserService usrServ;

	@Override
	public List<Ticket> showAllTicket(String uname, String pwd) throws Exception {
		usrServ.checkUser(uname, pwd);
		return ticketDao.showAllTicket();
	}

	@Override
	public Ticket showTicketById(Long id, String uname, String pwd) throws Exception {
		usrServ.checkUser(uname, pwd);
		return ticketDao.showTicketById(id);
	}

	@Override
	public List<Ticket> showTicketByType(Ticket ticket, String uname, String pwd) throws Exception {
		usrServ.checkUser(uname, pwd);
		return ticketDao.showTicketByType(ticket);
	}

	@Override
	public void newTicket(Ticket ticket, String uname, String pwd) throws Exception {
		usrServ.checkUser(uname, pwd);
		ticketDao.newTicket(ticket);
	}

	@Override
	public Ticket updateTicket(Ticket ticket, String uname, String pwd) throws Exception {
		usrServ.checkUser(uname, pwd);
		return ticketDao.updateTicket(ticket);
	}

	@Override
	public void deleteAllTicket(String uname, String pwd) throws Exception {
		usrServ.checkUser(uname, pwd);
		ticketDao.deleteAllTicket();
	}

	@Override
	public void deleteTicketById(Long id, String uname, String pwd) throws Exception {
		usrServ.checkUser(uname, pwd);
		ticketDao.deleteTicketById(id);
	}
	
	
}
