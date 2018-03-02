package com.bcht.its.commons.beans;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 交通车辆通行信息
 */
@Setter
@Getter
public class TrafficVechilePassInfo  implements Serializable{
    private static final long serialVersionUID = 1L;
    /**过车编号 由设备编号_号牌号码_号牌颜色_车辆序号*/
    private String gcbh;
    /**设备序号*/
    private String sbxh;
    /** 经过时间字符串*/
    private String jgsjStr;
    /**号牌号码*/
    private String hphm;
    /**号牌种类*/
    private String hpzl;
    /**号牌颜色*/
    private String hpys;
    /**车外廓长 单位：cm，为空时用0填充*/
    private float cwkc;
    /**车辆（车身）颜色*/
    private String  clys;
    /**车辆类型*/
    private String cllx;
    /**车辆速度 单位：km/h*/
    private float sd;
    /**车道编号*/
    private int cdbh;
    /**方向类型*/
    private String fx;
    /**事件类型(代码)，事件检测设备专用字段*/
    private String sjlx;
    /**违法代码*/
    private String wfdm;
    /**图片URL(WEB访问地址)*/
    private String tpurl;
    /**图片URL(WEB访问地址)*/
    private String tpurl1;
    /**图片URL(WEB访问地址)*/
    private String tpurl2;
    /**号牌位置，号牌在通行车辆图片中的位置 */
    private String platePosition;
    /**设备的厂商编号，用于后期MQ消费把原始编号代码解析成对应厂商的代码，如：号牌颜色、车辆类型等 01为海康，02为大华*/
    private String csbh;

    private String sjly	;//N	VARCHAR2(12)	Y			数据来源  字段来源于jksj的主键,用来区分数据是从哪里传过来的
    private String  lsh	    ;//N	VARCHAR2(100)	Y			第三方厂商的过车编号，如果有级联关系则用，分割
    private List<byte[]> dataList;  //图片数组
    /**车辆经过时间 从SDK协议中获取*/
    private Date jgsj; //过车时间
    private Date hjsj	;//N	DATE	Y			一级接收时间
    private Date scsj	;//N	DATE	Y			一级上传时间
    private Date ffsj	;//N	DATE	Y			一级分发时间
    private Date rksj	;//N	DATE	Y			一级入库时间
    private  Date jrsj; //接入时间
}
