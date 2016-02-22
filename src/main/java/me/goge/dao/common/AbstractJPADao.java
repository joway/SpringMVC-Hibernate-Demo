package me.goge.dao.common;

import com.google.common.base.Preconditions;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
public abstract class AbstractJPADao<T extends Serializable> implements JPADao<T> {

    protected Class<T> clazz;

    @PersistenceContext
    private EntityManager entityManager;


    protected final void setClazz(final Class<T> clazz) {
        this.clazz = Preconditions.checkNotNull(clazz);
    }

    @Override
    public void insert(T entity) {
        entityManager.persist(entity);
    }

    @Override
    public void delete(Serializable entity) {
        entityManager.remove(entity);
    }

    @Override
    public void deleteById(int id) {
        entityManager.createQuery("delete from " + clazz.getSimpleName() + " entity where entity.id = " + id)
                .executeUpdate();
    }


    @Override
    public T update(T entity) {
        return (T) entityManager.merge(entity);
    }

    @Override
    public T searchById(int id) {
        return (T) entityManager.find(clazz, id);
    }

    @Override
    public List<T> searchAll() {
        return searchByJPQL("select en from "
                + clazz.getSimpleName() + " en");
    }

    // 根据带占位符参数JPQL语句查询实体
    public List<T> searchByJPQL(String jpql, Object... params) {
        // 创建查询
        Query query = entityManager.createQuery(jpql);
        // 为包含占位符的JPQL语句设置参数
        for (int i = 0, len = params.length; i < len; i++) {
            query.setParameter(i, params[i]);
        }
        return (List<T>) query.getResultList();
    }

    @Override
    @CacheEvict(value="dbCache", allEntries=true)
    public void deleteAllCache() {
        System.out.println("delete all cache");
    }

}
