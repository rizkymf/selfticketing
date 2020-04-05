package com.lawencon.ticketing.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lawencon.ticketing.model.Seats;

@Service
public interface SeatService {

	abstract List<Seats> showAllSeat(String uname, String pwd) throws Exception;
	abstract Seats showSeatById(Long id, String uname, String pwd) throws Exception;
	abstract List<Seats> showSeatByTicket(Seats seat, String uname, String pwd) throws Exception;
	abstract void newSeat(Seats seat, String uname, String pwd) throws Exception;
	abstract Seats updateSeat(Seats seat, String uname, String pwd) throws Exception;
	abstract void deleteAllSeat(String uname, String pwd) throws Exception;
	abstract void deleteSeat(Long id, String uname, String pwd) throws Exception;
	
}
