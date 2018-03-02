package com.bcht.its.physical;


;import com.bcht.its.snmp.CpuInfo;
import com.bcht.its.snmp.SystemInfo;

/**
 * 主要针对Linux 服务器snmp 采集
 * Created by Administrator on 14-5-26.
 */
public class SnmpLinux extends SnmpBase {
    public SnmpLinux(String ip, String community) {
        super(ip, community);
    }

    public CpuInfo getCpuInfo() throws Exception {
        CpuInfo cpuInfo = super.getCpuInfo();
        cpuInfo.setSysRate(snmpGet(props.getLinuxSysCPURate()));
        cpuInfo.setUserRate(snmpGet(props.getLinuxUserCPURate()));
        cpuInfo.setFreeRate(snmpGet(props.getLinuxFreeCPURate()));
        return cpuInfo;
    }

    public SystemInfo getSysInfo() throws Exception {
        SystemInfo sysInfo = super.getSysInfo();

        sysInfo.setMemoryInfo(getMemoryInfo());
        sysInfo.setDiskInfos(getDiskInfo());
        sysInfo.setCpuInfo(getCpuInfo());
        return sysInfo;
    }
}