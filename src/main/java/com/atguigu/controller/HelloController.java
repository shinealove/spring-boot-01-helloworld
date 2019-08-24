package com.atguigu.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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

    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
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
        map.put("hello", "你好");
        return "success";
    }

    @RequestMapping("/index")
    public String index(Map<String, Object> map){
        map.put("hello", "你好");
        return "login";
    }



}
