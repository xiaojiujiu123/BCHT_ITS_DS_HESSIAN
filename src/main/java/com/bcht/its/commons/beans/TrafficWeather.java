package com.bcht.its.commons.beans;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by JXX on 2017/6/2.
 */
@Getter
@Setter
public class TrafficWeather {
            private String lsh; //设备编号.
            private String sbbh; //设备编号.
            private String jcsd; //监测时段.
            private String jczqs; //监测周期数.
            private String njd; //能见度.
            private String wdu; //温度.
            private String fsdj; //风速等级.
            private String fx; //风向.
            private String jyl; //降雨（积水）量.
            private String jxl; //降雪（积雪）量.
            private String jcsjlx; //检测事件类型.

}
