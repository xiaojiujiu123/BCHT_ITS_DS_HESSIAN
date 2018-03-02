package com.bcht.its.Scheduled;

import com.bcht.its.Application;
import com.bcht.its.Sender.AsyncMethod;
import com.bcht.its.commons.beans.*;
import com.bcht.its.pojo.Dstjksj;
import com.bcht.its.service.BchtTrffService;
import com.bcht.its.service.KeyValidationService;
import com.bcht.its.utils.JsonDateValueProcessor;
import com.bcht.its.utils.PropertiesUtils;
import com.bcht.its.webserviceclient.Client;
import com.caucho.hessian.client.HessianProxyFactory;
import com.google.gson.Gson;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.json.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by JXX on 2017/5/31.
 */
@Component
public class ScheduledManager {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private Dao dao;
    private static final Logger logger = LoggerFactory.getLogger(ScheduledManager.class);

    /**
     * 过车数据上级汇聚准实时服务
     */
   // @Scheduled(cron = "*/5 * * * * ?")
    public void uploadPassInfo() {
        if (Application.isUpload) {
            List<DsTfcpassJsonTemp> list = dao.query(DsTfcpassJsonTemp.class, Cnd.where("1", "=", "1").orderBy("rksj", "desc"), dao.createPager(1, 500));
            if (list.size() > 0) {
                for (DsTfcpassJsonTemp info : list) {
                    if (AsyncMethod.bchtTrffService == null) {
                        AsyncMethod.initHessianProxy();
                    }
                    try {

                        AsyncMethod.bchtTrffService.writePassVehicle(info.getKey(),info.getPassjson());
                        int res =dao.delete(DsTfcpassJsonTemp.class, info.getId());
                        if(res>0){
                            logger.info("续传过车数据成功！");
                        }
                    } catch (Exception e) {
                        logger.info("续传过车数据失败，请检查上级接口是否存在或者访问：" + Application.BCHTHESSIANURL);
                        return;
                    }
                }
            }
        }
    }
    /**
     * 过车数据上传MQ准实时服务
     */
    //@Scheduled(cron = "*/6 * * * * ?")
    public void uploadPassMQ() {
        if (Application.isUpLoadMQ) {
            List<DsTfcpassMqTemp> list = dao.query(DsTfcpassMqTemp.class, Cnd.where("1", "=", "1").orderBy("rksj", "desc"), dao.createPager(1, 500));
            if (list.size() > 0) {
                for (DsTfcpassMqTemp info : list) {
                    TrafficVechilePassInfo ds = null;
                    try {
                        Gson gson = new Gson();
                        ds = gson.fromJson(info.getPassjsonmq(),TrafficVechilePassInfo.class);
                        rabbitTemplate.convertAndSend("ITS_EXCHANGE_ITS_TFCPASS", "BCHT.TFCPASS.HESSIAN.PASS", gson.toJson(ds));
                       int res = dao.delete(DsTfcpassMqTemp.class, info.getId());
                        if(res>0){
                            logger.info("续传过车数据到MQ成功！号牌号码为：" + ds.getHphm());
                        }
                    } catch (Exception e) {
                        logger.info("续传过车数据到MQ失败！请检查mq是否正常。");
                        return;
                    }
                }
            }
        }
    }


    /**
     * 违法数据上级汇聚准实时服务
     */
  //  @Scheduled(cron = "*/7 * * * * ?")
    public void uploadwPasswf() {
        if (Application.isUpload) {
            List<DsTfcpassWFJsonTemp> list = dao.query(DsTfcpassWFJsonTemp.class, Cnd.where("1", "=", "1").orderBy("rksj", "desc"), dao.createPager(1, 500));
            if (list.size() > 0) {
                for (DsTfcpassWFJsonTemp info : list) {
                    if (AsyncMethod.bchtTrffService == null) {
                        AsyncMethod.initHessianProxy();
                    }
                    try {
                        AsyncMethod.bchtTrffService.writeViolationVehicle(info.getKey(), info.getPassjson());
                        dao.delete(DsTfcpassWFJsonTemp.class, info.getId());
                    } catch (Exception e) {
                        logger.info("续传过车违法数据失败，请检查上级接口是否存在或者访问：" + Application.BCHTHESSIANURL);
                        return;
                    }
                }
            }
        }
    }


  /**
     * 过车违法数据上传MQ准实时服务
     */
  //  @Scheduled(cron = "*/8 * * * * ?")
    public void uploadPasswfMQ() {
        if (Application.isUpLoadMQ) {
            List<DsTfcpassWFMqTemp> list = dao.query(DsTfcpassWFMqTemp.class, Cnd.where("1", "=", "1").orderBy("rksj", "desc"), dao.createPager(1, 500));
            if (list.size() > 0) {
                for (DsTfcpassWFMqTemp info : list) {
                    TrafficVechilePassInfo ds = null;
                    try {
                        Gson gson = new Gson();
                       ds = gson.fromJson(info.getPassjsonmq(),TrafficVechilePassInfo.class);
                        rabbitTemplate.convertAndSend("ITS_EXCHANGE_ITS_TFCPASS", "BCHT.TFCPASS.HESSIAN.PASS", gson.toJson(ds));
                       int res =  dao.delete(DsTfcpassWFMqTemp.class, info.getId());
                        if(res>0){
                            logger.info("续传过车数据到MQ成功！号牌号码为：" + ds.getHphm());
                        }
                    } catch (Exception e) {
                        logger.info("续传过车数据到MQ失败！请检查mq是否正常。");
                        return;
                    }
                }
            }
        }
    }

    /**
     * GPS数据上级汇聚准实时服务
     */
   // @Scheduled(cron = "*/9 * * * * ?")
    public void uploadwPassGPS() {
        if (Application.isUpload) {
            List<DsTfcpassGPSJsonTemp> list = dao.query(DsTfcpassGPSJsonTemp.class, Cnd.where("1", "=", "1").orderBy("rksj", "desc"), dao.createPager(1, 500));
            if (list.size() > 0) {
                for (DsTfcpassGPSJsonTemp info : list) {
                    if (AsyncMethod.bchtTrffService == null) {
                        AsyncMethod.initHessianProxy();
                    }
                    try {
                        AsyncMethod.bchtTrffService.writePoliceCarLocation(info.getKey(), info.getPassjson());
                        dao.delete(DsTfcpassJsonTemp.class, info.getId());
                    } catch (Exception e) {
                        logger.info("续传GPS数据失败，请检查上级接口是否存在或者访问：" + Application.BCHTHESSIANURL);
                        return;
                    }
                }
            }
        }
    }
    /**
     * GPS数据上传MQ准实时服务
     */
   // @Scheduled(cron = "*/10 * * * * ?")
    public void uploadPassGPSMQ() {
        if (Application.isUpLoadMQ) {
            List<DsTfcpassGPSMqTemp> list = dao.query(DsTfcpassGPSMqTemp.class, Cnd.where("1", "=", "1").orderBy("rksj", "desc"), dao.createPager(1, 500));
            if (list.size() > 0) {
                for (DsTfcpassGPSMqTemp info : list) {
                    PoliceCarLocation gps = null;
                    try {
                        Gson gson = new Gson();
                        gps = gson.fromJson(info.getPassjsonmq(),PoliceCarLocation.class);
                        rabbitTemplate.convertAndSend("ITS_EXCHANGE_ITS_TFCPASS", "BCHT.TFCPASS.HESSIAN.PASS", gson.toJson(gps));
                        logger.info("续传过车数据到MQ成功！号牌号码为：" + gps.getHphm());
                        dao.delete(DsTfcpassMqTemp.class, info.getId());
                    } catch (Exception e) {
                        logger.info("续传过车数据到MQ失败！请检查mq是否正常。");
                        return;
                    }
                }
            }
        }
    }

    /**
     * 集成指挥平台准实时服务
     */
   // @Scheduled(cron = "*/5 * * * * ?")
    public void uploadRminfPass() {
        if (Application.isUpLoadMQ) {
            List<DsTfcpassRminfTemp> list = dao.query(DsTfcpassRminfTemp.class, Cnd.where("1", "=", "1").orderBy("rksj", "desc"), dao.createPager(1, 500));
            if (list.size() > 0) {
                for (DsTfcpassRminfTemp info : list) {
                    VehicleInfo vInfo = null;
                    try {
                       JSONObject object =JSONObject.fromObject(info.getPassjson());
                        Object obj = JSONObject.toBean(object, VehicleInfo.class);
                        vInfo =(VehicleInfo) obj;
                      String   result = Client.writerxml(vInfo);
                        logger.info("续传集成指挥平台成功！好牌号码："+vInfo.getHphm()+",过车时间："+vInfo.getGcsj());
                        dao.delete(DsTfcpassRminfTemp.class, info.getId());
                    } catch (Exception e) {
                        logger.info("续传过车数据到集成指挥平台失败！请检查接口地址是否可以访问！"+ PropertiesUtils.getValue("bcht_webservice_url"));
                        return;
                    }
                }
            }
        }
    }

}
