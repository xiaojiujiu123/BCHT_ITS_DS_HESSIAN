package com.bcht.its.commons.beans;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/18.
 */
@Getter
@Setter
public class HeartBeat implements Serializable {
    private String lsh="";         //流水号
    private String sbbh="";        //设备编号
    private String sbip="";        //设备ip
    private String sbzt="";        //设备状态
    private String gzms="";        //故障描述
    private String jd="";          //经度
    private String wd="";          //纬度
    private String sbqdsj="";      //设备最近启动时间 yyyy-mm-dd hh24:mi:ss
    private String sbdqsj="";      //设备当前时间  yyyy-mm-dd hh24:mi:ss

    @Override
    public String toString() {
        return "{" +
                "lsh='"+lsh+'\''+
                "sbbh='" + sbbh + '\'' +
                ", sbip='" + sbip + '\'' +
                ", sbzt='" + sbzt + '\'' +
                ", gzms='" + gzms + '\'' +
                ", jd='" + jd + '\'' +
                ", wd='" + wd + '\'' +
                ", sbqdsj='" + sbqdsj + '\'' +
                ", sbdqsj='" + sbdqsj + '\'' +
                '}';
    }
}
