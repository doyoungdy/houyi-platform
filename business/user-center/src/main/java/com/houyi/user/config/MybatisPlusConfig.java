package com.houyi.user.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.houyi.db.config.DefaultMybatisPlusConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: houyi
 * @date 2018/12/10
 */
@Configuration
@MapperScan({"com.houyi.user.mapper*"})
public class MybatisPlusConfig extends DefaultMybatisPlusConfig {
    @Bean
    public ISqlInjector sqlInjector(){
        return new LogicSqlInjector();
    }
}
