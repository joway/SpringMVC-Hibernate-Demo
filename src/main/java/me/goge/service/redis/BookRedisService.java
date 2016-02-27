package me.goge.service.redis;

import me.goge.model.Book;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author joway
 * @Email joway.w@gmail.com
 * @Date 16/2/24.
 */
public interface BookRedisService {

        /*
    通用操作
     */

    void delete(String key);

    void delete(List<String> keyList);

    /*
    list 操作
     */
    Long list_push(String key, Book value);

    Book list_pop(String key);

    Long list_inQueue(String key, Book value);

    Book list_outQueue(String key);

    Long list_length(String key);

    List<Book> list_range(String key, int start, int end);

    void list_remove(String key, long index, Book value);

    Book list_index(String key, long index);

    void list_set(String key, long index, Book value);

    void list_trim(String key, long start, int end);


    /*
    value 操作
    */
    void value_set(String key, Book value);

    Book value_get(String key);



    Set<Book> getSet(String key);

    void setSet(String key, Set<Book> set);

    Map getMap(String key);

    void setMap(String key, Map<String, Book> map);

    Map getIntegerMap(String key);

    void setIntegerMap(String key, Map<Integer, Book> map);
}
