package com.atguigu;

import com.atguigu.bean.Article;
import com.atguigu.bean.Book;
import com.atguigu.bean.Person;
import com.atguigu.entities.Employee;
import com.atguigu.mapper.EmployeeMapper;
import com.atguigu.repository.BookRepository;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloWorldApplicationTests {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    DataSource dataSource;

    @Autowired
    private Person person;

    @Autowired
    ApplicationContext ioc;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    AmqpAdmin amqpAdmin;

    @Autowired
    JestClient jestClient;

    @Autowired
    BookRepository bookRepository;

    @BeforeClass()
    public static void init(){
        System.setProperty("es.set.netty.runtime.available.processors", "false");
    }

    @Test
    public void testESBookRespository(){
        Book book = new Book();
        bookRepository.index(book);
    }

    @Test
    public void testESSearch(){
        String json = "{\n" +
                "    \"query\" : {\n" +
                "        \"match\" : {\n" +
                "            \"content\" : \"hello\"\n" +
                "        }\n" +
                "    }\n" +
                "}";
        Search search = new Search.Builder(json).addIndex("atguigu").addType("news").build();
        try {
            SearchResult result = jestClient.execute(search);
            System.out.println(result.getJsonString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testJestClient() {
        Article article = new Article();
        article.setId(1);
        article.setTitle("好消息");
        article.setTitle("zhangsan");
        article.setContent("hello world");
        Index index = new Index.Builder(article).index("atguigu").type("news").build();
        try {
            jestClient.execute(index);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testAMQPAdminExchange(){
//        amqpAdmin.declareExchange(new DirectExchange("amqpadmin.exchange"));
//        System.out.println("创建完成");
//        amqpAdmin.declareQueue(new Queue("amqpadmin.queue", true));
        amqpAdmin.declareBinding(new Binding("amqpadmin.queue", Binding.DestinationType.QUEUE, "amqpadmin.exchange", "amqp.haha", null));
    }

//    @Autowired
//    RedisTemplate<Object, Employee> empRedisTemplate;

    @Test
    public void testSendMsg(){
        rabbitTemplate.convertAndSend("exchange.fanout", "", new Book("红楼梦", "曹雪芹"));
    }

    @Test
    public void testGetMessage(){
        Object o = rabbitTemplate.receiveAndConvert("atguigu.news");
        System.out.println(o.getClass());
        System.out.println(o);
    }

    @Test
    public void testRabbitmq(){
//        rabbitTemplate.send(exchange, rooteKey, message);
//        rabbitTemplate.convertAndSend(exchange, routeKey, object);
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "这是第一个消息");
        map.put("data", Arrays.asList("helloworld", 123, true));
        rabbitTemplate.convertAndSend("exchange.direct", "atguigu.news", new Book("西游记", "吴承恩"));
    }

    @Test
    public void testRedis(){
//        ValueOperations<String, String> op = stringRedisTemplate.opsForValue();
//        op.append("msg","hello");

//        String msg = stringRedisTemplate.opsForValue().get("msg");
//        System.out.println("=== msg is: " + msg + " ===");

//        stringRedisTemplate.opsForList().leftPush("mylist", "1");
//        stringRedisTemplate.opsForList().leftPush("mylist", "2");
        Employee employee = employeeMapper.getEmpById(1);
        redisTemplate.opsForValue().set("emp-01",employee);
    }

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

    @Test
    public void testSelectEmployeeById(){
        Employee employee = employeeMapper.getEmpById(1);
        System.out.println(employee.toString());
    }
}
