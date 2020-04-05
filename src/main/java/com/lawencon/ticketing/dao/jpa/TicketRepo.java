package com.lawencon.ticketing.dao.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.model.Ticket;

@Repository
public interface TicketRepo extends JpaRepository<Ticket, Long> {

	@Query(value = "select * from ticket where typeId = ?1", nativeQuery = true)
	abstract List<Ticket> showTicketByType(Long typeId);
}
