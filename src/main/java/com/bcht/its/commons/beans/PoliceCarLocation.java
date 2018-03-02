package com.bcht.its.commons.beans;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by JXX on 2017/6/1.
 */
@Getter
@Setter
public class PoliceCarLocation implements Serializable {
         private String   lsh; // 信息流水号.
         private String   sbbh; // 设备编号.
         private String    hpzl; // 号牌种类.
         private String   hphm; // 号牌号码.
         private String    sbsj; // 上报事件.
         private String   jd; // 精度.
         private String     wd; // 维度.
         private String   sd; // 速度.
         private String      fx; // 方向.
         private String sjly;  //数据来源
         private VechilePassInfoTimes vechilePassInfoTimes;

}
