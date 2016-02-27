package me.goge.dao.common;

import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private Logger logger;


    @PersistenceContext
    private EntityManager entityManager;


    protected final void setClazz(final Class<T> clazz) {
        this.clazz = Preconditions.checkNotNull(clazz);
        this.logger =  LoggerFactory.getLogger(clazz);
    }

    protected void logInfo(String msg){
        logger.info(msg);
    }

    @Override
    @CachePut(value = "dbCache", key = "#root.caches[0].name")
    @Cacheable(value = "dbCache") // add cache
    // Cacheable注解还有个参数key：默认为空，即表示使用方法的参数类型及参数值作为key，支持SpEL
    public void insert(T entity) {
        entityManager.persist(entity);
        logInfo("insert success");
    }

    @Override
    @CacheEvict(value = "dbCache") // delete cache
    // 还有个参数key, 默认同上, 清除key对应的cache
    public void delete(Serializable entity) {
        entityManager.remove(entity);
        logInfo("delete success");
    }

    @Override
    @CacheEvict(value = "dbCache")
    public void deleteById(int id) {
        entityManager.createQuery("delete from " + clazz.getSimpleName() + " entity where entity.id = " + id)
                .executeUpdate();
        logInfo("delete success");

    }


    @Override
    @CachePut(value = "dbCache") // 每次都会更新缓存
    public T update(T entity) {
        logInfo("update success");
        return (T) entityManager.merge(entity);

    }

    @Override
    @Cacheable(value = "dbCache")
    public T searchById(int id) {
        logInfo("search success");
        return (T) entityManager.find(clazz, id);
    }

    // 发现如果insert了一个值后,searchAll 仍旧会是缓存后的值,故而取消了它的缓存
    @Override
    public List<T> searchAll() {
        logInfo("searchAll success");
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
        logInfo("searchByJPQL success");
        return (List<T>) query.getResultList();
    }

    @Override
    @CacheEvict(value = "dbCache", allEntries = true)
    public void deleteAllCache() {
        logInfo("deleteAllCache success");
        System.out.println("delete all cache");
    }

    @Override
    @Cacheable(value = "dbCache")
    public long getCount() {
        List<?> result = searchByJPQL("select count(*) from "
                + clazz.getSimpleName());
        logInfo("getCount success");
        if (result != null && result.size() == 1) {
            return (Long) result.get(0);
        }
        return 0;
    }

    @Override
    @Cacheable(value = "dbCache")
    public List<T> searchByPage(String jpql, int pageNo, int pageSize) {
        logInfo("searchByPage success");
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
        logInfo("searchAllByPage success");
        return searchByPage("select en from "
                + clazz.getSimpleName() + " en", pageNo, pageSize);
    }
}
