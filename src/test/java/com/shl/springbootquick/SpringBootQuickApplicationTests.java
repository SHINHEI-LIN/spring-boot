package com.shl.springbootquick;

import com.shl.springbootquick.bean.Person;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Spring boot的单元测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class SpringBootQuickApplicationTests {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private Person person;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private ApplicationContext applicationContext;

	@Test
	public void getHelloService() {
		Boolean ret = applicationContext.containsBean("helloService");
		System.out.println(ret);
	}

	@Test
	void contextLoads() {
		System.out.println(person);
	}

	@Test
	void printLog() {
		logger.trace("trace log");
		logger.debug("debug log");
		logger.info("info log");
		logger.warn("warn log");
		logger.error("error log");
	}

	@Test
	void dateSource() throws SQLException {
		System.out.println(dataSource);
//		Connection connection = dataSource.getConnection();
////		Statement statement = connection.createStatement();
////		statement.execute("");
//		connection.close();
	}
}
