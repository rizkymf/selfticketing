package com.lawencon.ticketing.dao.hibernate;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lawencon.ticketing.dao.UserDao;
import com.lawencon.ticketing.model.Users;

@SuppressWarnings("unchecked")
@Repository("user_repo_hibernate")
public class UserImpl extends BaseHibernate implements UserDao {

	@Override
	public List<Users> showAllUser() {
		Query q = em.createQuery("from Users");
		return q.getResultList();
	}

	@Override
	public Users showUserById(Long id) {
		Query q = em.createQuery("from Users where user_id = :userId").setParameter("userId", id);
		return (Users) q.getResultList().get(0);
	}

	@Override
	public void newUser(Users user) {
		em.persist(user);		
	}

	@Override
	public Users updateUser(Users user) {
		user = em.merge(user);
		return user;
	}

	@Override
	public void deleteAllUser() {
		Query q = em.createQuery("delete from Users");
		q.executeUpdate();
	}

	@Override
	public void deleteUser(Long id) {
		Query q = em.createQuery("delete from Users where user_id = :userId").setParameter("userId", id);
		q.executeUpdate();
	}

	@Override
	public List<Users> findByUnameAndPwd(String uname, String pwd) {
		Query q = em.createQuery("from Users where uname = :uname and pwd = :pwd").setParameter("uname", uname).setParameter("pwd", pwd);
		return q.getResultList();
	}

	
}
