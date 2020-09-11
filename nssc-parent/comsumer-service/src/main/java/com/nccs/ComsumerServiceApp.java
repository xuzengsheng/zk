package com.nccs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @program: nssc-parent
 * @author: xuzengsheng
 * @create: 2020-09-10 14:57
 * @description:
 **/
@EnableDiscoveryClient
@SpringBootApplication
public class ComsumerServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(ComsumerServiceApp.class, args);
    }


    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
