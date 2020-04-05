package com.lawencon.ticketing.dao;

import java.util.List;

import com.lawencon.ticketing.model.Transactions;

public interface TransactionDao {
	
	abstract List<Transactions> showAllTrx();
	abstract Transactions showTrxById(Long id);
	abstract void newTrx(Transactions trx);
	abstract Transactions updateTrx(Transactions trx);
	abstract void deleteAllTrx();
	abstract void deleteTrx(Long id);
	
	abstract String findCode();
	abstract int findPrice(Transactions trx);
	abstract double findDiscount(Transactions trx);
	abstract boolean findStatus(Transactions trx);

}
