package com.houyi.gateway.feign.fallback;

import org.springframework.stereotype.Component;

import com.houyi.gateway.feign.ClientApiService;

import cn.hutool.core.collection.CollectionUtil;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * menuService降级工场
 *
 * @Author: houyi
 * @date 2019/1/18
 */
@Slf4j
@Component
public class ClientApiServiceFallbackFactory implements FallbackFactory<ClientApiService> {
    @Override
    public ClientApiService create(Throwable throwable) {
        return apis -> {
            log.error("调用findApisByClientId异常：{}", apis, throwable);
            return CollectionUtil.newArrayList();
        };
    }
}

