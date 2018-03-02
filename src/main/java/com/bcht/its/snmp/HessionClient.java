package com.bcht.its.snmp;

import com.bcht.its.service.BchtTrffService;
import com.bcht.its.utils.MyPeropertiesUtil;
import com.caucho.hessian.client.HessianProxyFactory;
import net.sf.json.JSONObject;

import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * hession接口工具类
 */
public class HessionClient {
    private static String hessionUrl = MyPeropertiesUtil.getValue("hession.url");
    private static String key = MyPeropertiesUtil.getValue("hession.key");
    private static BchtTrffService writeService = null;
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static void init() throws MalformedURLException {
        HessianProxyFactory proxyFactory =  new HessianProxyFactory();
        writeService = (BchtTrffService)proxyFactory.create(BchtTrffService.class,hessionUrl);
    }
    /**
     *
     * @param systemInfo
     * @return
     */
    public static String uploadServiceHeartbeat(SystemInfo systemInfo, String serviceId,String serviceDsIp) throws MalformedURLException {
        if(writeService==null){
            init();
        }
        JSONObject jsonObject = convertServiceHeatbeat(systemInfo,serviceId,serviceDsIp);
        return writeService.writeServiceHeartBeat(key,jsonObject.toString());
    }

    /**
     * 组装服务心跳json
     * @param systemInfo
     * @param serviceId
     * @param serviceDsIp
     * @return
     */
    public static JSONObject convertServiceHeatbeat(SystemInfo systemInfo,String serviceId,String serviceDsIp){
        CpuInfo cpuInfo = systemInfo.getCpuInfo();
        MemoryInfo memoryInfo = systemInfo.getMemoryInfo();
        List<DiskInfo> diskInfoList = systemInfo.getDiskInfos();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("lsh", UUID.randomUUID().toString().replaceAll("-",""));
        jsonObject.put("fwbh",serviceId);
        jsonObject.put("fwip",serviceDsIp);
        jsonObject.put("fwzt",0);
        jsonObject.put("gzms","");
        jsonObject.put("cpusyl",cpuInfo.getSysRate());
        jsonObject.put("nczl",memoryInfo.getMemorySize());
        jsonObject.put("ncsyl",memoryInfo.getMemoryUsedSize());
        jsonObject.put("nckxl",memoryInfo.getMemoryFreeSize());
        JSONObject usedObject = new JSONObject();
        JSONObject freeObject = new JSONObject();
        double zkj = 0;
        for(int i=0;i<diskInfoList.size()-1;i++){
            zkj = zkj+Double.parseDouble(diskInfoList.get(i).getDiskSize());
            usedObject.put(diskInfoList.get(i).getDiskName(),diskInfoList.get(i).getDiskUsedSize());
            freeObject.put(diskInfoList.get(i).getDiskName(),diskInfoList.get(i).getDiskFreeSize());
        }
        jsonObject.put("cpzkj",zkj+"");
        jsonObject.put("cpsykj",usedObject.toString());
        jsonObject.put("cpkxkj",freeObject.toString());
        jsonObject.put("qdsj","");
        jsonObject.put("dqsj",sdf.format(new Date()));
        return  jsonObject;
    }





}
