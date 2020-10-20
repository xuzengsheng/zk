package com.nccs.influx.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Query;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @program: nssc-parent
 * @author: xuzengsheng
 * @create: 2020-10-19 14:20
 * @description:
 **/

@Data
@Slf4j
@Configuration
public class InfluxConfig {
    @Value("${spring.influx.url}")
    private String url;

    @Value("${spring.influx.user}")
    private String userName;

    @Value("${spring.influx.password}")
    private String password;

    @Value("${spring.influx.database}")
    private String database;

    @Value("${spring.influx.retentionPolicy:autogen}")
    private String retentionPolicy;
    // InfluxDB实例
    private InfluxDB influxDB;

    // 数据保存策略
    public static String policyNamePix = "logmonitor";

    @Bean
    public InfluxDB influxdb() {
        InfluxDB influxDB = InfluxDBFactory.connect(url, userName, password);
        try {
            /*
             * 异步插入：
             * enableBatch这里第一个是point的个数，第二个是时间，单位毫秒
             * point的个数和时间是联合使用的，如果满100条或者60 * 1000毫秒
             * 满足任何一个条件就会发送一次写的请求。
             */
            influxDB.setDatabase(database).enableBatch(100, 1000 * 60, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //设置默认策略
            //  influxDB.setRetentionPolicy("sensor_retention");
            influxDB.setRetentionPolicy(retentionPolicy); //
        }
        //设置日志输出级别
        influxDB.setLogLevel(InfluxDB.LogLevel.BASIC);
        return influxDB;
    }



//    public InfluxConfig(String userName, String password, String url, String database,
//                        String retentionPolicy) {
//        this.userName = userName;
//        this.password = password;
//        this.url = url;
//        this.database = database;
//        this.retentionPolicy = retentionPolicy == null || "".equals(retentionPolicy) ? "autogen" : retentionPolicy;
//        this.influxDB = influxDbBuild();
//    }

    /**
     * 连接数据库 ，若不存在则创建
     *
     * @return influxDb实例
     */
//    private InfluxDB influxDbBuild() {
//        if (influxDB == null) {
//            influxDB = InfluxDBFactory.connect(url, userName, password);
//        }
//        try {
//            createDB(database);
//            influxDB.setDatabase(database);
//        } catch (Exception e) {
//            log.error("create influx db failed, error: {}", e.getMessage());
//        } finally {
//            influxDB.setRetentionPolicy(retentionPolicy);
//        }
//        influxDB.setLogLevel(InfluxDB.LogLevel.BASIC);
//        return influxDB;
//    }

    /****
     *  创建数据库
     * @param database
     */
//    private void createDB(String database) {
//        influxDB.query(new Query("CREATE DATABASE " + database));
//    }
}
