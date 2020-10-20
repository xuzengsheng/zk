package com.nccs.test;

import com.nccs.influx.utils.InfluxdbUtils;
import com.nccs.custom.BaseCodeEnum;
import com.nccs.influx.measurement.LogInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: nssc-parent
 * @author: xuzengsheng
 * @create: 2020-10-13 10:56
 * @description:
 **/

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test01 {
    @Autowired
    InfluxdbUtils influxdbUtils;

    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    @Test
    public void test() throws InterruptedException {
        for (int i = 0; i < 10; i++) {

            new Thread(() -> {
                int i1 = atomicInteger.incrementAndGet();
                LogInfo logInfo = new LogInfo();
                logInfo.setDeviceId(BaseCodeEnum.TOKEN_EXPIRED.getCode());
                logInfo.setLevel(i1 + "");
                logInfo.setModule("NCC-USER");
                logInfo.setMsg(BaseCodeEnum.TOKEN_EXPIRED.getMsg());
                logInfo.setTime(new Date().toString());
                influxdbUtils.insertOne(logInfo);
                System.out.println("=== 第 " + i1 + " 条数据插入成功 ===");
            }).start();

            new Thread(() -> {
                String query = "select * from logInfo";
                List<LogInfo> logs = influxdbUtils.fetchResults(query, LogInfo.class);
                System.out.println("=== 查询到 " + logs.size() + " 条数据 ===");
            }).start();


            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
