

package com.bcht.its.pojo;



import lombok.Getter;
import lombok.Setter;


/**
 * Created by ty on 2017/4/19.
 */


@Getter
@Setter
public class Devregional {

    private String qjid;//varchar(20) NOT NULL区间ID
    private String qjmc;//varchar(100) NULL区间名称
    private String qsdw;//varchar(32) NULL点位ＩＤ 由道路编号+路口号+米数形成
    private String jsdw;//varchar(32) NULL点位ＩＤ 由道路编号+路口号+米数形成
    private String  qjjl;//decimal(8,0) NULL区间距离 公里
    private String  fxlx;//char(1) NULL1:上行 2:下行
    private String  fxmc;//varchar(100) NULL方向名称
    private  Integer dcxgs;//int(11) NULL大车限高速
    private  Integer xcxgs;//int(11) NULL小车限高速
    private  Integer qtcxgs;//int(11) NULL其它车限高速
    private  Integer xds;//int(11) NULL限低速
    private  Integer dcxsbdz;//int(11) NULL大车限速波动
    private  Integer xcxsbdz;//int(11) NULL小车限速波动
    private  Integer zt;//int(11) NULL1:启用 2:未启用

}


