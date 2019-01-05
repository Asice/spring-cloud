package com.qurich.centerhystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * https://www.kancloud.cn/fymod/springcloud2/784132
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableTurbine
public class CenterHystrixApplication {

    public static void main(String[] args) {
        SpringApplication.run(CenterHystrixApplication.class, args);
    }

}

