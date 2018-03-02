package com.bcht.its.commons.beans;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.nutz.dao.entity.annotation.Table;

import java.io.Serializable;

/**
 * 设备基础信息实体对象
 */
@Getter
@Setter
@ToString
@Table("dev_device")
public class DeviceInfo implements Serializable{
    /**设备编号  glbm12位+2位设备类型+4位序号  */
    private String sbbh;
    /**终端编号  设备所属终端的终端编号 只有设备是通过终端盒接入时，才有此属性*/
    private String tmnlNo;
    /**设备名称*/
    private String sbmc;
    /**设备类型 select * from frm_code c where c.xtlb='00' and c.dmlb='5001'
     *  01 :卡口
     *  02 : 执法取证设备
     *  03 : 视频监控设备
     *  04 : 交警执法站
     *  05 : 停车场
     *  06 : 气象检测设备
     *  07 : 流量检测设备
     *  08 : 可变信息标志
     *  09 : 交通信号控制设备
     *  10 : 交通部门
     *  11 : 消防部门
     *  12 : 医院
     *  13 : 修理厂
     *  14 : 广播设备
     *  15 : 交警队
     *  16 : 主要路口
     *  17 : 巡逻警车
     *  18 : 单警GPS
     *  19 : 交通事件检测设备
     *  99 : 终端设备(百诚自定义)
     * */
    private String sblx;
    /**设备IP*/
    private String ip;
    /**设备端口 海康默认端口*/
    private Short port=8000;
    /**登录名称*/
    private String loginName;
    /**登录 密码*/
    private String loginPass;
    /**设备安装的点位编号*/
    private String siteCode;
    /**过车数据接入方式 0:数据直接布控接入 1:通过终端接入*/
    private String  accessMode;
    /**经度*/
    private String jd;
    /**纬度*/
    private String wd;
    /**在线状态 01:在线 02:离线 03:异常*/
    private String zxzt="01";
    /**设备状态 1:正常 2:维修中 3:报废*/
    private String zt="1";
    /**设备厂商
     *  01 : 海康
     *  02 : 大华
     * */
    private String sbcs="01";
/*    *//** 设备消息类型(RabbitMQ消费者用来识别是增加、更新还是删除设备有：SAVE、UPDATE、DELETE三个值)*//*
    private String msgType;*/
    /**方向类型1上行2下行*/
    private String fxlx;
    /***/
    private String flmc;



}
