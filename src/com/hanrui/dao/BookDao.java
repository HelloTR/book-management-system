package com.hanrui.dao;

import java.util.List;

import com.hanrui.entity.Book;

public interface BookDao {

	/**
	 * 增加一本图书
	 * @param book	要增加的图书
	 * @return	增加成功返回true，失败返回false
	 */
	public boolean addBook(Book book);
	/**
	 * 根据isbn删除一本图书
	 * @param isbn	要删除的图书的isbn号
	 * @return	删除成功返回true，删除失败返回false
	 */
	public boolean deleteBook(String isbn);
	/**
	 * 修改图书信息
	 * @param book	要修改的图书
	 * @return	修改成功返回true，修改失败返回false
	 */
	public boolean updateBook(Book book);
	/**
	 * 查询所有图书
	 * @return	返回所有的图书列表
	 */
	public List<Book> findAllBooks();
	/**
	 * 根据isbn查询图书
	 * @param isbn	要查询的isbn号
	 * @return	查询到的图书，没有则返回null
	 */
	public Book findBookByIsbn(String isbn);
	/**
	 * 根据书名查询图书
	 * @param bookName	书名
	 * @return	返回书名相同的所有图书列表，没有则返回null
	 */
	public List<Book> findBookByBookName(String bookName);
	/**
	 * 根据作者查询图书
	 * @param writer	作者
	 * @return	返回该作者的所有图书列表，没有则返回null
	 */
	public List<Book> findBookByWriter(String writer);
}
