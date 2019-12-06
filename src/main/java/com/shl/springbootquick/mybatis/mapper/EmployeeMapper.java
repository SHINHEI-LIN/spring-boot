package com.shl.springbootquick.mybatis.mapper;

import com.shl.springbootquick.bean.Employee;

/**
 * 使用mybatis配置方式操作数据库
 */
public interface EmployeeMapper {
    public Employee getEmp(Integer id);

    public int insertEmp(Employee employee);
}
