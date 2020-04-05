package com.lawencon.ticketing.dao.hibernate;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.dao.SeatDao;
import com.lawencon.ticketing.model.Seats;

@SuppressWarnings("unchecked")
@Repository("seat_repo_hibernate")
public class SeatImpl extends BaseHibernate implements SeatDao {

	@Override
	public List<Seats> showAllSeat() {
		Query q = em.createQuery("from Seats");
		return q.getResultList();
	}

	@Override
	public Seats showSeatById(Long id) {
		Query q = em.createQuery("from Seats where seat_id = :seatId")
				.setParameter("seatId", id);
		return (Seats) q.getResultList().get(0);
	}

	@Override
	public List<Seats> showSeatByTicket(Seats seat) {
		Query q = em.createQuery("from Seats where ticket_id = :ticketId")
				.setParameter("ticketId", seat.getTicket().getTicketId());
		return q.getResultList();
	}

	@Override
	public void newSeat(Seats seat) {
		em.persist(seat);
	}

	@Override
	public Seats updateSeat(Seats seat) {
		seat = em.merge(seat);
		return seat;
	}

	@Override
	public void deleteAllSeat() {
		Query q = em.createQuery("delete from Seats");
		q.executeUpdate();
	}

	@Override
	public void deleteSeat(Long id) {
		Seats seat = em.find(Seats.class, id);
		em.remove(seat);
	}

}
