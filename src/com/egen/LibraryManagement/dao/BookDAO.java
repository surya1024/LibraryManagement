package com.egen.LibraryManagement.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.egen.LibraryManagement.entity.Book;

@Repository
public interface BookDAO {

	public List<Book>getAllBooks();
	
	public List<Book> findBookByName(String name);
	
	public Book addBook(Book b);
	
	public Book getBookById(int id);
	
	public Book checkOutBook(int userId, Book b);
}
