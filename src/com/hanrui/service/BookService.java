package com.hanrui.service;

import java.util.List;

import com.hanrui.entity.Book;

public interface BookService {
	/**
	 * ����һ��ͼ��
	 * 
	 * @param book
	 *            Ҫ���ӵ�ͼ��
	 * @return ���ӳɹ�����true��ʧ�ܷ���false
	 */
	public boolean addBook(Book book);

	/**
	 * ����isbnɾ��һ��ͼ��
	 * 
	 * @param isbn
	 *            Ҫɾ����ͼ���isbn��
	 * @return ɾ���ɹ�����true��ɾ��ʧ�ܷ���false
	 */
	public boolean deleteBook(String isbn);

	/**
	 * �޸�ͼ����Ϣ
	 * 
	 * @param book
	 *            Ҫ�޸ĵ�ͼ��
	 * @return �޸ĳɹ�����true���޸�ʧ�ܷ���false
	 */
	public boolean updateBook(Book book);

	/**
	 * ��ѯ����ͼ��
	 * 
	 * @return �������е�ͼ���б�
	 */
	public List<Book> findAllBooks();

	/**
	 * ����isbn��ѯͼ��
	 * 
	 * @param isbn
	 *            Ҫ��ѯ��isbn��
	 * @return ��ѯ����ͼ�飬û���򷵻�null
	 */
	public Book findBookByIsbn(String isbn);

	/**
	 * ����������ѯͼ��
	 * 
	 * @param bookName
	 *            ����
	 * @return ����������ͬ������ͼ���б�û���򷵻�null
	 */
	public List<Book> findBookByBookName(String bookName);

	/**
	 * �������߲�ѯͼ��
	 * 
	 * @param writer
	 *            ����
	 * @return ���ظ����ߵ�����ͼ���б�û���򷵻�null
	 */
	public List<Book> findBookByWriter(String writer);
}
