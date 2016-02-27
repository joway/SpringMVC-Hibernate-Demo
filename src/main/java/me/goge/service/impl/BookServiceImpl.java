package me.goge.service.impl;

import me.goge.dao.mysql.BookDao;
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
	public void addBook(@Valid Book book) {
		bookDao.insert(book);
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

    @Override
    public void deleteAllCache() {
        bookDao.deleteAllCache();
    }

    @Override
    public long getBookCount(){
        return bookDao.getCount();
    }

    @Override
    public void updateBook(Book book) {
        bookDao.update(book);
    }
}
