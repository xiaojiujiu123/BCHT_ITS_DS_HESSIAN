package com.bcht.its.pojo;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;

/**
 * Created by ${zhaojinglong} on 2017/4/24 0024.
 */
@Getter
@Setter
public class ds_weather {
    private  String lsh;     //varchar(50) NOT NULL流水号
    private  String sbcs;    //varchar(10) NULL设备厂商
    private  String sbxh;    //varchar(20) NULL设备或终端序列号
    private  String cjsj;    //varchar(20) NULL采集时间
    private  String hjwd ;   //varchar(10) NULL环境气温(℃)
    private  String lmwd;    //varchar(10) NULL路面温度(℃)
    private  String hjsd;    //varchar(10) NULL环境湿度(%RH)
    private  String smhd;    //varchar(10) NULL水膜厚度(mm)
    private  String bhd;     //varchar(10) NULL冰厚度(mm)
    private  String xshd;    //varchar(10) NULL雪深厚度(m)
    private  String shxs;    //varchar(10) NULL湿滑系数
    private  String lmzk;    //varchar(5) NULL路面状况
    private  String qy;      //varchar(10) NULL气压(hPa)
    private  String fwj;     //varchar(10) NULL方位角(°)
    private  String xdfx;    //varchar(10) NULL相对风向(°)
    private  String sjfx;    //varchar(10) NULL实际风向(°)
    private  String fsssz;   //varchar(10) NULL风速瞬时值(m/s)
    private  String fspjz_1; //varchar(10) NULL2分钟平均风速值(m/s)
    private  String fspjz_2; //varchar(10) NULL10分钟平均风速值(m/s)
    private  String yljgljz; //varchar(10) NULL雨量间隔累计值(mm)
    private  String ylrljz;  //varchar(10) NULL雨量日累计值(mm)
    private  String rzsjljz; //varchar(10) NULL日照时间隔累计值(分钟)
    private  String rzsrljz; //varchar(10) NULL日照时日累计值(分钟)
    private  String njdssz;  //varchar(5) NULL能见度瞬时值(m)
    private  String njdpjz;  //varchar(5) NULL10分钟平均能见度(m)
    private  String dl;      //varchar(5) NULL电量(V)
    private  String accepttime;  //datetime NULL接收服务接收到数据的时间
    private  String analysistime;//datetime NULL解析服务解析成功之后的时间
    private  String sendtomqtime;//datetime NULL发送到消息队列中的时间
    private  String getformqtime;//datetime NULL从消息队列中取出的时间
    private  String storagetime;//datetime NULLstorageTime
    public   String getlmzkinfo(String lmzk,String sbcs){
      if(sbcs.equals("01")){
          switch (lmzk){
              case "0":
                  lmzk = "干";
                  break;
              case "1":
                  lmzk="潮";
                  break;
              case "2":
                  lmzk="湿";
                  break;
              case "3":
                  lmzk="冰";
                  break;
              case "4":
                  lmzk="雪/冰";
                  break;
              case "5":
                  lmzk="湿含融雪剂";
                  break;
              case "6":
                  lmzk="冰水混合";
                  break;
              case "8":
                  lmzk="雪";
                  break;
             default:
                 lmzk="未定义";
                 break;
          }
      }else{

      }
      return lmzk;
    }


}
