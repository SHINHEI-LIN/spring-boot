package com.shl.springbootquick.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.Map;

/**
 * Thymeleaf是SpringBoot支持的前端模板引擎
 */
@Controller
public class ThymeLeafController {

    @RequestMapping("/thymeleaf")
    public String success() {
        return "success";
    }

    @GetMapping({"/", "index.html", "index"})
    public String index() {
        return "login";
    }

    @GetMapping("/tl/hello")
    public String sayHello(Map<String , Object> pramMap){
        pramMap.put("hello", "<h1>你好</h1>");
        pramMap.put("users", Arrays.asList("lisi","wangwu","zhaoliu"));
        return "hello";
    }
}
