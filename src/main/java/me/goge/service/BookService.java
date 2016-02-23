package me.goge.service;

import me.goge.model.Book;

import java.util.List;


public interface BookService
{
	// ÃÌº”Õº È
	void addBook(Book book);

	List<Book> getAllBooks();

	Book getBookById(int id);
	
	void deleteBook(int id);

	void deleteAllCache();

    long getBookCount();

    void updateBook(Book book);
}
