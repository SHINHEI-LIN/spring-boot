package com.shl.springbootquick.mybatis.mapper;

import com.shl.springbootquick.bean.Department;
import org.apache.ibatis.annotations.*;

/**
 * 使用Mybatis注解的方式操作数据库
 */
//指定一个操作数据库的mapper
//@Mapper
public interface DepartmentMapper {

    @Select("select * from department where id=#{id}")
    public Department getDept(Integer id);

    @Delete("delete from department where id=#{id}")
    public int delDept(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into department set department_name=#{deptName}")
    public int insertDept(String deptName);

    @Update("update department set department_name=#{deptName} where id=#{id}")
    public int updateDept(String deptName);
}
