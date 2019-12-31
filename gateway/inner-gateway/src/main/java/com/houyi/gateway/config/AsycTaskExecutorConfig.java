package com.houyi.gateway.config;

import com.houyi.common.config.DefaultAsycTaskConfig;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: houyi
 * 线程池配置、启用异步
 * @Async quartz 需要使用
 */
@Configuration
public class AsycTaskExecutorConfig extends DefaultAsycTaskConfig {

}
