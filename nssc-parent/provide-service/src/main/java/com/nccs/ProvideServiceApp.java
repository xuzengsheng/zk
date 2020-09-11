package com.nccs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @program: nssc-parent
 * @author: xuzengsheng
 * @create: 2020-09-10 14:57
 * @description:
 **/
@EnableDiscoveryClient
@SpringBootApplication
public class ProvideServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(ProvideServiceApp.class, args);
    }
}
