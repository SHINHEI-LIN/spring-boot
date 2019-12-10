package com.shl.springbootquick.thymeleaf.controller;

import com.shl.springbootquick.thymeleaf.exper.dao.DepartmentDao;
import com.shl.springbootquick.thymeleaf.exper.dao.EmployeeDao;
import com.shl.springbootquick.thymeleaf.exper.entities.Department;
import com.shl.springbootquick.thymeleaf.exper.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class EmpController {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DepartmentDao departmentDao;

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

    /**
     * 跳转到员工添加页面
     * @return
     */
    @GetMapping("/emp")
    public String toAddPage(Model model) {
        // 来到添加页面，查出所有部门
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);

        return "emp/add";
    }

    /**
     * 添加员工
     * SpringMvc会自动将请求参数与入参的对象绑定，需要将请求参数的名称与对象中属性名称一一对应
     * @param employee
     * @return
     */
    @PostMapping("/emp")
    public String addEmp(Employee employee) {
        // 来到员工列表页面
        employeeDao.save(employee);
        // redirect: 表示重定向一个请求,在这里location可以重定向到任意URL，
        // 既然是浏览器重新发出了请求，则就没有什么request传递的概念了
        // forward：表示转发一个请求,转发的路径必须是同一个web容器下的url，
        // 其不能转向到其他的web路径上去，中间传递的是自己的容器内的request
        return "redirect:/emps";
    }

    /**
     * 来到员工编辑页面，并回显信息
     * @return
     */
    @GetMapping("/emp/{id}")
    public String editEmp(@PathVariable("id")Integer id, Model model) {
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp", employee);

        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);
        // 修改和添加共用一个页面
        return "emp/add";
    }

    //员工修改；需要提交员工id；
    @PutMapping("/emp")
    public String updateEmployee(Employee employee){
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    /**
     * 删除员工
     * @param id
     * @return
     */
    @DeleteMapping("/emp/{id}")
    public String deleteEmp(@PathVariable("id") Integer id){
        employeeDao.delete(id);

        return "redirect:/emps";
    }
}
