package com.bcht.its.snmp;

/**
 * Created by taoshide on 2016/10/22.
 */

import java.util.ArrayList;

/**
 * Created by Ouwei on 14-5-26.
 * CPU信息基本类
 */
public class CpuInfo {
    private String cpuDesc;      //  cpu信息描述
    private int coreNum;        // cpu核数
    private String userRate;      // cpu使用率
    private String sysRate;
    private String freeRate;      // cpu空闲率
    private ArrayList<CpuInfo> cpuDetailInfos;      // 每个核的信息

    public ArrayList<CpuInfo> getCpuDetailInfos() {
        return cpuDetailInfos;
    }

    public void setCpuDetailInfos(ArrayList<CpuInfo> cpuDetailInfos) {
        this.cpuDetailInfos = cpuDetailInfos;
    }

    public String getUserRate() {
        return userRate;
    }

    public void setUserRate(String userRate) {
        this.userRate = userRate;
    }

    public String getSysRate() {
        return sysRate;
    }

    public void setSysRate(String sysRate) {
        this.sysRate = sysRate;
    }

    public String getFreeRate() {
        return freeRate;
    }

    public void setFreeRate(String freeRate) {
        this.freeRate = freeRate;
    }

    public int getCoreNum() {
        return coreNum;
    }

    public void setCoreNum(int coreNum) {
        this.coreNum = coreNum;
    }

    public String getCpuDesc() {
        return cpuDesc;
    }

    public void setCpuDesc(String cpuDesc) {
        this.cpuDesc = cpuDesc;
    }
}