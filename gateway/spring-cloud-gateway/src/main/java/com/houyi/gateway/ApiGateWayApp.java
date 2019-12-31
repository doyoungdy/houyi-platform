package com.houyi.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: houyi
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ApiGateWayApp {
    public static void main(String[] args) {
        SpringApplication.run(ApiGateWayApp.class, args);
    }
}