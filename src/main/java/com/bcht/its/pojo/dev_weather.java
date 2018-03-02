package com.bcht.its.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by ${zhaojinglong} on 2017/4/18 0018.
 */
@Getter
@Setter
public class dev_weather {
    private String sbbh;        //varchar(18) NOT NULL 设备编号 glbm12位+2位设备类型+4位序号;
    private String sbmc;       //varchar(50) NOT NULL 设备名称
    private String ip;        //varchar(15) NOT NULL 设备IP
    private String port;      //int(11) NOT NULL设备端口
    private String serialnum; //varchar(6) NULL串口号 只有在接入方式为串口时才有效
    private String loginname; //varchar(50) NULL登录名称 用于SDK连接认证
    private String loginpass; //varchar(50) NULL
    private String accessmode;// varchar(2) NOT NULL过车数据接入方式 0:串口直接接入 1:串口转RS485
    private String jd;        //varchar(20) NULL
    private String wd;       //varchar(20) NULL
    private String zxzt;     //varchar(2) NOT NULL设备在线状态 01在线 02离线 03 用户名密码错误
    private String zt;       //varchar(2) NOT NULL1:正常 2:维修中 3:报废
    private String sbcs;     //varchar(2) NULL01: 锦州阳光 02:交通气象

}
