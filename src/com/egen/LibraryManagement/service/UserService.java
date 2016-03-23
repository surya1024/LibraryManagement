package com.egen.LibraryManagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.egen.LibraryManagement.entity.User;
import com.egen.LibraryManagement.exception.UserBadRequest;
import com.egen.LibraryManagement.exception.UserNotFound;

@Service
public interface UserService {
	
	public User addUser(User u) throws UserBadRequest;
	
	public List<User> getAllUser();
	
	public User updateUser(User u) throws UserNotFound;
}
