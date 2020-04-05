package com.lawencon.ticketing.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lawencon.ticketing.dao.TicketTypeDao;
import com.lawencon.ticketing.model.TicketType;

@Transactional
@Service
public class TicketTypeServiceImpl implements TicketTypeService {

	@Autowired
	@Qualifier("tickettype_repo_jpa")
	TicketTypeDao typeDao;
	
	@Autowired
	UserService userServ;

	@Override
	public List<TicketType> showAllType(String uname, String pwd) throws Exception {
		userServ.checkUser(uname, pwd);
		return typeDao.showAllType();
	}

	@Override
	public TicketType showTypeById(Long id, String uname, String pwd) throws Exception {
		userServ.checkUser(uname, pwd);
		return typeDao.showTypeById(id);
	}

	@Override
	public void newType(TicketType type, String uname, String pwd) throws Exception {
		userServ.checkUser(uname, pwd);
		typeDao.newType(type);
	}

	@Override
	public TicketType updateType(TicketType type, String uname, String pwd) throws Exception {
		userServ.checkUser(uname, pwd);
		return typeDao.updateType(type);
	}

	@Override
	public void deleteAllType(String uname, String pwd) throws Exception {
		userServ.checkUser(uname, pwd);
		typeDao.deleteAllType();
	}

	@Override
	public void deleteType(Long id, String uname, String pwd) throws Exception {
		userServ.checkUser(uname, pwd);
		typeDao.deleteType(id);
	}
	
}
