package com.bcht.its.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/12.
 */
@Getter
@Setter
@ToString
public class TrffPassInfo implements Serializable {
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
    private byte[] data;

}
