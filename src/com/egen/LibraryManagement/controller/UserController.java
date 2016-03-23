package com.egen.LibraryManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.egen.LibraryManagement.entity.User;
import com.egen.LibraryManagement.exception.UserBadRequest;
import com.egen.LibraryManagement.exception.UserNotFound;
import com.egen.LibraryManagement.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE, 
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public User addUser(@RequestBody User u) throws UserBadRequest {
		return this.userService.addUser(u);
	}
	
	@RequestMapping(method = RequestMethod.PUT, 
			produces = MediaType.APPLICATION_JSON_VALUE, 
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public User updateUser(@RequestBody User u) throws UserNotFound {
		return this.userService.updateUser(u);
	}
	
	@RequestMapping(method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> listUser() {
		return this.userService.getAllUser();
	}

}
