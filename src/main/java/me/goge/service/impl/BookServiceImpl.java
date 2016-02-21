package me.goge.service.impl;

import me.goge.dao.BookDao;
import me.goge.model.Book;
import me.goge.service.BookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class BookServiceImpl implements BookService
{

	@Resource
	private BookDao bookDao;

	@Override
	public int addBook(Book book) {
		System.out.println("add book ");
		bookDao.insert(book);
		return 0;
	}

	@Override
	public List<Book> getAllBooks() {
		return null;
	}

	@Override
	public void deleteBook(int id) {

	}
}
