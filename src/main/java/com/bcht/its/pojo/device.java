package com.bcht.its.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by ${zhaojinglong} on 2017/4/18 0018.
 */
@Getter
@Setter
public class device {
    private String sbbh;          //设备编号 glbm12位+2位设备类型+4位序号
    private String tmnlno;        //终端编号
    private String sbmc;          //设备名称
    private String sitecode;      //点位ＩＤ 由道路编号+路口号+米数形成
    private String sblx;          //设备类型c.dmlb='5001' and c.xtlb='00'取字典中电子设备
    private String ip;           //设备ip
    private Integer port;          //设备端口
    private String loginname;     //用户名
    private String loginpass;     //密码
    private String accessmode;    //过车数据接入方式 0:数据直接布控接入 1:通过终端接入
    private String jd;            //经度
    private String wd;            //维度
    private String zxzt;          //设备在线状态 01在线 02离线 03 用户名密码错误
    private String zt;            //1:正常 2:维修中 3:报废
    private String sbcs;          //厂商01:海康 02:大华
    private String fxlx;          //方向类型
    private String flmc;          //方向名称


}
