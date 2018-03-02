package com.bcht.its.commons.beans;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 *  终端信息实体对象
 */
@Getter
@Setter
public class TerminalInfo implements Serializable {
    /**终端编号  glbm12位+2位设备类型(99)+4位序号*/
    private String tmnlNo;
    /**终端名称*/
    private String zdmc;
    /**终端IP*/
    private String ip;
    /**终端端口*/
    private Short port;
    /**终端登录用户名*/
    private String loginName;
    /**终端登录密码*/
    private String loginPass;
    /**在线状态 01:在线 02:离线 03:异常*/
    private String zxzt;
    /**设备厂商 01:海康 02:大华*/
    private String sbcs;

 /*   *//** 终端消息类型(RabbitMQ消费者用来识别是增加、更新还是删除设备有：SAVE、UPDATE、DELETE三个值)*//*
    private String msgType;*/
}
