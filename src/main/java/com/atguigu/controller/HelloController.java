package com.atguigu.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    Logger logger = LoggerFactory.getLogger(this.getClass());
//    @Value("${person.lastName}")
    private String name;

    @RequestMapping("/hello")
    public String hello(){
        logger.info("hello this is logger!");
        return "Hello World!";
    }

    @RequestMapping("/sayHello")
    public String sayHello(){
        return "Hello " + name;
    }

}
