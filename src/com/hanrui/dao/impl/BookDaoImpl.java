package com.hanrui.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.hanrui.dao.BookDao;
import com.hanrui.entity.Book;
import com.hanrui.util.JdbcUtil;

public class BookDaoImpl implements BookDao {

	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;

	@Override
	public boolean addBook(Book book) {
		try {
			conn = JdbcUtil.getConnection();
			stmt = conn
					.prepareStatement("insert into book (isbn,book_name,writer,price,publisher,publication_date) values(?,?,?,?,?,?)");
			stmt.setString(1, book.getIsbn());
			stmt.setString(2, book.getBookName());
			stmt.setString(3, book.getWriter());
			stmt.setFloat(4, book.getPrice());
			stmt.setString(5, book.getPublisher());
			stmt.setString(6, book.getPublication_date());
			int i = stmt.executeUpdate();
			return i == 1 ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ÃÌº”Õº È ß∞‹");
			return false;
		} finally {
			JdbcUtil.release(rs, stmt, conn);
		}
	}

	@Override
	public boolean deleteBook(String isbn) {
		try {
			conn = JdbcUtil.getConnection();
			stmt = conn.prepareStatement("delete from book where isbn=?");
			stmt.setString(1, isbn);
			int i = stmt.executeUpdate();
			return i == 1 ? true : false;
		} catch (Exception e) {
			System.out.println("…æ≥˝Õº È ß∞‹");
			return false;
		} finally {
			JdbcUtil.release(rs, stmt, conn);
		}
	}

	@Override
	public boolean updateBook(Book book) {
		try {
			conn = JdbcUtil.getConnection();
			stmt = conn
					.prepareStatement("update book set book_name=?,writer=?,price=?,publisher=?,publication_date=? where isbn=?");
			stmt.setString(1, book.getBookName());
			stmt.setString(2, book.getWriter());
			stmt.setFloat(3, book.getPrice());
			stmt.setString(4, book.getPublisher());
			stmt.setString(5, book.getPublication_date());
			stmt.setString(6, book.getIsbn());
			int i = stmt.executeUpdate();
			return i == 1 ? true : false;
		} catch (Exception e) {
			System.out.println("–ﬁ∏ƒÕº È ß∞‹");
			return false;
		} finally {
			JdbcUtil.release(rs, stmt, conn);
		}
	}

	@Override
	public List<Book> findAllBooks() {
		List<Book> books = null;
		try {
			conn = JdbcUtil.getConnection();
			stmt = conn.prepareStatement("select * from book");
			rs = stmt.executeQuery();
			books = new ArrayList<Book>();
			while (rs.next()) {
				Book book = new Book();
				book.setIsbn(rs.getString("isbn"));
				book.setBookName(rs.getString("book_name"));
				book.setWriter(rs.getString("writer"));
				book.setPrice(rs.getFloat("price"));
				book.setPublisher(rs.getString("publisher"));
				book.setPublication_date(rs.getString("publication_date"));
				books.add(book);
			}
			return books;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("≤È—Ø ß∞‹");
			return books;
		} finally {
			JdbcUtil.release(rs, stmt, conn);
		}
	}

	@Override
	public Book findBookByIsbn(String isbn) {
		try {
			conn = JdbcUtil.getConnection();
			stmt = conn.prepareStatement("select * from book where isbn=?");
			stmt.setString(1, isbn);
			rs = stmt.executeQuery();
			Book book = null;
			while (rs.next()) {
				book = new Book();
				book.setIsbn(isbn);
				book.setBookName(rs.getString("book_name"));
				book.setWriter(rs.getString("writer"));
				book.setPrice(rs.getFloat("price"));
				book.setPublisher(rs.getString("publisher"));
				book.setPublication_date(rs.getString("publication_date"));
			}
			return book;
		} catch (Exception e) {
			System.out.println("≤È—Ø ß∞‹");
			return null;
		} finally {
			JdbcUtil.release(rs, stmt, conn);
		}
	}

	@Override
	public List<Book> findBookByBookName(String bookName) {
		List<Book> books = null;
		try {
			conn = JdbcUtil.getConnection();
			stmt = conn
					.prepareStatement("select * from book where book_name=?");
			stmt.setString(1, bookName);
			rs = stmt.executeQuery();
			books = new ArrayList<Book>();
			while (rs.next()) {
				Book book = new Book();
				book.setIsbn(rs.getString("isbn"));
				book.setBookName(rs.getString("book_name"));
				book.setWriter(rs.getString("writer"));
				book.setPrice(rs.getFloat("price"));
				book.setPublisher(rs.getString("publisher"));
				book.setPublication_date(rs.getString("publication_date"));
				books.add(book);
			}
			return books;
		} catch (Exception e) {
			System.out.println("≤È—Ø ß∞‹");
			return books;
		} finally {
			JdbcUtil.release(rs, stmt, conn);
		}
	}

	@Override
	public List<Book> findBookByWriter(String writer) {
		List<Book> books = null;
		try {
			conn = JdbcUtil.getConnection();
			stmt = conn.prepareStatement("select * from book where writer=?");
			stmt.setString(1, writer);
			rs = stmt.executeQuery();
			books = new ArrayList<Book>();
			while (rs.next()) {
				Book book = new Book();
				book.setIsbn(rs.getString("isbn"));
				book.setBookName(rs.getString("book_name"));
				book.setWriter(rs.getString("writer"));
				book.setPrice(rs.getFloat("price"));
				book.setPublisher(rs.getString("publisher"));
				book.setPublication_date(rs.getString("publication_date"));
				books.add(book);
			}
			return books;
		} catch (Exception e) {
			System.out.println("≤È—Ø ß∞‹");
			return books;
		} finally {
			JdbcUtil.release(rs, stmt, conn);
		}
	}

}
