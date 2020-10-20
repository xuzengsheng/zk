package com.nccs.service;

import com.nccs.influx.measurement.ActiveMqLog;
import org.influxdb.InfluxDB;
import org.influxdb.dto.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

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
//    @Autowired
//    private InfluxdbUtils influxdbUtils;
    @Autowired
    InfluxDB influxDB;

    // 使用JmsListener配置消费者监听的队列 queue1是队列的名字，需要与写入的队列名称一致
    @JmsListener(destination = "queue1")
    // SendTo 会将此方法返回的数据, 写入到 OutQueue 中去.
    @SendTo("SQueue")
    public void handleMessage(String message) {
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        ActiveMqLog activeMqLog = new ActiveMqLog();
        activeMqLog.setModule("nccs-gateway");
        activeMqLog.setLevel("info");
        activeMqLog.setMsg(message);
        activeMqLog.setTime(new Date().toString());
//        influxdbUtils.insertOne(activeMqLog);
        influxDB.write(Point.measurement("activeMqLog")
                .time(System.nanoTime(), TimeUnit.NANOSECONDS) //纳秒级别
//                .addField("module","nccs-gateway")
//                .addField("level","info")
//                .addField("msg",message)
                .addFieldsFromPOJO(activeMqLog)
                .build());
        System.out.println("nccs-gateway 成功接受消息:" + message);
        return;
    }
}
