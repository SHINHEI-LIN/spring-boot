package com.shl.springbootquick;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 该方法必须放在根目录下，因为SpringBoot只会扫描该类同级目录下和根目录的子目录下的注解
 */
// 全局设置扫描mapper的文件夹，不需要在每一个mapper文件下添加@Mapper注解
@MapperScan("com.shl.springbootquick.mybatis.mapper")
@SpringBootApplication
public class SpringBootQuickApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootQuickApplication.class, args);
	}

}
