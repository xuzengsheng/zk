package com.nccs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @program: nssc-parent
 * @author: xuzengsheng
 * @create: 2020-09-27 09:58
 * @description:
 **/
@SpringBootApplication
@MapperScan("com.nccs.mapper")
@ComponentScan(value = "com.nccs.*")
@EnableDiscoveryClient
@EnableAsync  //开启异步
//@EnableJms  //启用消息队列 此注解可以不要
public class UserServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApp.class, args);
    }
}
