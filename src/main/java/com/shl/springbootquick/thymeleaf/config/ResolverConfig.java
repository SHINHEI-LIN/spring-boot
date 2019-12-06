package com.shl.springbootquick.thymeleaf.config;

import com.shl.springbootquick.thymeleaf.interceptor.LoginHandlerInterceptor;
import com.shl.springbootquick.thymeleaf.resolver.MyLocalResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Configuration 指明这是一个配置类
 * 作用与在配置文件中用<bean></bean>标签添加组件相同
 */
@Configuration
public class ResolverConfig extends WebMvcConfigurerAdapter {

    /**
     * @Bean 将方法的返回值放到IOC容器中, 组件的默认ID为方法名
     */
    @Bean
    public LocaleResolver localeResolver() {
        // 启用自定义的国际化处理器，默认的国际化处理器会失效
        return new MyLocalResolver();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/shenghail").setViewName("success");
    }

    //所有的WebMvcConfigurerAdapter组件都会一起起作用
    @Bean //将组件注册在容器
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter(){
        WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("/login");
                registry.addViewController("/index.html").setViewName("/login");
                registry.addViewController("/main.html").setViewName("dashboard");
            }


            //注册拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                //super.addInterceptors(registry);
                //静态资源；  *.css , *.js
                //SpringBoot已经做好了静态资源映射
                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                        .excludePathPatterns("/index.html","/","/login");
            }
        };
        return adapter;
    }
}
