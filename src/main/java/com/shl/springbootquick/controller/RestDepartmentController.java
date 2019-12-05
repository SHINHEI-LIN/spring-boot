package com.shl.springbootquick.controller;

import com.shl.springbootquick.bean.Department;
import com.shl.springbootquick.bean.Employee;
import com.shl.springbootquick.mapper.DepartmentMapper;
import com.shl.springbootquick.mapper.EmployeeMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;

@org.springframework.web.bind.annotation.RestController
public class RestDepartmentController {

    @Resource
    private DepartmentMapper departmentMapper;

    @Resource
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
