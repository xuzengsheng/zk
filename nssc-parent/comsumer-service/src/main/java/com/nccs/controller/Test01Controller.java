package com.nccs.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @program: nssc-parent
 * @author: xuzengsheng
 * @create: 2020-09-10 17:38
 * @description:
 **/
@RestController
@RefreshScope  //开启配置自动刷新。当修改了配置中心的配置时，不用重启服务，自动获取更新后的值
@Slf4j
public class Test01Controller {
    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/comsumer/helloNacos")
    public String helloNacos() {
        return "hello,nacos-consumer:9001";
    }


    @Value("${config.info}")
    private String info;

    @GetMapping("/comsumer/test")
    String test() {
        return info;
    }


    @GetMapping("/comsumer/test1")
    public String test1() {
        //使用restTemplate调用其他服务
        log.info("===== 正在使用restTemplate调用nacos-provide服务 =====");
        return restTemplate.getForObject("http://nacos-provide/provide/helloNacos", String.class);
    }

}
