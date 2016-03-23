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

import com.egen.LibraryManagement.dao.BookDAO;
import com.egen.LibraryManagement.entity.Book;
import com.egen.LibraryManagement.entity.User;
import com.egen.LibraryManagement.exception.UserBadRequest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class BookServiceTest {

	@Mock
	private BookDAO dao;
	
	@InjectMocks
	private BookService service=new BookServiceImp();
	
	private Book book;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		book=new Book();
		book.setId(1);
		book.setName("jave Spring");
		String[] authors={"surya"};
		book.setAuthors(authors);
		book.setCheckedOutBy(null);		
	}
	
	@Test
	public void testgetAllBook(){
		List<Book> expected = Arrays.asList(book);
		Mockito.when(dao.getAllBooks()).thenReturn(expected);
		List<Book> actual = service.getAllBooks();
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testfindBookByName(){
		List<Book> expected = Arrays.asList(book);
		Mockito.when(dao.findBookByName(book.getName())).thenReturn(expected);
		List<Book> actual = service.findBookByName(book.getName());
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testaddBook() {
		service.addBook(book);
		Mockito.verify(dao).addBook(book);
	}
	
	
	
	@Configuration
	public static class TestConfig {
		
	}
}
