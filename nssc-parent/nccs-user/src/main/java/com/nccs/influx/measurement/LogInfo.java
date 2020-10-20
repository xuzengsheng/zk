package com.nccs.influx.measurement;

import lombok.Data;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

import java.io.Serializable;

/**
 * @program: nssc-parent
 * @author: xuzengsheng
 * @create: 2020-10-16 10:35
 * @description: InfluxDB中, measurement对应于传统关系型数据库中的table(database为配置文件中的log_management).
 * InfluxDB里存储的数据称为时间序列数据,时序数据有零个或多个数据点.
 * 数据点包括time(一个时间戳)，measurement(例如logInfo)，零个或多个tag，其对应于level,module,device_id),至少一个field(即日志内容,msg=something error).
 * InfluxDB会根据tag数值建立时间序列(因此tag数值不能选取诸如UUID作为特征值,易导致时间序列过多,导致InfluxDB崩溃),并建立相应索引,以便优化诸如查询速度.
 **/

@Data
@Measurement(name = "logInfo")
public class LogInfo implements Serializable {

    // Column中的name为measurement中的列名
    // 此外,需要注意InfluxDB中时间戳均是以UTC时保存,在保存以及提取过程中需要注意时区转换
    @Column(name = "time")
    private String time;  //时间
    @Column(name = "module")
    private String module; //所属模块
    // 注解中添加tag = true,表示当前字段内容为tag内容
    @Column(name = "level", tag = true)
    private String level; //异常等级
    @Column(name = "deviceId")
    private String deviceId;
    @Column(name = "msg")
    private String msg;   //信息

}
