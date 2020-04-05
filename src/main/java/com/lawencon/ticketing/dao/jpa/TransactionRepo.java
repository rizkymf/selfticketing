package com.lawencon.ticketing.dao.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.model.Transactions;

@Repository
public interface TransactionRepo extends JpaRepository<Transactions, Long> {

	@Query(value = "select trx_code from Transactions order by trx_code desc limit 1", nativeQuery = true)
	abstract List<String> getCode();
	
	@Query(value = "select price from Ticket where ticket_id = ?1", nativeQuery = true)
	abstract int getPrice(Long ticketId);
	
	@Query(value = "select discount from Vouchers where voucher_id = ?1", nativeQuery = true)
	abstract double getDiscount(Long voucherId);
	
	@Query(value = "select status from Seats where ticket_id = ?1 and seat_code = ?2", nativeQuery = true)
	abstract String getStatus(Long ticketId, String seatCode);
}
