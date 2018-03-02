package com.bcht.its.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by ${zhaojinglong} on 2017/4/18 0018.
 */
@Getter
@Setter
public class terminal {
    private String tmnlno;    //终端编号
    private String zdmc;      //终端名称
    private String ip;       //终端IP
    private Integer port;      //终端端口
    private String loginname; //登录用户名
    private String loginpass; //登录密码
    private String zxzt;      //设备状态 01:在线 02:离线 03:异常
    private String sbcs;      //01:海康 02:大华
}
