package com.nccs.controller;

import com.nccs.custom.BaseResponse;
import com.nccs.event.OrderEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: nssc-parent
 * @author: xuzengsheng
 * @create: 2020-10-10 14:02
 * @description:
 **/
@RestController
public class TestController {
    @Autowired
    private ApplicationContext applicationContext;


    @Value("${server.port}")
    private int port;

    @GetMapping("/test")
    public BaseResponse test(){
        return BaseResponse.success(port);
    }

    /**
     * 调用事件通知
     *
     * @param message
     * @return
     */
    @GetMapping("/provide/order")
    public String order(@RequestParam("message") String message) {
        OrderEvent orderEvent = new OrderEvent(this, message);
        applicationContext.publishEvent(orderEvent);
        return message;
    }
}
