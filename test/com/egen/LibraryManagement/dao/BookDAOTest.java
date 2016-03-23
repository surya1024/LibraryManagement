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

import com.egen.LibraryManagement.entity.Book;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class BookDAOTest {
	
	@Mock
	private SessionFactory sessionFactory;

	@Mock
	private Session session;

	@Mock
	private Query query;
	
	@InjectMocks
	private BookDAO dao = new BookDAOImp();

	private Book book;
	
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		Mockito.when(sessionFactory.getCurrentSession()).thenReturn(session);
		book=new Book();
		book.setId(1);
		book.setName("jave Spring");
		String[] authors={"surya"};
		book.setAuthors(authors);
		book.setCheckedOutBy(null);		
	}
	
	@Test
	public void getAllBooks() {
		List<Book> expected = Arrays.asList(book);
		Mockito.when(session.createQuery("from Book")).thenReturn(query);
		Mockito.when(query.list()).thenReturn(expected);
		List<Book> actual = dao.getAllBooks();
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testAddBook () {
		dao.addBook(book);
		Mockito.verify(session).persist(book);
	}
	
	@Test
	public void testgetBookById () {
		Mockito.when(session.get(Book.class, new Integer(book.getId()))).thenReturn(book);
		Book actual = dao.getBookById(book.getId());
		Assert.assertEquals(book, actual);
	}
	
	@Configuration
	public static class TestConfig {
		
	}
}
