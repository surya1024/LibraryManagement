package com.egen.LibraryManagement.dao;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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

import com.egen.LibraryManagement.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class UserDAOTest {

	@Mock
	private SessionFactory sessionFactory;

	@Mock
	private Session session;

	@Mock
	private Query query;

	@InjectMocks
	private UserDAO dao = new UserDAOImp();

	private User user;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		Mockito.when(sessionFactory.getCurrentSession()).thenReturn(session);
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
	public void testAddUser () {
		dao.addUser(user);
		Mockito.verify(session).persist(user);
	}
	
	@Test
	public void testupdateUser () {
		dao.updateUser(user);
		Mockito.verify(session).update(user);
	}
	
	@Test
	public void testlistUser() {
		List<User> expected = Arrays.asList(user);
		Mockito.when(session.createQuery("from User")).thenReturn(query);
		Mockito.when(query.list()).thenReturn(expected);
		List<User> actual = dao.getAllUser();
		Assert.assertEquals(expected, actual);
	}
	@Test
	public void testgetUserById () {
		Mockito.when(session.get(User.class, new Integer(user.getId()))).thenReturn(user);
		User actual = dao.getUserById(user.getId());
		Assert.assertEquals(user, actual);
	}
	@Configuration
	public static class TestConfig {
		
	}
}
