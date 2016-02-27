package me.goge.dao.common;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author joway
 * @Email joway.w@gmail.com
 * @Date 16/2/24.
 */
public interface RedisDao<T extends Serializable> {

    /*
    通用操作
     */

    void delete(String key);

    void delete(List<String> keyList);

    /*
    list 操作
     */
    Long list_push(String key, T value);

    T list_pop(String key);

    Long list_inQueue(String key, T value);

    T list_outQueue(String key);

    Long list_length(String key);

    List<T> list_range(String key, int start, int end);

    void list_remove(String key, long index, T value);

    T list_index(String key, long index);

    void list_set(String key, long index, T value);

    void list_trim(String key, long start, int end);


    /*
    value 操作
    */
    void value_set(String key, T value);

    T value_get(String key);



    /*
    Set 操作
     */
    Set<T> getSet(String key);

    void setSet(String key, Set<T> set);

    /*
    Map操作
     */
    Map getMap(String key);

    void setMap(String key, Map<String, T> map);

    Map getIntegerMap(String key);

    void setIntegerMap(String key, Map<Integer, T> map);
}
