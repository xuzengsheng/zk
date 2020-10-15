package com.nccs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @program: nssc-parent
 * @author: xuzengsheng
 * @create: 2020-10-15 09:43
 * @description:
 **/
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
@EnableFeignClients //多个“,”隔开
public class WebServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebServerApplication.class, args);
    }
}
