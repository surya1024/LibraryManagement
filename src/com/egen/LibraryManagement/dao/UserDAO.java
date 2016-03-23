package com.egen.LibraryManagement.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.egen.LibraryManagement.entity.User;


@Repository
public interface UserDAO {
	
	public User addUser(User u);

	public List<User> getAllUser();

	public User updateUser(User u);

	public User getUserById(int id);
}
