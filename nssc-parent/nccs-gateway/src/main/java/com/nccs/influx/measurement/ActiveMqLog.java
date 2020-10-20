package com.nccs.influx.measurement;

import lombok.Data;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

/**
 * @program: nssc-parent
 * @author: xuzengsheng
 * @create: 2020-10-19 11:52
 * @description:
 **/
@Data
@Measurement(name = "activeMqLog")
public class ActiveMqLog {

    // Column中的name为measurement中的列名
    // 此外,需要注意InfluxDB中时间戳均是以UTC时保存,在保存以及提取过程中需要注意时区转换
    @Column(name = "time")
    private String time;  //时间
    // 注解中添加tag = true,表示当前字段内容为tag内容
    @Column(name = "module", tag = true)
    private String module; //所属模块
    @Column(name = "level")
    private String level; //异常等级

    @Column(name = "msg")
    private String msg;   //信息


}
