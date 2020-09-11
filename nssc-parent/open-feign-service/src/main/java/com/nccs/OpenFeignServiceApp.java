package com.nccs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @program: nssc-parent
 * @author: xuzengsheng
 * @create: 2020-09-10 17:57
 * @description:
 **/
@SpringBootApplication
@EnableDiscoveryClient //开启服务注册发现
@EnableFeignClients //开启feign客户端
@EnableHystrix  //开启熔断降级
public class OpenFeignServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(OpenFeignServiceApp.class);
    }
}
