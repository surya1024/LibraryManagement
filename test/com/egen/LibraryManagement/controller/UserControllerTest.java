package com.egen.LibraryManagement.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.egen.LibraryManagement.entity.User;
import com.egen.LibraryManagement.exception.UserBadRequest;
import com.egen.LibraryManagement.exception.UserNotFound;
import com.egen.LibraryManagement.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class UserControllerTest {
	@Mock
	private UserService service;

	@InjectMocks
	private UserController controller;

	private MockMvc mockMvc;

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

		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	
	@Test
	public void testaddUser() throws Exception {
		String requestBody = new ObjectMapper().writeValueAsString(user);
		mockMvc.perform(MockMvcRequestBuilders.post("/users").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(requestBody)).andExpect(MockMvcResultMatchers.status().isOk());
		Mockito.verify(service).addUser(Mockito.any(User.class));
	}
	
	@Test
	public void testaddUserException() throws Exception {
		Mockito.when(service.addUser(Mockito.any(User.class))).thenThrow(new UserBadRequest());
		String requestBody = new ObjectMapper().writeValueAsString(user);
		mockMvc.perform(MockMvcRequestBuilders.post("/users").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(requestBody)).andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	@Test
	public void testUpdateUser() throws Exception {
		String requestBody = new ObjectMapper().writeValueAsString(user);
		mockMvc.perform(MockMvcRequestBuilders.put("/users").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(requestBody)).andExpect(MockMvcResultMatchers.status().isOk());
		Mockito.verify(service).updateUser(Mockito.any(User.class));
	}
	
	@Test
	public void testupdateUserException() throws Exception {
		Mockito.when(service.updateUser(Mockito.any(User.class))).thenThrow(new UserNotFound());
		String requestBody = new ObjectMapper().writeValueAsString(user);
		mockMvc.perform(MockMvcRequestBuilders.put("/users").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(requestBody)).andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	
	@Test
	public void testListUser() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/users")).andExpect(MockMvcResultMatchers.status().isOk());
		Mockito.verify(service).getAllUser();
	}
	
	@Configuration
	public static class TestConfig {
		
	}
}
