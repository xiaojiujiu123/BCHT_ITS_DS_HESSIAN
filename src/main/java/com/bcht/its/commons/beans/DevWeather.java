package com.bcht.its.commons.beans;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *  气象设备对象
 */
@Getter
@Setter
@ToString
public class DevWeather {
    /**设备编号*/
    private String sbbh ;//   varchar(18) NOT NULL COMMENT '设备编号  glbm12位+2位设备类型+4位序号  ',
    /**设备名称*/
    private String sbmc  ;//  varchar(50) NOT NULL,
    /**串口转RS服务器地址*/
    private String ip    ;//varchar(15) NOT NULL,
    /**串口转RS服务器端口号*/
    private Integer port   ;// int(11) NOT NULL COMMENT '设备端口',
    /**串口号  只有在接入方式为串口时才有效*/
    private String serialNum ;//   varchar(6) DEFAULT NULL COMMENT '串口号  只有在接入方式为串口时才有效',
    /**登录名称*/
    private String loginName  ;//  varchar(50) DEFAULT NULL COMMENT '登录名称 用于SDK连接认证',
    /**登录密码*/
    private String loginPass   ;// varchar(50) DEFAULT NULL,
    /**过车数据接入方式 0:串口直接接入 1:串口转RS485*/
    private String accessMode  ;//  varchar(2) NOT NULL COMMENT '过车数据接入方式 0:串口直接接入 1:串口转RS485',
    private String jd  ;//  varchar(20) DEFAULT NULL,
    private String wd  ;//  varchar(20) DEFAULT NULL,
    private String zxzt ;//   varchar(2) NOT NULL DEFAULT '2' COMMENT '设备在线状态 01在线 02离线 03 用户名密码错误',
    private String zt   ;// varchar(2) NOT NULL DEFAULT '1' COMMENT '1:正常 2:维修中 3:报废',
    /**设备厂商 01: 锦州阳光 02:交通气象*/
    private String sbcs  ;//  varchar(2) DEFAULT NULL COMMENT '01: 锦州阳光 02:交通气象'
}
