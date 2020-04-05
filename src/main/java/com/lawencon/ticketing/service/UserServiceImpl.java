package com.lawencon.ticketing.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lawencon.ticketing.dao.UserDao;
import com.lawencon.ticketing.model.Users;

@Transactional
@Service
public class UserServiceImpl implements UserService {
	
//	User Service ---------------------------------------------------------------------------------------------------------------------------
	@Autowired
	@Qualifier("user_repo_jpa")
	UserDao userDao;

	@Override
	public List<Users> showAllUser(String uname, String pwd) throws Exception {
		checkUser(uname, pwd);
		return userDao.showAllUser();
	}

	@Override
	public Users showUserById(Long id, String uname, String pwd) throws Exception {
		return userDao.showUserById(id);
	}

	@Override
	public void newUser(Users user, String uname, String pwd) throws Exception {
		userDao.newUser(user);		
	}

	@Override
	public Users updateUser(Users user, String uname, String pwd) throws Exception {
		return userDao.updateUser(user);
	}

	@Override
	public void deleteAllUser(String uname, String pwd) throws Exception {
		userDao.deleteAllUser();		
	}

	@Override
	public void deleteUser(Long id, String uname, String pwd) throws Exception {
		userDao.deleteUser(id);		
	}

	@Override
	public List<Users> findByUnameAndPwd(String uname, String pwd) throws Exception {
		return userDao.findByUnameAndPwd(uname, pwd);
	}

	@Override
	public void checkUser(String uname, String pwd) throws Exception {
		if(findByUnameAndPwd(uname, pwd).isEmpty()) {
			throw new Exception();
		}
	}

}
