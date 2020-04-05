package com.lawencon.ticketing.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lawencon.ticketing.model.TicketType;

@Service
public interface TicketTypeService {

	abstract List<TicketType> showAllType(String uname, String pwd) throws Exception;
	abstract TicketType showTypeById(Long id, String uname, String pwd) throws Exception;
	abstract void newType(TicketType type, String uname, String pwd) throws Exception;
	abstract TicketType updateType(TicketType type, String uname, String pwd) throws Exception;
	abstract void deleteAllType(String uname, String pwd) throws Exception;
	abstract void deleteType(Long id, String uname, String pwd) throws Exception;
}
