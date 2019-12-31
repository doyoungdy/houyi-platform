package com.houyi.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.houyi.common.annotation.EnableLoginArgResolver;

/**
 * @author houyi.com
 */
@EnableLoginArgResolver
@EnableDiscoveryClient
@EnableFeignClients
@EnableTransactionManagement
@SpringBootApplication
public class ClientCenterApp {
    public static void main(String[] args) {
        SpringApplication.run(ClientCenterApp.class, args);
    }
}