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

import com.egen.LibraryManagement.entity.Book;
import com.egen.LibraryManagement.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class BookControllerTest {
	@Mock
	private BookService service;

	@InjectMocks
	private BookController controller;

	private MockMvc mockMvc;

	private Book book;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		book = new Book();
		book.setId(1);
		book.setName("jave Spring");
		String[] authors = { "surya" };
		book.setAuthors(authors);
		book.setCheckedOutBy(null);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void testaddBook() throws Exception {
		String requestBody = new ObjectMapper().writeValueAsString(book);
		mockMvc.perform(MockMvcRequestBuilders.post("/books").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(requestBody)).andExpect(MockMvcResultMatchers.status().isOk());
		Mockito.verify(service).addBook(Mockito.any(Book.class));
	}

	@Test
	public void testgetAllBooks() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/books")).andExpect(MockMvcResultMatchers.status().isOk());
		Mockito.verify(service).getAllBooks();
	}

	@Test
	public void testfindBookByName() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/books/" + book.getName()))
				.andExpect(MockMvcResultMatchers.status().isOk());
		Mockito.verify(service).findBookByName(book.getName());
	}

	@Configuration
	public static class TestConfig {

	}
}
