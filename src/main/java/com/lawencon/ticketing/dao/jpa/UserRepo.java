package com.lawencon.ticketing.dao.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.model.Users;

@Repository
public interface UserRepo extends JpaRepository<Users, Long> {

	abstract List<Users> findByUnameAndPwd(String uname, String pwd);
}
