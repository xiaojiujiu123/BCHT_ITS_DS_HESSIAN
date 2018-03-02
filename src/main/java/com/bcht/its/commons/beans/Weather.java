package com.bcht.its.commons.beans;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 气象实体对象
 */
@Getter
@Setter
public class Weather {
    /**采集时间*/
    private Date cjsj;
    /**设备编号*/
    private String sbbh;
    /**设备厂商*/
    private String sbcs;
    /**气温*/
    private Float qw;
    /**相对湿度*/
    private Float xdsd;
    /**风向*/
    private Integer fx;
    /**风速*/
    private Float fs;
    /**每小时降水量毫米*/
    private Float jsl;
    /***能见度 米*/
    private Integer njd;
    /**路面温度*/
    private Float lmwd;
    /**雪层厚度 毫米*/
    private Float xchd;
    /**水层厚度 毫米*/
    private Float schd;
    /**冰层百度 毫米*/
    private Float bchd;
    /**干燥、潮湿、积水、结冰、积雪等5种路面状态*/
    private String lmzk;
    /** WMO天气现象编码*/
    private String tqxx;
}
