package com.lawencon.ticketing.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lawencon.ticketing.dao.TransactionDao;
import com.lawencon.ticketing.model.Transactions;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	@Qualifier("transaction_repo_jpa")
	TransactionDao trxDao;
	
	@Autowired
	UserService usrServ;

	@Override
	public List<Transactions> showAllTrx(String uname, String pwd) throws Exception {
		usrServ.checkUser(uname, pwd);
		return trxDao.showAllTrx();
	}

	@Override
	public Transactions showTrxById(Long id, String uname, String pwd) throws Exception {
		usrServ.checkUser(uname, pwd);
		return trxDao.showTrxById(id);
	}

	@Override
	public void newTrx(Transactions trx, String uname, String pwd) throws Exception {
		usrServ.checkUser(uname, pwd);
		statusCheck(trx);
		trxDao.newTrx(trx);
	}

	@Override
	public Transactions updateTrx(Transactions trx, String uname, String pwd) throws Exception {
		usrServ.checkUser(uname, pwd);
		return trxDao.updateTrx(trx);
	}

	@Override
	public void deleteAllTrx(String uname, String pwd) throws Exception {
		usrServ.checkUser(uname, pwd);
		trxDao.deleteAllTrx();
	}

	@Override
	public void deleteTrx(Long id, String uname, String pwd) throws Exception {
		usrServ.checkUser(uname, pwd);
		trxDao.deleteTrx(id);
	}

	@Override
	public String codeGenerate(Transactions trx) {
		String uCode = "";
		LocalDate date = LocalDate.parse(trxDao.findCode().substring(3, 9), DateTimeFormatter.ofPattern("ddMMyy"));
		String lastCode = trxDao.findCode();
		if(lastCode.equals("") || !date.equals(LocalDate.now())) {
			uCode = "-1";
		} else {
			uCode = lastCode.substring(9);
		}
		int uniqueCode = Integer.valueOf(uCode) + 1;
		String code = "TRX" + trx.getTrxDate().format(DateTimeFormatter.ofPattern("ddMMyy")) + String.format("%03d", uniqueCode);
		return code;
	}

	@Override
	public double finalPrice(Transactions trx) {
		trx.getTicketId().setPrice(trxDao.findPrice(trx));
		int ticketPrice = trx.getTicketId().getPrice();
		trx.getVoucher().setDiscount(trxDao.findDiscount(trx));
		double discount = trx.getVoucher().getDiscount();
		double finalPrice = ticketPrice - (ticketPrice * discount);
		return finalPrice;
	}
	
	@Override
	public void statusCheck(Transactions trx) throws Exception {
		if(trxDao.findStatus(trx) == false) {
			throw new Exception();
		}
	}
	
	
}
