package me.goge.dao.impl;

import me.goge.dao.BookDao;
import me.goge.model.Book;
import me.goge.service.common.AbstractHibernateDao;
import org.springframework.stereotype.Repository;

/**
 * @Author joway
 * @Email joway.w@gmail.com
 * @Date 16/2/21.
 */
@Repository
public class BookDaoImpl extends AbstractHibernateDao<Book> implements BookDao {

}
