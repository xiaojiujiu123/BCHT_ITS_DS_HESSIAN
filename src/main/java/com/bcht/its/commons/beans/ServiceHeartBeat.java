package com.bcht.its.commons.beans;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by JXX on 2017/6/2.
 */
@Getter
@Setter
public class ServiceHeartBeat implements Serializable{
        private String   lsh="" ; //信息流水号.
        private String fwbh="" ; //主机编号.
        private String   fwip="" ; //设备IP.
        private String fwzt="" ; //设备状态.
        private String  gzms="" ; //故障描述.
        private String cpusyl =""; //cup使用率.
        private String  nczl =""; //内存总量.
        private String ncsyl =""; //内存使用量.
        private String  nckxl="" ; //内存空限量.
        private String cpzkj =""; //磁盘总空间.
        private String  cpsykj="" ; //磁盘使用空间.
        private String cpkxkj =""; //磁盘空闲空间.
        private String   qdsj="" ; //最近启动时间.
        private String dqsj =""; //当前时间.

        @Override
        public String toString() {
                return "{" +
                        "lsh='" + lsh + '\'' +
                        ", fwbh='" + fwbh + '\'' +
                        ", fwip='" + fwip + '\'' +
                        ", fwzt='" + fwzt + '\'' +
                        ", gzms='" + gzms + '\'' +
                        ", cpusyl='" + cpusyl + '\'' +
                        ", nczl='" + nczl + '\'' +
                        ", ncsyl='" + ncsyl + '\'' +
                        ", nckxl='" + nckxl + '\'' +
                        ", cpzkj='" + cpzkj + '\'' +
                        ", cpsykj='" + cpsykj + '\'' +
                        ", cpkxkj='" + cpkxkj + '\'' +
                        ", qdsj='" + qdsj + '\'' +
                        ", dqsj='" + dqsj + '\'' +
                        '}';
        }
}
