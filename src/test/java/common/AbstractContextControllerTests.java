package common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

@WebAppConfiguration
@ContextConfiguration({"file:src/main/webapp/WEB-INF/root-context.xml",
		"file:src/main/webapp/WEB-INF/persistent.xml",
		"file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml"})
public class AbstractContextControllerTests {

	@Autowired
	protected WebApplicationContext wac;

}
