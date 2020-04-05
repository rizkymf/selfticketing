package com.lawencon.ticketing.dao.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.dao.TicketTypeDao;
import com.lawencon.ticketing.model.TicketType;

@Repository("tickettype_repo_jpa")
public class TicketTypeImpl implements TicketTypeDao {

	@Autowired
	TicketTypeRepo typeRepo;

	@Override
	public List<TicketType> showAllType() {
		return typeRepo.findAll();
	}

	@Override
	public TicketType showTypeById(Long id) {
		return typeRepo.findById(id).orElse(null);
	}

	@Override
	public void newType(TicketType type) {
		typeRepo.save(type);
	}

	@Override
	public TicketType updateType(TicketType type) {
		type = typeRepo.save(type);
		return type;
	}

	@Override
	public void deleteAllType() {
		typeRepo.deleteAll();
	}

	@Override
	public void deleteType(Long id) {
		typeRepo.deleteById(id);
	}
	
	
}
