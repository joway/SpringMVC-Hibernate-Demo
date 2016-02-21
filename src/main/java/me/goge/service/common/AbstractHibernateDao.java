package me.goge.service.common;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * @Author joway
 * @Email joway.w@gmail.com
 *
 *  16/2/19.
 */

@Transactional
public abstract class AbstractHibernateDao <T extends Serializable> implements BaseDao<T> {


    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    protected final Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void insert(Serializable entity) {
        System.out.println("insert");
        getCurrentSession().saveOrUpdate(entity);
    }

    @Override
    public void delete(Serializable entity) {


    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public T update(T entity) {
        return null;
    }

    @Override
    public T search(T entity) {
        return null;
    }

    @Override
    public T searchById(int id) {
        return null;
    }

    @Override
    public List<T> searchAll() {
        return null;
    }
}
