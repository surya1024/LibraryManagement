package com.egen.LibraryManagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Book not available, checked out by other user")
public class BookNotAvailable extends Exception {
	private static final long serialVersionUID = 3395643850556625864L;

}
