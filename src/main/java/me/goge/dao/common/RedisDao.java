package me.goge.dao.common;

import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * @Author joway
 * @Email joway.w@gmail.com
 * @Date 16/2/24.
 */
@Repository
public interface RedisDao<T extends Serializable> {
    void set(String key, T value);
    T get(String key);
}
