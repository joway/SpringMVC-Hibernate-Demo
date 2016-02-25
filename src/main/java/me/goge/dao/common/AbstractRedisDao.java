package me.goge.dao.common;

import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;

import java.io.Serializable;

/**
 * @Author joway
 * @Email joway.w@gmail.com
 * @Date 16/2/24.
 */
public class AbstractRedisDao<T extends Serializable> implements RedisDao<T> {

    protected Class<T> clazz;
    private Logger logger;

    //直接使用autowire就可以引用到配置文件中的redis-template
    @Autowired
    private RedisTemplate<String, T> template;

    private ValueOperations<String, T> operations;


    protected void init(final Class<T> clazz) {
        this.logger = LoggerFactory.getLogger(clazz);

        //这里设置value的序列化方式为JacksonJsonRedisSerializer
        template.setValueSerializer(new JacksonJsonRedisSerializer<>(clazz));
        operations = template.opsForValue();
    }


    protected final void setClazz(final Class<T> clazz) {
        this.clazz = Preconditions.checkNotNull(clazz);
        init(clazz);
    }

    protected void logInfo(String msg) {
        logger.info(msg);
    }

    @Override
    public void set(String key, T value) {
        operations.set(key, value);
    }

    @Override
    public T get(String key) {
        return operations.get(key);
    }
}
