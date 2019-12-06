package com.shl.springbootquick.jdbctemplate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class AccessDataSourceController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/jt/query")
    @ResponseBody
    public Map<String , Object> getData() {
        List<Map<String , Object>> ret = jdbcTemplate.queryForList("SELECT * FROM department");

        return ret.get(0);
    }
}
