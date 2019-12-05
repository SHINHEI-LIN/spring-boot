package com.shl.springbootquick;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 全局设置扫描mapper的文件夹，不需要在每一个mapper文件下添加@Mapper注解
@MapperScan("com.shl.springbootquick.mapper")
@SpringBootApplication
public class SpringBootQuickApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootQuickApplication.class, args);
	}

}
