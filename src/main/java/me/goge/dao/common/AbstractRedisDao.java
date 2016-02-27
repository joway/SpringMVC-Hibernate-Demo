package me.goge.dao.common;

import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author joway
 * @Email joway.w@gmail.com
 * @Date 16/2/24.
 */
@SuppressWarnings("unchecked")
@Transactional
public class AbstractRedisDao<T extends Serializable> implements RedisDao<T> {

    protected Class<T> clazz;
    private Logger logger;

    //直接使用autowire就可以引用到配置文件中的redis-redisTemplate
    @Autowired
    private RedisTemplate<String, T> redisTemplate;


    @PostConstruct
    public void init() {
//        //这里可以动态设置value的序列化方式为JacksonJsonRedisSerializer
//        redisTemplate.setValueSerializer(new JacksonJsonRedisSerializer<>(clazz));
        logInfo("init Serializer");
    }


    protected final void setClazz(final Class<T> clazz) {
        this.clazz = Preconditions.checkNotNull(clazz);
        this.logger = LoggerFactory.getLogger(clazz);

        logInfo("init class");
    }

    protected void logInfo(String msg) {
        logger.info(msg);
    }

    @Override
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public void delete(List<String> keyList) {
        redisTemplate.delete(keyList);
    }


    /**
     * 压栈
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public Long list_push(String key, T value) {
        return redisTemplate.opsForList().leftPush(key, value);
    }

    /**
     * 出栈
     *
     * @param key
     * @return
     */
    @Override
    public T list_pop(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    /**
     * 入队
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public Long list_inQueue(String key, T value) {
        return redisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * 出队
     *
     * @param key
     * @return
     */
    @Override
    public T list_outQueue(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    @Override
    public Long list_length(String key) {
        return redisTemplate.opsForList().size(key);
    }

    /**
     * 范围检索
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    @Override
    public List<T> list_range(String key, int start, int end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 移除
     *
     * @param key
     * @param index
     * @param value
     */
    @Override
    public void list_remove(String key, long index, T value) {
        redisTemplate.opsForList().remove(key, index, value);
    }

    /**
     * 检索
     *
     * @param key
     * @param index
     * @return
     */
    @Override
    public T list_index(String key, long index) {
        return redisTemplate.opsForList().index(key, index);
    }

    /**
     * 置值
     *
     * @param key
     * @param index
     * @param value
     */
    @Override
    public void list_set(String key, long index, T value) {
        redisTemplate.opsForList().set(key, index, value);

    }

    /**
     * 裁剪
     *
     * @param key
     * @param start
     * @param end
     */
    @Override
    public void list_trim(String key, long start, int end) {
        redisTemplate.opsForList().trim(key, start, end);
    }


    /*
    value 操作
     */
    @Override
    public void value_set(String key, T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public T value_get(String key) {
        return redisTemplate.opsForValue().get(key);
    }


    /*
    Set 操作
     */
    @Override
    public Set<T> getSet(String key) {
        Set<T> dataSet = new HashSet<>();
        BoundSetOperations<String, T> setOperation = redisTemplate.boundSetOps(key);
        Long size = setOperation.size();
        for (int i = 0; i < size; i++) {
            dataSet.add(setOperation.pop());
        }
        return dataSet;
    }

    @Override
    public void setSet(String key, Set<T> set) {
        BoundSetOperations<String, T> setOperation = redisTemplate.boundSetOps(key);
        for (T s : set) {
            setOperation.add(s);
        }
    }

    /*
    HashMap 操作
     */
    @Override
    public Map getMap(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    @Override
    public void setMap(String key, Map<String, T> map) {
        HashOperations hashOperations = redisTemplate.opsForHash();
        if (null != map) {
            for (Map.Entry<String, T> entry : map.entrySet()) {
                hashOperations.put(key, entry.getKey(), entry.getValue());
            }
        }
    }

    @Override
    public Map getIntegerMap(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    @Override
    public void setIntegerMap(String key, Map<Integer, T> map) {
        HashOperations hashOperations = redisTemplate.opsForHash();
        if (null != map) {
            for (Map.Entry<Integer, T> entry : map.entrySet()) {
                hashOperations.put(key, entry.getKey(), entry.getValue());
            }
        }
    }


}
