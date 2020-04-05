package com.lawencon.ticketing.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lawencon.ticketing.model.Users;

@Service
public interface UserService {
	abstract List<Users> showAllUser(String uname, String pwd) throws Exception;
	abstract Users showUserById(Long id, String uname, String pwd) throws Exception;
	abstract void newUser(Users user, String uname, String pwd) throws Exception;
	abstract Users updateUser(Users user, String uname, String pwd) throws Exception;
	abstract void deleteAllUser(String uname, String pwd) throws Exception;
	abstract void deleteUser(Long id, String uname, String pwd) throws Exception;
	
	abstract List<Users> findByUnameAndPwd(String uname, String pwd) throws Exception;
	
	abstract void checkUser(String uname, String pwd) throws Exception;
}
