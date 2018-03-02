package com.bcht.its.Scheduled;

import com.bcht.its.Application;
import com.bcht.its.commons.beans.DevTServer;
import com.bcht.its.physical.SnmpWindow;
import com.bcht.its.service.BchtTrffService;
import com.bcht.its.snmp.CpuInfo;
import com.bcht.its.snmp.DiskInfo;
import com.bcht.its.snmp.MemoryInfo;
import com.bcht.its.snmp.SystemInfo;
import com.bcht.its.utils.MyPeropertiesUtil;
import com.caucho.hessian.client.HessianProxyFactory;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2017/7/21.
 */
//@Component
public class ScheduledService {
    @Autowired
    private Dao dao;
    public static HessianProxyFactory proxyFactory = null;
    public static BchtTrffService bchtTrffService = null;

    @Scheduled(cron = "0 */5 * * * ?")
    public void SerachForService() {
        if (proxyFactory == null) {
            initHessianProxy();
        }
        List<DevTServer> lists = dao.query(DevTServer.class, Cnd.where("wlsm", "=", "1"));
        if (lists.size() > 0) {
            for (DevTServer list : lists) {
                try {
                    SnmpWindow dsServiceWindows = new SnmpWindow(list.getFwip(), "public");


                    SystemInfo systemInfo = dsServiceWindows.getSysInfo();
                    JSONObject jsonObjectzx = convertServiceHeatbeat(systemInfo, list);
                    String key = MyPeropertiesUtil.getValue("bcht.hessian.key");
                    bchtTrffService.writeServiceHeartBeat(MyPeropertiesUtil.getValue("bcht.hessian.key"), jsonObjectzx.toString());

                } catch (Exception e) {
                    JSONObject jsonObjectlx = convertServiceHeatbeatForLX(list);
                    bchtTrffService.writeServiceHeartBeat(MyPeropertiesUtil.getValue("bcht.hessian.key"), jsonObjectlx.toString());
                }

            }
        }
    }

    /**
     * 组装服务心跳json
     *
     * @param systemInfo
     * @param devTServer
     * @return
     */
    public static JSONObject convertServiceHeatbeat(SystemInfo systemInfo, DevTServer devTServer) {
        int a = (int) (Math.random() * (9999 - 1000 + 1)) - 1000;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CpuInfo cpuInfo = systemInfo.getCpuInfo();
        MemoryInfo memoryInfo = systemInfo.getMemoryInfo();
        List<DiskInfo> diskInfoList = systemInfo.getDiskInfos();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("lsh", UUID.randomUUID().toString().replaceAll("-", ""));
        jsonObject.put("fwbh", devTServer.getGlbm() + "21" + a);
        jsonObject.put("fwip", devTServer.getFwip());
        jsonObject.put("fwzt", 0);
        jsonObject.put("gzms", "");
        jsonObject.put("cpusyl", cpuInfo.getSysRate());
        jsonObject.put("nczl", memoryInfo.getMemorySize());
        jsonObject.put("ncsyl", memoryInfo.getMemoryUsedSize());
        jsonObject.put("nckxl", memoryInfo.getMemoryFreeSize());
       Map<String,String> usedObject = new HashMap<String,String>();

        Map<String,String> freeObject = new HashMap<String,String>();
        double zkj = 0;
        for (int i = 0; i < diskInfoList.size() - 1; i++) {
            zkj = zkj + Double.parseDouble(diskInfoList.get(i).getDiskSize().replace(",",""));
            usedObject.put("第"+(i+1)+"块磁盘", diskInfoList.get(i).getDiskUsedSize());
            freeObject.put("第"+(i+1)+"块磁盘", diskInfoList.get(i).getDiskFreeSize());
        }
        jsonObject.put("cpzkj", zkj + "");
        JSONObject jsonused = JSONObject.fromObject(usedObject);
       String sy = jsonused.toString();
        JSONObject jsonfree = JSONObject.fromObject(freeObject);
        String ky = jsonfree.toString();
        jsonObject.put("cpsykj", sy+"。");
        jsonObject.put("cpkxkj", ky+"。");
        jsonObject.put("qdsj","");
        jsonObject.put("dqsj", sdf.format(new Date()));
        return jsonObject;
    }

    /**
     * 组装服务心跳json li离线
     *
     * @param devTServer
     * @return
     */
    public static JSONObject convertServiceHeatbeatForLX(DevTServer devTServer) {
        int a = (int) (Math.random() * (9999 - 1000 + 1)) - 1000;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // CpuInfo cpuInfo = systemInfo.getCpuInfo();
        //MemoryInfo memoryInfo = systemInfo.getMemoryInfo();
        //List<DiskInfo> diskInfoList = systemInfo.getDiskInfos();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("lsh", UUID.randomUUID().toString().replaceAll("-", ""));
        jsonObject.put("fwbh", devTServer.getGlbm() + "21" + a);
        jsonObject.put("fwip", devTServer.getFwip());
        jsonObject.put("fwzt", 3);
        jsonObject.put("gzms", "");
        jsonObject.put("cpusyl", 0);
        jsonObject.put("nczl", 0);
        jsonObject.put("ncsyl", 0);
        jsonObject.put("nckxl", 0);
        JSONObject usedObject = new JSONObject();
        JSONObject freeObject = new JSONObject();
        double zkj = 0;
        jsonObject.put("cpzkj", zkj + "");
        jsonObject.put("cpsykj", 0);
        jsonObject.put("cpkxkj", 0);
        jsonObject.put("qdsj", "");
        jsonObject.put("dqsj", sdf.format(new Date()));
        return jsonObject;
    }

    public static void initHessianProxy() {
        proxyFactory = new HessianProxyFactory();
        String url = Application.BCHTLOCALHESSIANURL;
        proxyFactory.setConnectTimeout(1000);
        proxyFactory.setReadTimeout(1000);
        try {
            bchtTrffService = (BchtTrffService) proxyFactory.create(BchtTrffService.class, url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
