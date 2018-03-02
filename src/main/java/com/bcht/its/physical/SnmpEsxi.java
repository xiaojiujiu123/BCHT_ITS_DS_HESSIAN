package com.bcht.its.physical;




import com.bcht.its.snmp.MemoryInfo;
import com.bcht.its.snmp.SystemInfo;

import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * SnmpEsxi:主要针对ESXI服务器的snmp 采集
 * Created by taoshide on 2016/10/22.
 */
public class SnmpEsxi extends SnmpBase {
    public SnmpEsxi(String ip, String community) {
        super(ip, community);
    }

    public MemoryInfo getMemoryInfo() throws Exception {
        MemoryInfo memoryInfo = new MemoryInfo();
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMinimumFractionDigits(1);
        nf.setMaximumFractionDigits(1);

        int index = this.getMemoryIndex();
        double physicalSize = Double.parseDouble(snmpGet(props.getWindowDiskSize() + "." + index))*Double.parseDouble(snmpGet(props.getWindowDiskAmount() + "." + index))/(1024*1024*1024);
        double physicalUsedSize = Double.parseDouble(snmpGet(props.getWindowDiskUsed() + "." + index))*Double.parseDouble(snmpGet(props.getWindowDiskAmount() + "." + index))/(1024*1024*1024);

        memoryInfo.setMemorySize(super.getMemorySize());
        memoryInfo.setMemoryUsedSize(nf.format(physicalUsedSize));
        memoryInfo.setMemoryFreeSize(nf.format(physicalSize - physicalUsedSize));

        nf.setMinimumFractionDigits(0);
        nf.setMaximumFractionDigits(0);
        memoryInfo.setMemoryPercentage(nf.format(physicalUsedSize/physicalSize*100));
        return memoryInfo;
    }

    private int getMemoryIndex() throws Exception {
        ArrayList<String> diskIndexTable = this.snmpWalk(props.getWindowDiskIndex());
//        String physicalMemoryID = props.getHrStorageRamDisk();
        String physicalMemoryID = "1.3.6.1.2.1.25.2.1.20";
        int index = 0;
        int i = 1;
        for (String str : diskIndexTable) {
            String diskType = this.snmpGet(props.getWindowDiskType() + "." + i);
            if (diskType.equals(physicalMemoryID))
                index = Integer.parseInt(str);
            i++;
        }
        diskIndexTable = null;
        return index;
    }

    public SystemInfo getSysInfo() throws Exception {
        SystemInfo sysInfo = super.getSysInfo();

        sysInfo.setMemoryInfo(this.getMemoryInfo());
        sysInfo.setDiskInfos(getDiskInfo());
        sysInfo.setCpuInfo(getCpuInfo());
        return sysInfo;
    }
}