package me.goge.dao.common;

import com.google.common.base.Preconditions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * @Author joway
 * @Email joway.w@gmail.com
 * <p/>
 * 16/2/19.
 */
@SuppressWarnings("unchecked")
@Transactional
public abstract class AbstractHibernateDao<T extends Serializable> implements BaseDao<T> {

    protected Class<T> clazz;

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;


    protected final void setClazz(final Class<T> clazz) {
        this.clazz = Preconditions.checkNotNull(clazz);
    }

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
        return (T) getCurrentSession().get(clazz, id);
    }

    @Override
    public List<T> searchAll() {
        return null;
    }

}
