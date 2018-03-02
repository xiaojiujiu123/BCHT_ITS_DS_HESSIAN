package com.bcht.its.Sender;

import com.bcht.its.Application;
import com.bcht.its.commons.beans.DsTfcpassGPSJsonTemp;
import com.bcht.its.commons.beans.DsTfcpassJsonTemp;
import com.bcht.its.commons.beans.DsTfcpassWFJsonTemp;
import com.bcht.its.commons.beans.Thing;
import com.bcht.its.service.BchtTrffService;
import com.caucho.hessian.client.HessianProxyFactory;
import org.nutz.dao.Dao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.UUID;

/**
 * Created by JXX on 2017/6/5.
 *    异步处理汇聚方法
 */
@Component
public class AsyncMethod {
    private static Logger logger = LoggerFactory.getLogger(AsyncMethod.class);
    @Autowired
    private Dao dao;

    public static HessianProxyFactory proxyFactory = null;
    public static BchtTrffService bchtTrffService = null;
    @Async
    public void upload(String key,String passingVehicle){
        //获取上传时间
        sendPassData(key,passingVehicle);
    }
    @Async
    public void uploadWF(String key,String violationVehice){
       // scsj = new Date();
          sendPassWFData(key,violationVehice);
    }
    @Async
    public void uploadGPS(String key,String policeCarLocation){
      //  scsj = new Date();
      sendPolCarGPS(key,policeCarLocation);
    }

    /**
     * 向上级汇聚，如果上传失败，则存入本地，由准实时服务进行续传
     * @param key 密钥
     * @param passingVehicle  过车JSON
     */
    public  void sendPassData(String key,String passingVehicle){
        DsTfcpassJsonTemp ds = null;
        boolean isSuccess = false;
        if (bchtTrffService==null) {
            initHessianProxy();
        }
        try {

            bchtTrffService.writePassVehicle(key,passingVehicle);
            isSuccess = true;
        } catch (Exception e) {
            ds =  new DsTfcpassJsonTemp();
            ds.setId(getUUID());
            ds.setKey(key);
            ds.setRksj(new Date());
            ds.setPassjson(passingVehicle);
            dao.insert(ds);
        }finally {
            if(isSuccess){
                logger.info("上级汇聚成功！");
            }else{
                logger.info("上级汇聚失败，已存入本地内存");
            }
        }
    }

    /**
     * 向上级汇聚GPS，如果上传失败，则存入本地，由准实时服务进行续传
     * @param key 密钥
     * @param policeCarLocation  GPSJSON
     */
    public  void sendPolCarGPS(String key,String policeCarLocation){
        DsTfcpassGPSJsonTemp ds = null;
        boolean isSuccess = false;
        if (bchtTrffService==null) {
            initHessianProxy();
        }
        try {
            bchtTrffService.writePoliceCarLocation(key,policeCarLocation);
            isSuccess = true;
        } catch (Exception e) {
            ds =  new DsTfcpassGPSJsonTemp();
            ds.setId(getUUID());
            ds.setKey(key);
            ds.setRksj(new Date());
            ds.setPassjson(policeCarLocation);
            dao.insert(ds);
        }finally {
            if(isSuccess){
                logger.info("上级汇聚GPS信息成功！");
            }else{
                logger.info("上级汇聚GPS信息，已存入本地内存");
            }
        }

    }
    /**
     * 违法过车向上级汇聚，如果上传失败，则存入本地，由准实时服务进行续传
     * @param key 密钥
     * @param violationVehicle  过车JSON
     */
    public  void sendPassWFData(String key,String violationVehicle){
        DsTfcpassWFJsonTemp ds = null;
        boolean isSuccess = false;
        if (bchtTrffService==null) {
            initHessianProxy();
        }
        try {
            bchtTrffService.writeViolationVehicle(key,violationVehicle);
            isSuccess = true;
        } catch (Exception e) {
            ds =  new DsTfcpassWFJsonTemp();
            ds.setId(getUUID());
            ds.setKey(key);
            ds.setRksj(new Date());
            ds.setPassjson(violationVehicle);
            dao.insert(ds);
        }finally {
            if(isSuccess){
                logger.info("过车违法上级汇聚成功！");
            }else{
                logger.info("过车违法上级汇聚失败，已存入本地内存");
            }
        }
    }
    /**
     * 获取随机数（UUID）
     * @return
     */
    public String getUUID(){
        String str = UUID.randomUUID().toString();
        return str.substring(0,8)+ str.substring(9,13)+ str.substring(19,23)+ str.substring(24);
    }
    public static void initHessianProxy(){
        proxyFactory = new HessianProxyFactory();
        String url = Application.BCHTHESSIANURL;
        proxyFactory.setConnectTimeout(1000);
        proxyFactory.setReadTimeout(1000);
        try {
            bchtTrffService =  (BchtTrffService) proxyFactory.create( BchtTrffService.class,url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    public  void sendHeartBeat(String key,String heartBeat){
        // DsTfcpassJsonTemp ds = null;
        boolean isSuccess = false;
        if (bchtTrffService==null) {
            initHessianProxy();
        }
        try {
            bchtTrffService.writeDeviceHeartBeat(key,heartBeat);
            isSuccess = true;
        } catch (Exception e) {

        }finally {
            if(isSuccess){
                logger.info("上级汇聚设备状态成功！");
            }else{
                logger.info("上级汇聚设备状态失败");
            }
        }
    }

    public  void sendServiceHeartBeat(String key,String heartBeat){
        // DsTfcpassJsonTemp ds = null;
        boolean isSuccess = false;
        if (bchtTrffService==null) {
            initHessianProxy();
        }
        try {
            bchtTrffService.writeServiceHeartBeat(key,heartBeat);
            isSuccess = true;
        } catch (Exception e) {

        }finally {
            if(isSuccess){
                logger.info("上级汇聚服务器状态成功！");
            }else{
                logger.info("上级汇聚服务器状态失败");
            }
        }
    }

}
