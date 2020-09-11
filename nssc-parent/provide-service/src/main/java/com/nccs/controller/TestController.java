package com.nccs.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: nssc-parent
 * @author: xuzengsheng
 * @create: 2020-09-10 17:42
 * @description:
 **/

@RestController
public class TestController {

    @GetMapping("/provide/helloNacos")
    public String helloNacos() {
        return "hello,nacos-provide:8001";
    }
}
