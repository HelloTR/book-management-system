package com.hanrui.service.impl;

import java.util.List;

import com.hanrui.dao.BookDao;
import com.hanrui.dao.impl.BookDaoImpl;
import com.hanrui.entity.Book;
import com.hanrui.service.BookService;

public class BookServiceImpl implements BookService {

	private BookDao bDao = new BookDaoImpl();

	@Override
	public boolean addBook(Book book) {
		return bDao.addBook(book);
	}

	@Override
	public boolean deleteBook(String isbn) {
		return bDao.deleteBook(isbn);
	}

	@Override
	public boolean updateBook(Book book) {
		return bDao.updateBook(book);
	}

	@Override
	public List<Book> findAllBooks() {
		return bDao.findAllBooks();
	}

	@Override
	public Book findBookByIsbn(String isbn) {
		return bDao.findBookByIsbn(isbn);
	}

	@Override
	public List<Book> findBookByBookName(String bookName) {
		return bDao.findBookByBookName(bookName);
	}

	@Override
	public List<Book> findBookByWriter(String writer) {
		return bDao.findBookByWriter(writer);
	}

}
