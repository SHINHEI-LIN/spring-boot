package com.shl.springbootquick.mybatis.config;

import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;

/**
 * 设置mybatis相关的配置
 */
@org.springframework.context.annotation.Configuration
public class MybatisConfig {

    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return new ConfigurationCustomizer() {
            @Override
            public void customize(Configuration configuration) {
                // 开启驼峰命名法
                configuration.setMapUnderscoreToCamelCase(true);
            }
        };
    }
}
