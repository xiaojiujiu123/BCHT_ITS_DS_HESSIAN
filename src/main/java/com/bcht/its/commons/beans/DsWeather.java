package com.bcht.its.commons.beans;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017/4/20.
 */
@Getter
@Setter
public class DsWeather  implements Serializable {
    private String lsh ;//流水号
    private String sbcs ;//设备厂商
    private String sbxh ;//设备或终端序列号
    private String cjsj ;//采集时间
    private String hjwd ;//环境气温(℃)
    private String lmwd ;//路面温度(℃)
    private String hjsd ;//环境湿度(%RH)
    private String smhd ;//水膜厚度(mm)
    private String bhd ;//冰厚度(mm)
    private String xshd ;//雪深厚度(m)

    private String shxs ;//湿滑系数
    private String lmzk ;//路面状况
    private String qy ;//气压(hPa)
    private String fwj ;//方位角(°)
    private String xdfx ;//相对风向(°)
    private String sjfx ;//实际风向(°)
    private String fsssz ;//风速瞬时值(m/s)
    private String fspjz_1 ;//2分钟平均风速值(m/s)
    private String fspjz_2 ;//10分钟平均风速值(m/s)
    private String yljgljz ;//雨量间隔累计值(mm)

    private String ylrljz ;//雨量日累计值(mm)
    private String rzsjljz ;//日照时间隔累计值(分钟)
    private String rzsrljz ;//日照时日累计值(分钟)
    private String njdssz ;//能见度瞬时值(m)
    private String njdpjz ;//10分钟平均能见度(m)
    private String dl ;//电量(V)
    private Date acceptTime ;//接收服务接收到数据的时间
    private String analysisTime ;//解析服务解析成功之后的时间
    private String sendToMQTime ;//发送到消息队列中的时间
    private String getForMQTime ;//从消息队列中取出的时间

    private String storageTime ;//数据入库时间
    private String sendToMQState ;//是否发送至rabbitMQ,'0'为否，‘1’为是
}
