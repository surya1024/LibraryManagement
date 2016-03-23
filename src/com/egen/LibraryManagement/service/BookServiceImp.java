package com.egen.LibraryManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.egen.LibraryManagement.dao.BookDAO;
import com.egen.LibraryManagement.dao.UserDAO;
import com.egen.LibraryManagement.entity.Book;
import com.egen.LibraryManagement.entity.User;
import com.egen.LibraryManagement.exception.BookNotAvailable;
import com.egen.LibraryManagement.exception.BookNotFound;
import com.egen.LibraryManagement.exception.UserNotFound;

@Service
public class BookServiceImp implements BookService {
	@Autowired
	@Qualifier("bookDAOImp")
	private BookDAO bookDAO;
	
	@Autowired
	@Qualifier("userDAOImp")
	private UserDAO userDAO;

	@Override
	public List<Book> getAllBooks() {	
		return bookDAO.getAllBooks();
	}

	@Override
	public List<Book> findBookByName(String name) {
		return bookDAO.findBookByName(name);
	}

	@Override
	public Book addBook(Book b) {
		return bookDAO.addBook(b);
	}

	@Override
	public Book getBookById(int id) throws BookNotFound {
		Book existing =bookDAO.getBookById(id);
		if(existing!= null)
			return existing;
		else
			throw new BookNotFound();
	}

	@Override
	public Book checkOutBook(int userId, int bookId) throws BookNotAvailable, BookNotFound, UserNotFound {
		User validUser=userDAO.getUserById(userId);
		if (validUser==null) {
			throw new UserNotFound();
		}
		Book validBook=bookDAO.getBookById(bookId);
		if (validBook==null) {
			throw new BookNotFound();
		}
		Book checkOut =bookDAO.checkOutBook(userId, validBook);
		if(checkOut== null)
			throw new BookNotAvailable();
		else
			return checkOut;			
	}
	
	
}
