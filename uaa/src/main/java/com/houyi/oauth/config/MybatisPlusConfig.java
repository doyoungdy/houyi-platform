package com.houyi.oauth.config;

import com.houyi.db.config.DefaultMybatisPlusConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: houyi
 * @date 2018/12/10
 */
@Configuration
@MapperScan({"com.houyi.oauth.mapper*"})
public class MybatisPlusConfig extends DefaultMybatisPlusConfig {

}
