package com.nccs.event.listener;

import com.nccs.event.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @program: nssc-parent
 * @author: xuzengsheng
 * @create: 2020-09-14 15:07
 * @description: 邮件监听（异步执行）
 **/
@Component
public class MailListener implements ApplicationListener<OrderEvent> {
    private static final Logger logger = LoggerFactory.getLogger(MailListener.class);

    @Override
//    @Async  //异步
    public void onApplicationEvent(OrderEvent event) {
        System.out.println(Thread.currentThread() + "...邮件监听到..." + event.getMessage() + "......" + event.getSource());
    }
}
