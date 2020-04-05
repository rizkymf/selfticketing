package com.lawencon.ticketing.dao;

import java.util.List;

import com.lawencon.ticketing.model.Users;

public interface UserDao {

	abstract List<Users> showAllUser();
	abstract Users showUserById(Long id);
	abstract void newUser(Users user);
	abstract Users updateUser(Users user);
	abstract void deleteAllUser();
	abstract void deleteUser(Long id);
	
	abstract List<Users> findByUnameAndPwd(String uname, String pwd);
	
}
