package com.shl.springbootquick.Thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Thymeleaf是SpringBoot支持的前端模板引擎
 */
@Controller
public class ThymeLeafController {

    @RequestMapping("/thymeleaf")
    public String success() {
        return "success";
    }
}
