package com.egen.LibraryManagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.egen.LibraryManagement.entity.Book;
import com.egen.LibraryManagement.exception.BookNotAvailable;
import com.egen.LibraryManagement.exception.BookNotFound;
import com.egen.LibraryManagement.exception.UserNotFound;


@Service
public interface BookService {
public List<Book>getAllBooks();
	
	public List<Book>findBookByName(String name);
	
	public Book addBook(Book b);
	
	public Book getBookById(int id) throws BookNotFound;
	
	public Book checkOutBook(int userId, int bookId) throws BookNotAvailable, BookNotFound, UserNotFound;
}
