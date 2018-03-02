package com.bcht.its.commons.beans;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 过车信息中关键时间点
 */
@Getter
@Setter
public class VechilePassInfoTimes implements Serializable{
    private static final long serialVersionUID = 1L;
    /**车辆经过时间 从SDK协议中获取*/
    private Date jgsj;
   private Date jssj	;//N	DATE	Y			一级接收时间
   private Date scsj	;//N	DATE	Y			一级上传时间
   private Date ffsj	;//N	DATE	Y			一级分发时间
   private Date rksj	;//N	DATE	Y			一级入库时间
}
