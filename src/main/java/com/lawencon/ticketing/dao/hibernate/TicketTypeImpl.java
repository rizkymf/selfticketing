package com.lawencon.ticketing.dao.hibernate;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.dao.TicketTypeDao;
import com.lawencon.ticketing.model.TicketType;

@SuppressWarnings("unchecked")
@Repository("tickettype_repo_hibernate")
public class TicketTypeImpl extends BaseHibernate implements TicketTypeDao {

	@Override
	public List<TicketType> showAllType() {
		Query q = em.createQuery("from TicketType");
		return q.getResultList();
	}

	@Override
	public TicketType showTypeById(Long id) {
		Query q = em.createQuery("from TicketType where ticket_id = :ticketId").setParameter("ticketId", id);
		return (TicketType) q.getResultList().get(0);
	}

	@Override
	public void newType(TicketType type) {
		em.persist(type);
	}

	@Override
	public TicketType updateType(TicketType type) {
		type = em.merge(type);
		return type;
	}

	@Override
	public void deleteAllType() {
		Query q = em.createQuery("delete from TicketType");
		q.executeUpdate();
	}

	@Override
	public void deleteType(Long id) {
		Query q = em.createQuery("delete from TicketType where type_id = :typeId").setParameter("typeId", id);
		q.executeUpdate();
	}
	
	

}
