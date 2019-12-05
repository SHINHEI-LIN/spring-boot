package com.shl.springbootquick.mapper;

import com.shl.springbootquick.bean.Employee;

public interface EmployeeMapper {
    public Employee getEmp(Integer id);

    public int insertEmp(Employee employee);
}
