package com.lawencon.ticketing.service;

import java.util.List;

import com.lawencon.ticketing.model.Transactions;

public interface TransactionService {
	abstract List<Transactions> showAllTrx(String uname, String pwd) throws Exception;
	abstract Transactions showTrxById(Long id, String uname, String pwd) throws Exception;
	abstract void newTrx(Transactions trx, String uname, String pwd) throws Exception;
	abstract Transactions updateTrx(Transactions trx, String uname, String pwd) throws Exception;
	abstract void deleteAllTrx(String uname, String pwd) throws Exception;
	abstract void deleteTrx(Long id, String uname, String pwd) throws Exception;
	
	abstract String codeGenerate(Transactions trx);
	abstract double finalPrice(Transactions trx);
	abstract void statusCheck(Transactions trx) throws Exception;
}
