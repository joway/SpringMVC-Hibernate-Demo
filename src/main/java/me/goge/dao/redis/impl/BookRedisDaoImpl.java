package me.goge.dao.redis.impl;

import me.goge.dao.common.AbstractRedisDao;
import me.goge.dao.redis.BookRedisDao;
import me.goge.model.Book;
import org.springframework.stereotype.Repository;

/**
 * @Author joway
 * @Email joway.w@gmail.com
 * @Date 16/2/24.
 */
@Repository //用于标注数据访问组件
public class BookRedisDaoImpl extends AbstractRedisDao<Book> implements BookRedisDao {
    public BookRedisDaoImpl() {
        super();
        setClazz(Book.class);
    }
}
