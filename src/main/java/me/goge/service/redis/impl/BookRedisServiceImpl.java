package me.goge.service.redis.impl;

import me.goge.dao.redis.BookRedisDao;
import me.goge.model.Book;
import me.goge.service.redis.BookRedisService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author joway
 * @Email joway.w@gmail.com
 * @Date 16/2/24.
 */
@Service
public class BookRedisServiceImpl implements BookRedisService {

    @Resource
    BookRedisDao bookRedisDao;

    @Override
    public void delete(String key) {
        bookRedisDao.delete(key);
    }

    @Override
    public void delete(List<String> keyList) {
        bookRedisDao.delete(keyList);
    }

    @Override
    public Long list_push(String key, Book value) {
        return bookRedisDao.list_push(key, value);
    }

    @Override
    public Book list_pop(String key) {
        return bookRedisDao.list_pop(key);
    }

    @Override
    public Long list_inQueue(String key, Book value) {
        return bookRedisDao.list_inQueue(key, value);
    }

    @Override
    public Book list_outQueue(String key) {
        return bookRedisDao.list_outQueue(key);
    }

    @Override
    public Long list_length(String key) {
        return bookRedisDao.list_length(key);
    }

    @Override
    public List<Book> list_range(String key, int start, int end) {
        return bookRedisDao.list_range(key, start, end);
    }

    @Override
    public void list_remove(String key, long index, Book value) {
        bookRedisDao.list_remove(key, index, value);
    }

    @Override
    public Book list_index(String key, long index) {
        return bookRedisDao.list_index(key, index);
    }

    @Override
    public void list_set(String key, long index, Book value) {
        bookRedisDao.list_set(key, index, value);
    }

    @Override
    public void list_trim(String key, long start, int end) {
        bookRedisDao.list_trim(key, start, end);
    }

    @Override
    public void value_set(String key, Book value) {
        bookRedisDao.value_set(key, value);
    }

    @Override
    public Book value_get(String key) {
        return bookRedisDao.value_get(key);
    }

    @Override
    public Set<Book> getSet(String key) {
        return bookRedisDao.getSet(key);
    }

    @Override
    public void setSet(String key, Set<Book> set) {
        bookRedisDao.setSet(key, set);
    }

    @Override
    public Map getMap(String key) {
        return bookRedisDao.getMap(key);
    }

    @Override
    public void setMap(String key, Map<String, Book> map) {
        bookRedisDao.setMap(key, map);
    }

    @Override
    public Map getIntegerMap(String key) {
        return bookRedisDao.getIntegerMap(key);
    }

    @Override
    public void setIntegerMap(String key, Map<Integer, Book> map) {
        bookRedisDao.setIntegerMap(key, map);
    }
}
