package com.bcht.its.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by ${zhaojinglong} on 2017/4/21 0021.
 */
@Getter
@Setter
public class Gcxx {
    private  String gcbh;            //过车编号
    private  String sbxh;            //设备型号
    private  Date gcsj;            //过车时间
    private  String hphm;            //号牌号码
    private  String hpzl;            //号牌种类
    private  String hpys;            //号牌颜色
    private  String cwkc;            //车外廓长 单位：cm，为空时用0填充
    private  String clys;            //车辆颜色
    private  String cllx;            //车辆类型
    private  float  sd;              //车速
    private  String cdbh;            //车道编号
    private  String fx;              //行车方向
    private  String sjlx;            //车辆类型
    private  String wfdm;            //违法代码
    private  String tpurl;           //图片路径1
    private  String tpurl1;          //图片路径2
    private  String tpurl2;          //图片路径3
    private  String platePosition;   //号牌位置，号牌在通行车辆图片中的位置
    private  String csbh;            //设备的厂商编号，用于后期MQ消费把原始编号代码解析成对应厂商的代码，如：号牌颜色、车辆类型等
    private  Date acceptTime;      //接收服务接收到数据的时间
    private  Date analysisTime;    //解析服务解析成功之后的时间
    private  Date sendToMQTime;    //发送到消息队列中的时间
    private  Date getForMQTime;    //从消息队列中取出的时间
    private  Date storageTime;     //数据入库时间
    private  Date sendToLDTime;    //发送到蓝盾平台的时间
    private  Date sendToRminfTime; //发送到集成指挥平台的时间
    private  Date sendToTmriTime;  //发送到六合一的时间
    private  String sendToMQState;   //是否发送至rabbitMQ,'0'为否，‘1’为是
}
