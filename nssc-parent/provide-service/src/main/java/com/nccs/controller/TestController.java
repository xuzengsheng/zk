package com.nccs.controller;

import com.nccs.event.OrderEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: nssc-parent
 * @author: xuzengsheng
 * @create: 2020-09-10 17:42
 * @description:
 **/

@RestController
public class TestController {
    @Autowired
    private ApplicationContext applicationContext;

    @GetMapping("/provide/helloNacos")
    public String helloNacos() {
        return "hello,nacos-provide:8001";
    }

    /**
     * 调用事件通知
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
