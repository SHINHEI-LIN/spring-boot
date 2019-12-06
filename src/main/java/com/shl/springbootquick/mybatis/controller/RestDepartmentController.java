package com.shl.springbootquick.mybatis.controller;

import com.shl.springbootquick.bean.Department;
import com.shl.springbootquick.bean.Employee;
import com.shl.springbootquick.mybatis.mapper.DepartmentMapper;
import com.shl.springbootquick.mybatis.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;

@org.springframework.web.bind.annotation.RestController
public class RestDepartmentController {

    /**
     * @Resource 是j2ee的一个注解，默认按照以下顺序注入依赖对象Bean
     * ①如果同时指定了name和type，那么会从spring容器中找到唯一的Bean，找不到抛出异常；
     * ②如果指定了name，那么会从容器中根据该name查找Bean，找不到抛出异常；
     * ③如果指定了type，那么会从容器中根据type查找Bean，找不到或找到多个抛出异常；
     * ④如果没指定name和type，那么默认按照byName名称进行注入依赖对象Bean；
     */
    @Resource
    private DepartmentMapper departmentMapper;

    /**
     * @Autowired 是spring的一个注解，默认按照byType类型注入依赖依赖对象Bean
     * 默认该Bean必须存在，可以配合@Qualifier注解按照byName名称注入依赖对象Bean
     */
    @Autowired
    private EmployeeMapper employeeMapper;

    @RequestMapping("/getDept")
    @ResponseBody
    public Department getDept(@PathParam("id") Integer id) {
        Department department = departmentMapper.getDept(id);

        return department;
    }

    @RequestMapping("/insertDept")
    @ResponseBody
    public int insetDept(@PathParam("deptName") String deptName) {
        int ret = departmentMapper.insertDept(deptName);

        return ret;
    }

    @GetMapping("/emp/{id}")
    public Employee getEmp(@PathVariable("id") Integer id) {
        Employee employee = employeeMapper.getEmp(id);

        return employee;
    }
}
