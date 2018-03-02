package com.bcht.its.commons.beans;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by JXX on 2017/6/2.
 */
@Getter
@Setter
public class DeviceheartBeat {
           private String  lsh; //信息流水号.
           private String sbbh; //设备编号.
           private String sbip; //设备IP.
           private String sbzt; //设备状态.
           private String gzms; //故障描述.
           private String jd; //精度.
           private String wd; //维度.
           private String qdsj; //设备最近启动时间.
           private String dqsj; //设备当前时间.

}
