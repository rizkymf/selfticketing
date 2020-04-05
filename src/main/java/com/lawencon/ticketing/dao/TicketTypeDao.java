package com.lawencon.ticketing.dao;

import java.util.List;

import com.lawencon.ticketing.model.TicketType;

public interface TicketTypeDao {

	abstract List<TicketType> showAllType();
	abstract TicketType showTypeById(Long id);
	abstract void newType(TicketType type);
	abstract TicketType updateType(TicketType type);
	abstract void deleteAllType();
	abstract void deleteType(Long id);
}
