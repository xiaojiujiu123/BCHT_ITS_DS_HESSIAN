package com.bcht.its.snmp;

/**
 * SNMP协议参数类型
 * Created by taoshide on 2016/10/21.
 */
public class OIDType {
    //系统类型参数

    /**
     * 获取系统基本信息
     */
    public static final String SYS_DESC_GET = ".1.3.6.1.2.1.1.1.0";
    /**
     * 监控时间
     */
    public static final String SYS_UPTIME_GET = ".1.3.6.1.2.1.1.3.0";
    /**
     * 系统联系人
     */
    public static final String SYS_CONTACT_GET = ".1.3.6.1.2.1.1.4.0";
    /**
     * 获取机器名称
     */
    public static final String SYS_NAME_GET = ".1.3.6.1.2.1.1.5.0";
    /**
     * 机器提供的服务
     */
    public static final String SYS_SERVICE_GET= ".1.3.6.1.2.1.1.7.0";
    /**
     * 机器运行的进程列表
     */
    public static final String HRSWRUNNAME_WALK = ".1.3.6.1.2.1.25.4.2.1.2";
    /**
     * 系统安装的软件列表
     */
    public static final String HRSWINSTALLEDNAME_WALK=".1.3.6.1.2.1.25.6.3.1.2";

    //网络接口

    /**
     * 网络接口的数目
     */
    public static final String IFNUMBER_GET = ".1.3.6.1.2.1.2.1.0";
    /**
     * 网络接口信息描述
     */
    public static final String IFDESCR_WALK=".1.3.6.1.2.1.2.2.1.2";
    /**
     * 网络接口类型
     */
    public static final String IFTYPE_WALK = ".1.3.6.1.2.1.2.2.1.3";
    /**
     * 接口发送和接收的最大IP数据报[BYTE]
     */
    public static final String IFMTU_WALK=".1.3.6.1.2.1.2.2.1.4";
    /**
     * 口当前带宽[bps]
     */
    public static final String IFSPEED_WALK=".1.3.6.1.2.1.2.2.1.5";
    /**
     * 接口的物理地址
     */
    public static final String IFPHYSADDRESS_WALK=".1.3.6.1.2.1.2.2.1.6";
    /**
     * 接口当前操作状态[up|down]
     */
    public static final String IFOPERSTATUS_WALK=".1.3.6.1.2.1.2.2.1.8";
    /**
     * 接口收到的字节数
     */
    public static final String IFINOCTET_WALK=".1.3.6.1.2.1.2.2.1.10";
    /**
     * 接口发送的字节数
     */
    public static final String IFOUTOCTET_WALK=".1.3.6.1.2.1.2.2.1.16";
    /**
     * 接口收到的数据包个数
     */
    public static final String IFINUCASTPKTS_WALK=".1.3.6.1.2.1.2.2.1.11";
    /**
     * 接口发送的数据包个数
     */
    public static final String IFOUTUCASTPKTS_WALK=".1.3.6.1.2.1.2.2.1.17";

    //CPU

    /**
     * 用户CPU百分比
     */
    public static final String SSCPUUSER_GET=".1.3.6.1.4.1.2021.11.9.0";
    /**
     * 系统CPU百分比
     */
    public static final String SSCPUSYSTEM_GET = ".1.3.6.1.4.1.2021.11.10.0";


    //".1.3.6.1.2.1.25.2.2.0" 可用内存
}
