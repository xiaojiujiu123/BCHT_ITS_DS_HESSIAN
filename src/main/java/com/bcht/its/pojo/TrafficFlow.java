package com.bcht.its.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by JXX on 2017/6/2.
 * 	交通流量实体类
 */
@Getter
@Setter
public class TrafficFlow {
           private String lsh ; //设备编号.
           private String sbbh ; //设备编号.
           private String tjsd ; //统计时段.
           private String tjzqs ; //统计周期数.
           private String txcls ; //通行车辆数.
           private String dcs ; //大车数.
           private String xcs ; //小车数.
           private String pjsd ; //平均速度.
           private String pjcc ; //平均车长.
           private String pjctsj ; //平均车头时距.
           private String pjcsjj ; //平均车头间距.
           private String pjpdcd ; //平均排队长度.

}
