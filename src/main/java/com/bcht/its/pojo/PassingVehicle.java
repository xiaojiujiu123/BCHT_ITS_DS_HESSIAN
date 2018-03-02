package com.bcht.its.pojo;

/**
 * Created by Administrator on 2017/5/18.
 */

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PassingVehicle implements Serializable {
    private String lsh="";         //流水号
    private String sbbh="";        //设备编号
    private String gcsj="";        //过车时间 yyyy-MM-dd HH:mm:ss
    private String hphm="";        //号牌号码
    private String hpys="";        //号牌颜色
    private String hpzl="";        //号牌种类
    private String cwkc="";        //车外廓长
    private String csys="";        //车身颜色
    private String cllx="";        //车辆类型
    private String clsd="";        //车辆速度
    private String cdbh="";        //车道编号
    private String fxlx="";        //方向类型
    private String fx="";          //方向名称
    private String wfdm="";        //违法代码
    private String tpurl="";       //图片路径
    private String tpurl1="";      //图片路径1
    private String tpurl2="";      //图片路径2
    private String jrsj ; //接入时间
    @Override
    public String toString() {
        return "{" +
                "lsh=\"" + lsh + "\"" +
                "sbbh=\"" + sbbh + "\"" +
                ", gcsj=\"" + gcsj + "\"" +
                ", hphm=\"" + hphm + "\"" +
                ", hpys=\"" + hpys + "\"" +
                ", hpzl=\"" + hpzl + "\"" +
                ", cwkc=\"" + cwkc + "\"" +
                ", csys=\"" + csys + "\"" +
                ", cllx=\"" + cllx + "\"" +
                ", clsd=\"" + clsd + "\"" +
                ", cdbh=\"" + cdbh + "\"" +
                ", fxlx=\"" + fxlx + "\"" +
                ", fx=\"" + fx + "\"" +
                ", wfdm=\"" + wfdm + "\"" +
                ", tpurl=\"" + tpurl + "\"" +
                ", tpurl1=\"" + tpurl1 + "\"" +
                ", tpurl2=\"" + tpurl2 + "\"" +
                "}";
    }
}
