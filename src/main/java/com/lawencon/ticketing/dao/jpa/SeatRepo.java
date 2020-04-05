package com.lawencon.ticketing.dao.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.model.Seats;

@Repository
public interface SeatRepo extends JpaRepository<Seats, Long> {

	@Query("from Seats where ticketId = ?1")
	abstract List<Seats> findSeatByTicket(Long id);
}
