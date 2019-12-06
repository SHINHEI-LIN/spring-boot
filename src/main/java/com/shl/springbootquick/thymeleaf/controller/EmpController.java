package com.shl.springbootquick.thymeleaf.controller;

import com.shl.springbootquick.thymeleaf.exper.dao.EmployeeDao;
import com.shl.springbootquick.thymeleaf.exper.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

@Controller
public class EmpController {

    @Autowired
    private EmployeeDao employeeDao;

    /**
     * 查询所有员工
     * @return
     */
    @GetMapping("/emps")
    public String list(Model model){
        Collection<Employee> employees =  employeeDao.getAll();
        // 放在请求域中
        model.addAttribute("emps", employees);
        return "emp/list";
    }
}
