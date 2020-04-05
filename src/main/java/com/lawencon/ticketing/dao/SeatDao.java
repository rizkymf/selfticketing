package com.lawencon.ticketing.dao;

import java.util.List;

import com.lawencon.ticketing.model.Seats;

public interface SeatDao {

	abstract List<Seats> showAllSeat();
	abstract Seats showSeatById(Long id);
	abstract List<Seats> showSeatByTicket(Seats seat);
	abstract void newSeat(Seats seat);
	abstract Seats updateSeat(Seats seat);
	abstract void deleteAllSeat();
	abstract void deleteSeat(Long id);
}
