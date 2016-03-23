package com.egen.LibraryManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.egen.LibraryManagement.entity.Book;
import com.egen.LibraryManagement.exception.BookNotAvailable;
import com.egen.LibraryManagement.exception.BookNotFound;
import com.egen.LibraryManagement.exception.UserNotFound;
import com.egen.LibraryManagement.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {
	@Autowired
	private BookService bookService;

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Book addBook(@RequestBody Book b) {
		return this.bookService.addBook(b);
	}

	@RequestMapping(value = "{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Book> findBookByName(@PathVariable("name") String name) {
		return this.bookService.findBookByName(name);
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Book> getAllBooks() {
		return this.bookService.getAllBooks();
	}

	@RequestMapping(value = "{userId}/{bookId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Book checkOutBook(@PathVariable("userId") int userId, @PathVariable("bookId") int bookId)
			throws BookNotAvailable, BookNotFound, UserNotFound {
		return this.bookService.checkOutBook(userId, bookId);
	}
}
