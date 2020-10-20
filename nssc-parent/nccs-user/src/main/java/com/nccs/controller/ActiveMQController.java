package com.nccs.controller;

import com.nccs.custom.BaseResponse;
import com.nccs.influx.utils.InfluxdbUtils;
import org.influxdb.InfluxDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Destination;
import javax.swing.*;

/**
 * @program: nssc-parent
 * @author: xuzengsheng
 * @create: 2020-10-12 14:50
 * @description:
 **/
@RestController
public class ActiveMQController {
    @Autowired // 也可以注入JmsTemplate，JmsMessagingTemplate对JmsTemplate进行了封装
    private JmsMessagingTemplate jmsTemplate;

    @Autowired
    InfluxdbUtils influxdbUtils;

    @Autowired
    private Destination destination; //注入存放的队列  com.nccs.config包下的BeanConfig中配置的bean


    @GetMapping("/send")
    public BaseResponse send(@RequestParam("name") String message) {
        //方法一：添加消息到消息队列
        jmsTemplate.convertAndSend(destination, message);
        System.out.println("nccs-user发出消息：" + message);

        //方法二：这种方式不需要手动创建queue，系统会自行创建名为test的队列
        //jmsMessagingTemplate.convertAndSend("test", name);
        return new BaseResponse("200", "消息发送成功,msg = " + message);
    }

}
