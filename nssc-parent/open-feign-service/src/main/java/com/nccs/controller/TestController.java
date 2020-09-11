package com.nccs.controller;

import com.nccs.feign.RemoteClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: nssc-parent
 * @author: xuzengsheng
 * @create: 2020-09-11 09:04
 * @description:
 **/
@RestController
public class TestController {

    @Autowired
    private RemoteClient remoteClient;

    @GetMapping("/feign/test")
    public String test() {
        return remoteClient.helloNacos();
    }


}
