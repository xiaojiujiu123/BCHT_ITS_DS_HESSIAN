package com.bcht.its.service.impl;
import com.bcht.its.Application;
import com.bcht.its.Sender.AsyncMethod;
import com.bcht.its.Sender.AsyncRminf;
import com.bcht.its.commons.beans.*;
import com.bcht.its.pojo.*;
import com.bcht.its.service.BchtTrffService;
import com.bcht.its.service.KeyValidationService;
import com.bcht.its.utils.PropertiesUtils;
import com.caucho.hessian.client.HessianProxyFactory;
import com.google.gson.Gson;
import net.sf.json.JSONObject;
import org.nutz.dao.Dao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2017/5/18.
 */

@Service("bchtTrffServiceImpl")
public class BchtTrffServiceImpl implements BchtTrffService{
    @Autowired
    private Dao dao;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private KeyValidationService keyValidationServiceImpl;
    /**
     * 异步处理上传上级
     */
    @Resource
    private  AsyncMethod asyncMethod;
    @Resource
    private AsyncRminf asyncRminf;
    private static boolean off = false;
    private static Logger logger = LoggerFactory.getLogger(BchtTrffServiceImpl.class);
    private   Date scsj =null;
    private Date jssj = null;
    private static HessianProxyFactory proxyFactory = new HessianProxyFactory();
    private static BchtTrffService bchtTrffService = null;
    private static String gawUrl = PropertiesUtils.getValue("gawUrl");
    private static String zwUrl = PropertiesUtils.getValue("zwUrl");


    /**
     * 百诚慧通过车写入接口
     * @param key 序列号
     * @param passingVehicle 过车对象JSO
     * @return
     */
    @Override
    public String writePassVehicle(String key, String passingVehicle) {
        jssj = new Date();
        String result = "";
        TrafficVechilePassInfo trffPassInfo = null;
        DsTfcpassMqTemp mq = null;

        PassingVehicle ps  = null;
        try {
            Gson gson = new Gson();
            ps = gson.fromJson(passingVehicle,PassingVehicle.class);
            if ((ps.getTpurl().length() > 0) && (ps.getTpurl().contains(zwUrl))) {
                ps.setTpurl(ps.getTpurl().replaceAll(zwUrl, gawUrl));
            }
        }catch(Exception e){
            return "请检查JSON格式是否正确";
        }
        result = validInfo(ps);
        if(!StringUtils.isEmpty(result)){
            logger.error(result);
            return result;
        }
        /**
         * 判断是否向上级汇聚
         */
        if(Application.isUpload) {
            asyncMethod.upload(key, passingVehicle);
            scsj  = new Date();
        }
        /**
         * 判断是否上传集成指挥平台
         */
        if(Application.isUploadRminf.equals("1")){
            trffPassInfo = passVehConvertToTrffVechPass(ps);
            asyncRminf.upLoadToRminf(trffPassInfo);
        }
        /**
         * 判断是否上传到MQ
         */
        if(Application.isUpLoadMQ) {
            try {
                trffPassInfo = passVehConvertToTrffVechPass(ps);
                trffPassInfo.setSjly(Application.INTERFACEMAP.get(key).getId() + "");
                Gson gson = new Gson();
                String passInfo = gson.toJson(trffPassInfo);
                rabbitTemplate.convertAndSend("ITS_EXCHANGE_ITS_TFCPASS", "BCHT.TFCPASS.HESSIAN.PASS", passInfo);
                logger.info("上传过车数据到MQ成功！号牌号码为：" + ps.getHphm());
            } catch (Exception e) {
                mq = new DsTfcpassMqTemp();
                mq.setId(getUUID());
                mq.setRksj(new Date());
                mq.setPassjsonmq(JSONObject.fromObject(trffPassInfo).toString());
                dao.insert(mq);
                logger.info("上传过车数据到MQ失败，已存入本地");
            }
        }

        return result;
    }
    /**
     * 百诚慧通过车违法写入接口
     * @param key 序列号
     * @param violationVehicle 过车违法对象JSON
     * @return
     */
    @Override
    public String writeViolationVehicle(String key, String violationVehicle) {
        jssj = new Date();
        String result = "";
        ViolationVehice vv  = null;
        TrafficVechilePassInfo passInfo =null;
        DsTfcpassWFMqTemp mq = null;
        try{
            Gson gson = new Gson();
            vv =gson.fromJson(violationVehicle,ViolationVehice.class);
            if ((vv.getTpurl().length() > 0) && (vv.getTpurl().contains(zwUrl))) {
                vv.setTpurl(vv.getTpurl().replaceAll(zwUrl, gawUrl));
            }
            if ((vv.getTpurl1().length() > 0) && (vv.getTpurl1().contains(zwUrl))) {
                vv.setTpurl1(vv.getTpurl1().replaceAll(zwUrl, gawUrl));
            }
            if ((vv.getTpurl2().length() > 0) && (vv.getTpurl2().contains(zwUrl))) {
                vv.setTpurl2(vv.getTpurl2().replaceAll(zwUrl, gawUrl));
            }
        }catch (Exception e){
            return "请检查JSON格式是否正确";
        }
        result =  validInfoWF(vv);
        if(!StringUtils.isEmpty(result)){
            return result;
        }
        /**
         * 判断是否向上级汇聚
         */
        if(Application.isUpload) {
            scsj  = new Date();
            asyncMethod.uploadWF(key, violationVehicle);
        }
        /**
         * 判断是否上传到MQ
         */
        if(Application.isUpLoadMQ) {
            try {
                passInfo = violationVehConvertToTrffVechPass(vv);
                passInfo.setSjly(Application.INTERFACEMAP.get(key).getId() + "");
                Gson gson = new Gson();
                String jsonPassInfo = gson.toJson(passInfo);
                rabbitTemplate.convertAndSend("ITS_EXCHANGE_ITS_TFCPASS", "BCHT.TFCPASS.HESSIAN.VIOLATION", jsonPassInfo);
                logger.info("上传过车违法数据到MQ成功！号牌号码为：" + passInfo.getHphm());
            } catch (Exception e) {
                mq = new DsTfcpassWFMqTemp();
                mq.setId(getUUID());
                mq.setRksj(new Date());
                mq.setPassjsonmq(JSONObject.fromObject(passInfo).toString());
                dao.insert(mq);
                logger.info("上传过车违法数据到MQ失败，已存入本地");
            }
        }

        return result;
    }

    /**
     * 写入警车GPS信息
     * @param key
     * @param policeCarLocation
     * @return
     */
    @Override
    public String writePoliceCarLocation(String key, String policeCarLocation) {
        jssj = new Date();
        VechilePassInfoTimes vechilePassInfoTimes = new VechilePassInfoTimes();

        vechilePassInfoTimes.setJssj(new Date());

        String result = "";
        PoliceCarLocation pcg = null;
        try {
            Gson gson = new Gson();
            pcg = gson.fromJson(policeCarLocation,PoliceCarLocation.class);
          /*  JSONObject json = JSONObject.fromObject(policeCarLocation);
            Object obj = JSONObject.toBean(json, PoliceCarLocation.class);
            pcg = (PoliceCarLocation) obj;*/
        } catch (Exception e) {
            return "请检查JSON格式是否正确";
        }
        result = validGPSInfo(pcg);
        if (result != "") {
            return result;
        }
        if (Application.BCHTHESSIANGPS.equals("1")) {

            vechilePassInfoTimes.setScsj(new Date());

            asyncMethod.uploadGPS(key, policeCarLocation);
        }


        pcg.setSjly(Application.INTERFACEMAP.get(key).getId() + "");
        pcg.setVechilePassInfoTimes(vechilePassInfoTimes);
        Gson gson =new Gson();
        String jsonPcgInfo =gson.toJson(pcg);
        rabbitTemplate.convertAndSend("ITS_EXCHANGE_ITS_TFCPASS", "BCHT.TFCPASS.HESSIAN.GPS", jsonPcgInfo);
        vechilePassInfoTimes.setFfsj(new Date());
        logger.info("上传GPS数据成功！号牌号码为：" + pcg.getHphm());
        return "";
    }

    /**
     * 百诚慧通
     * @param key 序列号
     * @param heartBeat 设备心跳对象JSON
     * @return
     */
    @Override
    public String writeDeviceHeartBeat(String key, String heartBeat) {

        if(!Application.INTERFACECACHE.contains(key)){
            return "接口秘钥错误，数据写入失败！";
        }
        String result = "";
        HeartBeat hb =null;
        try {
            Gson gson = new Gson();
            hb= gson.fromJson(heartBeat,HeartBeat.class);
          /*  JSONObject json = JSONObject.fromObject(heartBeat);
            Object obj = JSONObject.toBean(json, HeartBeat.class);
            hb = (HeartBeat) obj;*/
        }catch (Exception e){
            return "请检查JSON格式是否正确";
        }
        asyncMethod.sendHeartBeat(key,heartBeat);
        try{
            rabbitTemplate.convertAndSend("ITS_EXCHANGE_ITS_TFCPASS","BCHT.TFCPASS.HESSIAN.HEARTBEAT",hb);
        }catch (Exception e){

        }

        return result;
    }

    /**
     * 写入服务器心跳
     * @param key
     * @param serviceHeartBeat
     * @return
     */
    @Override
    public String writeServiceHeartBeat(String key,String serviceHeartBeat){

        ServiceHeartBeat shb = null;
        if(!Application.INTERFACECACHE.contains(key)){
            return "接口秘钥错误，数据写入失败！";
        }
        try{ JSONObject json = JSONObject.fromObject(serviceHeartBeat);
            Object obj = JSONObject.toBean(json, ServiceHeartBeat.class);
            shb = (ServiceHeartBeat) obj;
        }catch (Exception e){
            return "请检查JSON格式是否正确";
        }
        try{
            rabbitTemplate.convertAndSend("ITS_EXCHANGE_ITS_TFCPASS","BCHT.TFCPASS.HESSIAN.SERVICEHEARTBEAT",shb);
        }catch (Exception e){

        }
        return "";
    }

    /**
     * 上传交通气象信息接口
     * @param key
     * @param trafficWeather
     * @return
     */
    @Override
    public String writeTrafficWeather(String key, String trafficWeather) {

        if(!Application.INTERFACECACHE.contains(key)){
            return "接口秘钥错误，数据写入失败！";
        }
        return null;
    }
    /**
     * 上传交通事件信息接口
     * @param key
     * @param trafficIncident
     * @return
     */
    @Override
    public String writeTrafficIncident(String key, String trafficIncident) {


        if(!Application.INTERFACECACHE.contains(key)){
            return "接口秘钥错误，数据写入失败！";
        }

        return null;
    }
    /**
     * 上传交通诱导发布信息接口
     * @param key
     * @param trafficGuidance
     * @return
     */
    @Override
    public String writeTrafficGuidance(String key, String trafficGuidance) {

        if(!Application.INTERFACECACHE.contains(key)){
            return "接口秘钥错误，数据写入失败！";
        }
        return null;
    }
    /**
     * 上传单警定位信息接口
     *
     * @param key
     * @param singlePoliceLocation
     * @return
     */
    @Override
    public String writeSinglePoliceLocation(String key, String singlePoliceLocation) {

        if(!Application.INTERFACECACHE.contains(key)){
            return "接口秘钥错误，数据写入失败！";
        }
        return null;
    }
    /**
     * 上传停车场车辆信息接口
     * @param key
     * @param parkVehicle
     * @return
     */
    @Override
    public String writeParkVehicle(String key, String parkVehicle) {

        if(!Application.INTERFACECACHE.contains(key)){
            return "接口秘钥错误，数据写入失败！";
        }
        return null;
    }
    /**
     * 上传车辆检查台账信息接口
     */
    @Override
    public String writeVehicleInspectionLedger(String key, String vehicleInspectionLedger) {
        return null;
    }
    /**
     * 上传预警返回信息接口
     */
    @Override
    public String writeEarlyWarningFeedback(String key, String earlyWarningFeedback) {
        return null;
    }
    /**
     * 上传拦截处置图片信息接口
     */
    @Override
    public String writeInterceptDisposalPictures(String key, String interceptDisposalPictures) {
        return null;
    }

    public static String validGPSInfo(PoliceCarLocation policeCarLocation){
        String result = "";
        if(StringUtils.isEmpty(policeCarLocation.getLsh())){
            return "GPS流水号不能为空！";
        }
        if(StringUtils.isEmpty(policeCarLocation.getSbbh())){
            return "设备编号不能为空！";
        }
        if(StringUtils.isEmpty(policeCarLocation.getHpzl())){
            return "号牌种类不能为空！";
        }
        if(StringUtils.isEmpty(policeCarLocation.getHphm())){
            return "号牌号码不能为空！";
        }
        if(StringUtils.isEmpty(policeCarLocation.getSbsj())){
            return "上报时间不能为空！";
        }
        if(StringUtils.isEmpty(policeCarLocation.getJd())){
            return "精度不能为空！";
        }
        if(StringUtils.isEmpty(policeCarLocation.getWd())){
            return "纬度不能为空！";
        }
        return result;
    }

    /**
     * 违法数据验证
     * @param vv
     * @return
     */
    public static String validInfoWF(ViolationVehice vv){
        String result = "";
        if(StringUtils.isEmpty(vv.getLsh())){
            return "过车流水号不能为空！";
        }
        if(StringUtils.isEmpty(vv.getSbbh())){
            return "设备编号不能为空！";
        }
        if(StringUtils.isEmpty(vv.getCdbh())){
            return "车道编号不能为空！";
        }
        if(StringUtils.isEmpty(vv.getFxlx())){
            return "方向类型不能为空！";
        }
        if(StringUtils.isEmpty(vv.getHphm())){
            return "号牌号码不能为空！";
        }
        if(StringUtils.isEmpty(vv.getHpzl())){
            return "号牌种类不能为空！";
        }
        if(StringUtils.isEmpty(vv.getGcsj())){
            return "过车时间不能为空！";
        }
        if(StringUtils.isEmpty(vv.getTpurl())) {
            return "至少要有一张图片信息！";
        }
        return result;
    }

    /**
     * 过车数据验证
     * @param vv
     * @return
     */
    public static String validInfo(PassingVehicle vv){
        String result = "";
        if(StringUtils.isEmpty(vv.getLsh())){
            return "过车流水号不能为空！";
        }
        if(StringUtils.isEmpty(vv.getSbbh())){
            return "设备编号不能为空！";
        }
        if(StringUtils.isEmpty(vv.getCdbh())){
            return "车道编号不能为空！";
        }
        if(StringUtils.isEmpty(vv.getFxlx())){
            return "方向类型不能为空！";
        }
        if(StringUtils.isEmpty(vv.getHphm())){
            return "号牌号码不能为空！";
        }
        if(StringUtils.isEmpty(vv.getHpzl())){
            return "号牌种类不能为空！";
        }
        if(StringUtils.isEmpty(vv.getGcsj())){
            return "过车时间不能为空！";
        }
        if(StringUtils.isEmpty(vv.getTpurl())) {
            return "至少要有一张图片信息！";
        }
        return result;
    }


    /**
     * 获取随机数（UUID）
     * @return
     */
    public String getUUID(){
        String str = UUID.randomUUID().toString();
        return str.substring(0,8)+ str.substring(9,13)+ str.substring(19,23)+ str.substring(24);
    }

    /**
     * 过车违法对象转换
     * @param violationVehice
     * @return
     */
    public TrafficVechilePassInfo  violationVehConvertToTrffVechPass(ViolationVehice violationVehice){

        TrafficVechilePassInfo trffPassInfo = new TrafficVechilePassInfo();
        trffPassInfo.setGcbh(getUUID());
        trffPassInfo.setLsh(violationVehice.getLsh());
        trffPassInfo.setHphm(violationVehice.getHphm());
        trffPassInfo.setHpzl(violationVehice.getHpzl());
        trffPassInfo.setHpys(violationVehice.getHpys());
        trffPassInfo.setCllx(violationVehice.getCllx());
        trffPassInfo.setClys(violationVehice.getCsys());
        if  (StringUtils.isEmpty(violationVehice.getCwkc())){
            trffPassInfo.setCwkc(0);
        }else{
            trffPassInfo.setCwkc(Float.parseFloat(violationVehice.getCwkc()));
        }
        trffPassInfo.setFx(violationVehice.getFxlx());
        VechilePassInfoTimes vech = new VechilePassInfoTimes();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {

            trffPassInfo.setJgsj(sdf.parse(violationVehice.getGcsj()));
            if(!com.alibaba.druid.util.StringUtils.isEmpty(violationVehice.getJrsj())){
                trffPassInfo.setJrsj(sdf.parse(violationVehice.getJrsj()));

            }else{
                trffPassInfo.setJrsj(null);
            }
            trffPassInfo.setFfsj(new Date());
            trffPassInfo.setHjsj(jssj);
            trffPassInfo.setScsj(scsj);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //  trffPassInfo.setVechilePassInfoTimes(vech);　
        trffPassInfo.setSbxh(violationVehice.getSbbh());
        trffPassInfo.setJgsjStr(violationVehice.getGcsj());
        if(StringUtils.isEmpty(violationVehice.getClsd())){
            trffPassInfo.setSd(0);
        }else{
            trffPassInfo.setSd(Float.parseFloat(violationVehice.getClsd()));
        }
        trffPassInfo.setTpurl(violationVehice.getTpurl());
        trffPassInfo.setTpurl1(violationVehice.getTpurl1());
        trffPassInfo.setTpurl2(violationVehice.getTpurl2());
        trffPassInfo.setWfdm(violationVehice.getWfdm());
        if(StringUtils.isEmpty(violationVehice.getCdbh())){
            trffPassInfo.setCdbh(1);
        }else{
            trffPassInfo.setCdbh(Integer.parseInt(violationVehice.getCdbh()));
        }
        return trffPassInfo;
    }

    /**
     * 过车对象转换
     * @param pascsingVehicle
     * @return
     */
    public TrafficVechilePassInfo passVehConvertToTrffVechPass(PassingVehicle pascsingVehicle){

        TrafficVechilePassInfo trffPassInfo = new TrafficVechilePassInfo();
        trffPassInfo.setGcbh(getUUID());
        trffPassInfo.setLsh(pascsingVehicle.getLsh());
        trffPassInfo.setHphm( pascsingVehicle.getHphm());
        trffPassInfo.setHpzl( pascsingVehicle.getHpzl());
        trffPassInfo.setHpys( pascsingVehicle.getHpys());
        trffPassInfo.setCllx(pascsingVehicle.getCllx());
        trffPassInfo.setClys(pascsingVehicle.getCsys());
        if  (StringUtils.isEmpty(pascsingVehicle.getCwkc())){
            trffPassInfo.setCwkc(0);
        }else{
            trffPassInfo.setCwkc(Float.parseFloat(pascsingVehicle.getCwkc()));
        }
        trffPassInfo.setFx(pascsingVehicle.getFxlx());
        VechilePassInfoTimes vech = new VechilePassInfoTimes();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            trffPassInfo.setJrsj(sdf.parse(pascsingVehicle.getJrsj()));
            trffPassInfo.setJgsj(sdf.parse(pascsingVehicle.getGcsj()));
            trffPassInfo.setFfsj(new Date());
            trffPassInfo.setHjsj(jssj);
            trffPassInfo.setScsj(scsj);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        //  trffPassInfo.setVechilePassInfoTimes(vech);
        trffPassInfo.setSbxh(pascsingVehicle.getSbbh());
        trffPassInfo.setJgsjStr(pascsingVehicle.getGcsj());
        if(StringUtils.isEmpty(pascsingVehicle.getClsd())){
            trffPassInfo.setSd(0);
        }else{
            trffPassInfo.setSd(Float.parseFloat(pascsingVehicle.getClsd()));
        }
        trffPassInfo.setTpurl(pascsingVehicle.getTpurl());
        trffPassInfo.setTpurl1(pascsingVehicle.getTpurl1());
        trffPassInfo.setTpurl2(pascsingVehicle.getTpurl2());
        trffPassInfo.setWfdm(pascsingVehicle.getWfdm());
        if(StringUtils.isEmpty(pascsingVehicle.getCdbh())){
            trffPassInfo.setCdbh(1);
        }else{
            trffPassInfo.setCdbh(Integer.parseInt(pascsingVehicle.getCdbh()));
        }
        return trffPassInfo;
    }





}
