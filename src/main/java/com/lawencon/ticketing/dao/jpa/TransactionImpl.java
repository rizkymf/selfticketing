package com.lawencon.ticketing.dao.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.dao.TransactionDao;
import com.lawencon.ticketing.model.Transactions;

@Repository("transaction_repo_jpa")
public class TransactionImpl implements TransactionDao {

	@Autowired
	TransactionRepo trxRepo;

	@Override
	public List<Transactions> showAllTrx() {
		return trxRepo.findAll();
	}

	@Override
	public Transactions showTrxById(Long id) {
		return trxRepo.findById(id).orElse(null);
	}

	@Override
	public void newTrx(Transactions trx) {
		trxRepo.save(trx);
	}

	@Override
	public Transactions updateTrx(Transactions trx) {
		trx = trxRepo.save(trx);
		return trx;
	}

	@Override
	public void deleteAllTrx() {
		trxRepo.deleteAll();
	}

	@Override
	public void deleteTrx(Long id) {
		trxRepo.deleteById(id);
	}

	@Override
	public String findCode() {
		if(trxRepo.getCode().isEmpty()) {
			return "TRX200301000";
		} else {
			return trxRepo.getCode().get(0);
		}		
	}
	
	@Override
	public int findPrice(Transactions trx) {
		return trxRepo.getPrice(trx.getTicketId().getTicketId());
	}
	
	@Override
	public double findDiscount(Transactions trx) {
		return trxRepo.getDiscount(trx.getVoucher().getVoucherId());
	}
	
	@Override
	public boolean findStatus(Transactions trx) {
		if(trxRepo.getStatus(trx.getTicketId().getTicketId(), trx.getSeat().getSeatCode()).equals("AVAILABLE")) {
			return true;
		} else return false;
	}
	
}
