package com.atguigu.controller;

import com.atguigu.exception.UserNotExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
public class HelloController {
    Logger logger = LoggerFactory.getLogger(this.getClass());
//    @Value("${person.lastName}")
    private String name;

//    @RequestMapping({"/", "/index.html"})
//    public String index(){
//        return "index";
//    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    @ResponseBody
    @GetMapping("/query")
    public Map<String, Object> map(){
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select  * from department");
        return list.get(0);
    }

    @ResponseBody
    @RequestMapping("/hello")
    public String hello(@RequestParam("user") String user){
        if (user.equals("aaa")){
            throw new UserNotExistException();
        }
        logger.info("hello this is logger!");
        return "Hello World!";
    }

    @ResponseBody
    @RequestMapping("/sayHello")
    public String sayHello(){
        return "Hello " + name;
    }

    @RequestMapping("/success")
    public String success(Map<String, Object> map){
        map.put("hello", "<h1>你好</h1>");
        map.put("users", Arrays.asList("zhangsan", "lisi", "wangwu"));
        return "success";
    }

    @RequestMapping("/index")
    public String index(Map<String, Object> map){
        map.put("hello", "你好");
        return "login";
    }



}
