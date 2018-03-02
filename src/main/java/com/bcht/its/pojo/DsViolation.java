package com.bcht.its.pojo;

import lombok.Getter;
import lombok.Setter;
import org.nutz.dao.entity.annotation.Table;

import java.util.Date;

/**
 * Created by Administrator on 2017/5/25.
 */
@Table("ds_violation")
@Getter
@Setter
public class DsViolation {
    private String gcbh		        ;//    系统过车编号 由设备编号_号牌号码_号牌颜色_车辆序号 构成
    private String sbxh		        ;//	设备序号
    private Date gcsj	  	        ;//过车时间
    private String hphm;//
    private String hpzl	  ;//
    private String hpys;//
    private String cwkc	  		    ;//    车外廓长 单位：cm，为空时用0填充
    private String clys;//
    private String cllx;//
    private String sd;//
    private String cdbh	   	        ;//车道编号
    private String fx	   		    ;//    方向类型
    private String sjlx	  		    ;//    事件类型(代码)，事件检测设备专用字段
    private String wfdm				;//违法代码
    private String tpurl;//
    private String tpurl1;//
    private String tpurl2;//
    private String plateposition	;//	号牌位置，号牌在通行车辆图片中的位置
    private String csbh	            ;//        设备的厂商编号，用于后期mq消费把原始编号代码解析成对应厂商的代码，如：号牌颜色、车辆类型等
    private Date accepttime		;//	接收服务接收到数据的时间
    private Date analysistime   	;//	解析服务解析成功之后的时间
    private Date sendtomqtime  	;//	发送到消息队列中的时间
    private Date getformqtime    	;//        从消息队列中取出的时间
    private Date storagetime	    ;//        数据入库时间
    private Date sendtoldtime   	;//	发送到蓝盾平台的时间
    private Date sendtorminftime  ;//			发送到集成指挥平台的时间
    private Date sendtotmritime  	;//        发送到六合一的时间
    private String sendtomqstate	;//	    是否发送至rabbitMQ,'0'为

}
