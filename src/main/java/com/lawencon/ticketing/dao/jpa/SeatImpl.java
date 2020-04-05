package com.lawencon.ticketing.dao.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.dao.SeatDao;
import com.lawencon.ticketing.model.Seats;

@Repository("seat_repo_jpa")
public class SeatImpl implements SeatDao {

	@Autowired
	private SeatRepo seatRepo;

	@Override
	public List<Seats> showAllSeat() {
		return seatRepo.findAll();
	}

	@Override
	public Seats showSeatById(Long id) {
		return seatRepo.findById(id).orElse(null);
	}

	@Override
	public List<Seats> showSeatByTicket(Seats seat) {
		return seatRepo.findSeatByTicket(seat.getTicket().getTicketId());
	}

	@Override
	public void newSeat(Seats seat) {
		seatRepo.save(seat);
	}

	@Override
	public Seats updateSeat(Seats seat) {
		seat = seatRepo.save(seat);
		return seat;
	}

	@Override
	public void deleteAllSeat() {
		seatRepo.deleteAll();
	}

	@Override
	public void deleteSeat(Long id) {
		seatRepo.deleteById(id);
	}
	
	
}
