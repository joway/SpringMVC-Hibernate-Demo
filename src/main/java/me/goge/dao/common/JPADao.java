package me.goge.dao.common;

import java.io.Serializable;
import java.util.List;

/**
 * @Author joway
 @Email joway.w@gmail.com
 on 16/2/19.
 */

/*
 * 通用的操作接口
 */
public interface JPADao<T extends Serializable> {
    void insert(T entity);
    void delete(T entity);
    void deleteById(int id);
    T update(T entity);
    T searchById(int id);
    List<T> searchAll();
    List<T> searchByJPQL(String jpql , Object... params);

    void deleteAllCache();

}
