package com.shl.springbootquick.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
/**
 * @ConfigurationProperties
 * ①前缀prefix定义了该类对应到配置文件中父级属性
 * ②类属性名称必须与配置文件中的属性对应
 * ③需要配合@Component才能被spring中的组件扫描扫描到；
 */
@ConfigurationProperties(prefix = "dog")
public class Dog {
    private String name;
    private Integer age;

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
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
}
