package me.goge.dao.common;

import com.google.common.base.Preconditions;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
    @Cacheable(value = "dbCache") // add cache
    // Cacheable注解还有个参数key：默认为空，即表示使用方法的参数类型及参数值作为key，支持SpEL
    public void insert(T entity) {
        entityManager.persist(entity);
    }

    @Override
    @CacheEvict(value = "dbCache") // delete cache
    // 还有个参数key, 默认同上, 清除key对应的cache
    public void delete(Serializable entity) {
        entityManager.remove(entity);
    }

    @Override
    @CacheEvict(value = "dbCache")
    public void deleteById(int id) {
        entityManager.createQuery("delete from " + clazz.getSimpleName() + " entity where entity.id = " + id)
                .executeUpdate();
    }


    @Override
    @CachePut(value = "dbCache")
    public T update(T entity) {
        return (T) entityManager.merge(entity);
    }

    @Override
    @Cacheable(value = "dbCache")
    public T searchById(int id) {
        return (T) entityManager.find(clazz, id);
    }

    @Override
    @Cacheable(value = "dbCache")
    public List<T> searchAll() {
        return searchByJPQL("select en from "
                + clazz.getSimpleName() + " en");
    }

    // 根据带占位符参数JPQL语句查询实体
    @Override
    @Cacheable(value = "dbCache")
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
    @CacheEvict(value = "dbCache", allEntries = true)
    public void deleteAllCache() {
        System.out.println("delete all cache");
    }

    @Override
    @Cacheable(value = "dbCache")
    public long getCount() {
        List<?> l = searchByJPQL("select count(*) from "
                + clazz.getSimpleName());
        // 返回查询得到的实体总数
        if (l != null && l.size() == 1 )
        {
            return (Long)l.get(0);
        }
        return 0;
    }

    @Override
    @Cacheable(value = "dbCache")
    public List<T> searchByPage(String jpql, int pageNo, int pageSize) {
        // 创建查询
        return entityManager.createQuery(jpql)
                // 执行分页
                .setFirstResult((pageNo - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
    }

    @Override
    @Cacheable(value = "dbCache")
    public List<T> searchAllByPage(int pageNo, int pageSize) {
        return searchByPage("select en from "
                + clazz.getSimpleName() + " en",pageNo,pageSize);
    }
}
