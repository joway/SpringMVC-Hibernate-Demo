package common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

@WebAppConfiguration
@ContextConfiguration({"file:src/main/webapp/WEB-INF/root-context.xml",
		"file:src/main/webapp/WEB-INF/hibernate-validator.xml",
		"file:src/main/webapp/WEB-INF/hibernate-jpa.xml",
		"file:src/main/webapp/WEB-INF/redis.xml",
		"file:src/main/webapp/WEB-INF/cache.xml",
		"file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml"})
public class WebContextConfiguration {

	@Autowired
	protected WebApplicationContext wac;

}
