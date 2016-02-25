package me.goge.dao.mysql.impl;

import me.goge.dao.mysql.BookDao;
import me.goge.model.Book;
import me.goge.dao.common.AbstractJPADao;
import org.springframework.stereotype.Repository;

/**
 * @Author joway
 * @Email joway.w@gmail.com
 * @Date 16/2/21.
 */
@Repository //用于标注数据访问组件
public class BookDaoImpl extends AbstractJPADao<Book> implements BookDao {
    public BookDaoImpl() {
        super();
        setClazz(Book.class);
    }
}
