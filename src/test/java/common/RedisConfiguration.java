package common;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @Author joway
 * @Email joway.w@gmail.com
 * @Date 16/2/24.
 */

// 经过测试, hibernate-jpa.xml 文件不加载的话,是会报错的
@WebAppConfiguration
@ContextConfiguration({"file:src/main/webapp/WEB-INF/root-context.xml",
        "file:src/main/webapp/WEB-INF/hibernate-jpa.xml",
        "file:src/main/webapp/WEB-INF/redis.xml"})
public class RedisConfiguration {
}
