package com.shl.springbootquick.config;

import com.shl.springbootquick.service.HelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Configuration 指明这是一个配置类
 * 作用与在配置文件中用<bean></bean>标签添加组件相同
 */
@Configuration
public class MyConfig {

    // 将方法的返回值放到IOC容器中, 组件的默认ID为方法名
    @Bean
    public HelloService helloService() {
        System.out.println("配置类给容器添加组件");
        return new HelloService();
    }

}
