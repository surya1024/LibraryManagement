package com.egen.LibraryManagement.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.egen.LibraryManagement.entity.User;

@Repository
public class UserDAOImp implements UserDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public User addUser(User u) {
		Session session = sessionFactory.getCurrentSession();
		session.persist(u);
		return u;
	}

	@Override
	@Transactional
	public List<User> getAllUser() {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<User> userList = session.createQuery("from User").list();
		return userList;
	}

	@Override
	@Transactional
	public User updateUser(User u) {
		Session session = sessionFactory.getCurrentSession();
		session.update(u);
		return u;
	}

	@Override
	@Transactional
	public User getUserById(int id) {
		Session session = sessionFactory.getCurrentSession();
		User user = (User) session.get(User.class, new Integer(id));
		return user;
	}
}
