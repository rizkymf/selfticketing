package com.lawencon.ticketing.dao.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.dao.UserDao;
import com.lawencon.ticketing.model.Users;

@Repository("user_repo_jpa")
public class UserDaoImpl implements UserDao {

	@Autowired
	UserRepo userRepo;

	@Override
	public List<Users> showAllUser() {
		return userRepo.findAll();
	}

	@Override
	public Users showUserById(Long id) {
		return userRepo.findById(id).orElse(null);
	}

	@Override
	public void newUser(Users user) {
		userRepo.save(user);		
	}

	@Override
	public Users updateUser(Users user) {
		user = userRepo.save(user);
		return user;
	}

	@Override
	public void deleteAllUser() {
		userRepo.deleteAll();
		
	}

	@Override
	public void deleteUser(Long id) {
		userRepo.deleteById(id);		
	}

	@Override
	public List<Users> findByUnameAndPwd(String uname, String pwd) {
		return userRepo.findByUnameAndPwd(uname, pwd);
	}
}
