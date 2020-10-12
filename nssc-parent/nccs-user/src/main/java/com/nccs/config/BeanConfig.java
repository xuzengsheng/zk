package com.nccs.config;


import javax.jms.Destination;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: nssc-parent
 * @author: xuzengsheng
 * @create: 2020-10-12 14:45
 * @description:
 **/
@Configuration
public class BeanConfig {

    //定义存放消息的队列
    @Bean
    public Destination destination() {
        return new ActiveMQQueue("queue1");
    }
}