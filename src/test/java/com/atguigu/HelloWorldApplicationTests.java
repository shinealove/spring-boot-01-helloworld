package com.atguigu;

import com.atguigu.bean.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloWorldApplicationTests {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    DataSource dataSource;

    @Autowired
    private Person person;

    @Autowired
    ApplicationContext ioc;

    @Test
    public void contextLoads(){
//        System.out.println(person);
        logger.trace("这是 trace 日志。。。。。。。。。。");
        logger.debug("这是 debug 日志。。。。。。。。。。");
        logger.info("这是 info 日志。。。。。。。。。。");
        logger.warn("这是 warn 日志。。。。。。。。。。");
        logger.error("这是 error 日志。。。。。。。。。。");

    }

    @Test
    public void testHelloService(){
        boolean b = ioc.containsBean("helloService");
        System.out.println(b);
    }

    @Test
    public void testJdbc() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println(dataSource.getClass());
        System.out.println(connection);
        connection.close();
    }
}
