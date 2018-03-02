package com.bcht.its.commons.beans;

import lombok.Getter;
import lombok.Setter;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

/**
 * Created by Administrator on 2017/7/21.
 */
@Getter
@Setter
@Table("dev_t_server")
public class DevTServer {
    @Name
    private String id;//N	VARCHAR2(18)	N			主键ID
    private String fwbh;//N	VARCHAR2(18)	N			"主机编号	设备备案编号：12位管理部门+2位设备类型+4位序号"
    private String fwip;//N	VARCHAR2(16)	N			"设备IP	主机IP"
    private String fwzt;//N	VARCHAR2(1)	N			设备状态：正常:0,破坏:1,断电:2,故障:3
    private String gzms;//N	VARCHAR2(200)	Y			故障描述：当设备状态为空时
    private String cpusyl;//N	VARCHAR2(5)	Y			cpu使用率
    private String nczl;//N	VARCHAR2(5)	Y			内存用量
    private String ncsyl;//N	VARCHAR2(5)	Y			内存使用量
    private String nckxl;//N	VARCHAR2(5)	Y			内存空闲量
    private String cpzkj;//N	VARCHAR2(10)	Y			磁盘总空间（G)
    private String cpsykj;//N	VARCHAR2(120)	Y			磁盘使用空间：{"C":"使用空间","D":"使用空间","E":"使用空间"} json数据格式
    private String cpkxkj;//N	VARCHAR2(120)	Y			磁盘空闲时间：{"C":"空闲空间","D":"空闲空间","E":"空闲空间"} json数据格式}
    private String qdsj;//N	DATE	Y			最近启动时间 日期格式字符串“yyyy-mm-dd hh24:mi:ss”,形如“2003-09-11 11:07:23”
    private String dqsj;//N	DATE	Y			当前时间 日期格式字符串“yyyy-mm-dd hh24:mi:ss”,形如“2003-09-11 11:07:23”
    private String glbm;//N	VARCHAR2(20)	Y			管理部门
    private String fwmc;//N	VARCHAR2(100)	Y			服务器名称
   private String  wlsm;//	N	VARCHAR2(1)	Y			网络说明 0专网，1公安网，2互联网

}
