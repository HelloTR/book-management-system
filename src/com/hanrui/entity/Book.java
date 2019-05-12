package com.hanrui.entity;

public class Book {

	// ISBN
	private String isbn;
	// 书名
	private String bookName;
	// 作者
	private String writer;
	// 价格
	private float price;
	// 出版社
	private String publisher;
	// 出版日期
	private String publication_date;

	public Book() {

	}

	public Book(String isbn, String bookName, String writer, float price,
			String publisher, String publication_date) {
		this.isbn = isbn;
		this.bookName = bookName;
		this.writer = writer;
		this.price = price;
		this.publisher = publisher;
		this.publication_date = publication_date;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getPublication_date() {
		return publication_date;
	}

	public void setPublication_date(String publication_date) {
		this.publication_date = publication_date;
	}

	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", bookName=" + bookName + ", writer="
				+ writer + ", price=" + price + ", publisher=" + publisher
				+ ", publication_date=" + publication_date + "]";
	}

}
