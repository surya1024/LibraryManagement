package com.egen.LibraryManagement.service;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.egen.LibraryManagement.dao.UserDAO;
import com.egen.LibraryManagement.entity.User;
import com.egen.LibraryManagement.exception.UserBadRequest;
import com.egen.LibraryManagement.exception.UserNotFound;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class UserServiceTest {
	
	@Mock
	private UserDAO dao;
	
	
	@InjectMocks
	private UserServiceImp service = new UserServiceImp();
	
	private User user;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		user = new User();
		user.setId(1);
		user.setFirstName("Surya");
		user.setLastName("Mylar");
		user.setMiddleName("N");
		user.setGender("M");
		user.setAge("24");
		user.setPhone("2017360157");
		user.setZip("07053");

	}
	
	@Test
	public void testaddUser() throws UserBadRequest{
		service.addUser(user);
		Mockito.verify(dao).addUser(user);
	}
	
	@Test(expected=UserBadRequest.class)
	public void addUserException() throws UserBadRequest{
		user.setAge("asdf");
		service.addUser(user);
		Mockito.verify(dao).addUser(user);
	}
	@Test
	public void testgetAllUser(){
		List<User> expected = Arrays.asList(user);
		Mockito.when(dao.getAllUser()).thenReturn(expected);
		List<User> actual = service.getAllUser();
		Assert.assertEquals(expected, actual);
	}
	@Test
	public void testupdateUser() throws UserNotFound{
		Mockito.when(dao.getUserById(user.getId())).thenReturn(user);
		user.setFirstName("UpdatedFirstName");
		service.updateUser(user);
		Mockito.verify(dao).updateUser(user);
	}
	
	@Test(expected=UserNotFound.class)
	public void testupdateUserNotExist() throws UserNotFound{
		Mockito.when(dao.getUserById(user.getId())).thenReturn(null);
		service.updateUser(user);
		Mockito.verify(dao).updateUser(user);
	}
	
	@Configuration
	public static class TestConfig {
		
	}

}
