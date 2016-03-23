package com.egen.LibraryManagement.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.egen.LibraryManagement.entity.Book;

@Repository
public class BookDAOImp implements BookDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public List<Book> getAllBooks() {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Book> bookList = session.createQuery("from Book").list();
		return bookList;
	}

	@Override
	@Transactional
	public List<Book> findBookByName(String name) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Book b where str(b.name) = :searchTerm");
		@SuppressWarnings("unchecked")
		List<Book> bookList = query.setParameter("searchTerm",name).list();
		return bookList;
	}

	@Override
	@Transactional
	public Book addBook(Book b) {
		Session session = sessionFactory.getCurrentSession();
		session.persist(b);
		return b;
	}

	@Override
	@Transactional
	public Book checkOutBook(int userId, Book b) {
		Session session = sessionFactory.getCurrentSession();
		if(b.getCheckedOutBy()!=null){
			return null;
		}	
		b.setCheckedOutBy(new Integer(userId).toString());
		session.update(b);
		return b;
	}

	@Override
	@Transactional
	public Book getBookById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Book book = (Book) session.get(Book.class, new Integer(id));
		return book;
	}

}
