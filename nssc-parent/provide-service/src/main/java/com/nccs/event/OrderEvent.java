package com.nccs.event;

import org.springframework.context.ApplicationEvent;

/**
 * @program: nssc-parent
 * @author: xuzengsheng
 * @create: 2020-09-14 15:04
 * @description: 订单事件
 **/
public class OrderEvent extends ApplicationEvent {
    private String message;

    public OrderEvent(Object source, String message) {
        super(source);
        this.message = message;
    }


    public OrderEvent(Object source) {
        super(source);
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
