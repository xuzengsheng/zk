package com.nccs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @program: nssc-parent
 * @author: xuzengsheng
 * @create: 2020-09-27 09:58
 * @description:
 **/
@SpringBootApplication
@MapperScan("com.nccs.mapper")
@ComponentScan(value = "com.nccs.*")
public class UserServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApp.class, args);
    }
}
