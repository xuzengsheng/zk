package com.nccs.controller;

import com.nccs.custom.BaseResponse;
import com.nccs.influx.measurement.ActiveMqLog;
import com.nccs.influx.utils.InfluxdbUtils;
import org.influxdb.InfluxDB;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: nssc-parent
 * @author: xuzengsheng
 * @create: 2020-10-19 13:54
 * @description:
 **/
@RestController
public class InfluxDbTestController {
    @Autowired
    InfluxdbUtils influxdbUtils;
    @Autowired
    InfluxDB influxDB;

    @GetMapping("/get")
    BaseResponse get() {
        String query = "select * from activeMqLog";
//        List<ActiveMqLog> logs = influxdbUtils.fetchResults(query, ActiveMqLog.class);
//        System.out.println("==========");
//        logs.forEach(item -> {
//            System.out.println(item.toString());
//        });
//        System.out.println("==========");

        QueryResult query1 = influxDB.query(new Query(query));
        System.out.println("==========");
        query1.getResults().get(0).getSeries().get(0).getValues().forEach(item -> {
            System.out.println(item.toString());
        });
        System.out.println("==========");

        return BaseResponse.success(query1);
    }

}
