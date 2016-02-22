package me.goge.service.impl;

import me.goge.dao.BookDao;
import me.goge.model.Book;
import me.goge.service.BookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;


@Service
public class BookServiceImpl implements BookService
{

	@Resource
	private BookDao bookDao;

	@Override
	public int addBook(@Valid Book book) {
		bookDao.insert(book);
		return book.getId();
	}

	@Override
	public List<Book> getAllBooks() {
		return bookDao.searchAll();
	}

	@Override
	public Book getBookById(int id) {
		return bookDao.searchById(id);
	}

	@Override
	public void deleteBook(int id) {
		bookDao.deleteById(id);
		System.out.println("delete");
	}

    public void deleteAllCache() {
        bookDao.deleteAllCache();
    }
}
