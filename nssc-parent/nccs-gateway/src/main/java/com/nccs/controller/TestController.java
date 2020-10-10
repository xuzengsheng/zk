package com.nccs.controller;

import com.nccs.custom.BaseResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: nssc-parent
 * @author: xuzengsheng
 * @create: 2020-10-10 14:02
 * @description:
 **/
@RestController
public class TestController {
    @Value("${server.port}")
    private int port;

    @GetMapping("/test")
    public BaseResponse test(){
        return BaseResponse.success(port);
    }
}
