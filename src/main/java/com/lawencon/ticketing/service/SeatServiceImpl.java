package com.lawencon.ticketing.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lawencon.ticketing.dao.SeatDao;
import com.lawencon.ticketing.model.Seats;

@Transactional
@Service
public class SeatServiceImpl implements SeatService {

	@Autowired
	@Qualifier("seat_repo_jpa")
	private SeatDao seatDao;
	
	@Autowired
	private UserService usrServ;

	@Override
	public List<Seats> showAllSeat(String uname, String pwd) throws Exception {
		usrServ.checkUser(uname, pwd);
		return seatDao.showAllSeat();
	}

	@Override
	public Seats showSeatById(Long id, String uname, String pwd) throws Exception {
		usrServ.checkUser(uname, pwd);
		return seatDao.showSeatById(id);
	}

	@Override
	public List<Seats> showSeatByTicket(Seats seat, String uname, String pwd) throws Exception {
		usrServ.checkUser(uname, pwd);
		return seatDao.showSeatByTicket(seat);
	}

	@Override
	public void newSeat(Seats seat, String uname, String pwd) throws Exception {
		usrServ.checkUser(uname, pwd);
		seatDao.newSeat(seat);
	}

	@Override
	public Seats updateSeat(Seats seat, String uname, String pwd) throws Exception {
		usrServ.checkUser(uname, pwd);
		return seatDao.updateSeat(seat);
	}

	@Override
	public void deleteAllSeat(String uname, String pwd) throws Exception {
		usrServ.checkUser(uname, pwd);
		seatDao.deleteAllSeat();
	}

	@Override
	public void deleteSeat(Long id, String uname, String pwd) throws Exception {
		usrServ.checkUser(uname, pwd);
		seatDao.deleteSeat(id);
	}
	
	
}
