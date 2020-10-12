package com.nccs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * @program: nssc-parent
 * @author: xuzengsheng
 * @create: 2020-10-12 15:31
 * @description:
 **/
@Component
public class ActiveMQSerivce {
    @Autowired // 也可以注入JmsTemplate，JmsMessagingTemplate对JmsTemplate进行了封装
    private JmsMessagingTemplate jmsTemplate;

    // 使用JmsListener配置消费者监听的队列 queue1是队列的名字，需要与写入的队列名称一致
    @JmsListener(destination = "queue1")
    // SendTo 会将此方法返回的数据, 写入到 OutQueue 中去.
    @SendTo("SQueue")
    public String handleMessage(String message) {
        System.out.println("nccs-gateway 成功接受消息:" + message);
        return "成功接受:" + message;
    }
}
