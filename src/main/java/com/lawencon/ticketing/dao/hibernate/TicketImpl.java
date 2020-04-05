package com.lawencon.ticketing.dao.hibernate;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.dao.TicketDao;
import com.lawencon.ticketing.model.Ticket;

@SuppressWarnings("unchecked")
@Repository
public class TicketImpl extends BaseHibernate implements TicketDao {

	@Override
	public List<Ticket> showAllTicket() {
		Query q = em.createQuery("from Ticket");
		return q.getResultList();
	}

	@Override
	public Ticket showTicketById(Long id) {
		Query q = em.createQuery("from Ticket where ticket_id = :ticketId").setParameter("ticketId", id);
		return (Ticket) q.getResultList().get(0);
	}

	@Override
	public List<Ticket> showTicketByType(Ticket ticket) {
		Query q = em.createQuery("from Ticket where type_id = :typeId").setParameter("typeId", ticket.getTypeId().getTypeId());
		return q.getResultList();
	}

	@Override
	public void newTicket(Ticket ticket) {
		em.persist(ticket);
	}

	@Override
	public Ticket updateTicket(Ticket ticket) {
		ticket = em.merge(ticket);
		return ticket;
	}

	@Override
	public void deleteAllTicket() {
		Query q = em.createQuery("delete from Ticket");
		q.executeUpdate();
	}

	@Override
	public void deleteTicketById(Long id) {
		Query q = em.createQuery("delete from Ticket where ticketId = :ticketId").setParameter("ticketId", id);
		q.executeUpdate();
	}

	
}
