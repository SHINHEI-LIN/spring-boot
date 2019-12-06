package com.shl.springbootquick.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import java.util.List;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "person")
/**
 * @Validated 开启数据校验功能，在配置该属性的时候，需要满足相应的条件；
 */
@Validated
public class Person {
    /**
     * @Value 从配置文件中注入单个属性，如果需要一次性从配置文件中注入多个属性，
     * 可以选择@ConfigurationProperties
     */
    //@Value("${name}")
    private String name;
    private Integer age;
    private Integer sex;
    // 注入时校验当前字段是否为邮箱
    @Email
    private String Email;
    private List<Object> list;
    private Map<String, Object> map;
    private Dog dog;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", Email='" + Email + '\'' +
                ", list=" + list +
                ", map=" + map +
                ", dog=" + dog +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public List<Object> getList() {
        return list;
    }

    public void setList(List<Object> list) {
        this.list = list;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
