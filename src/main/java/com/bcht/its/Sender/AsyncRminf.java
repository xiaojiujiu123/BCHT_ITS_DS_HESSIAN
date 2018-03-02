package com.bcht.its.Sender;

import com.alibaba.druid.util.StringUtils;
import com.bcht.its.commons.beans.DeviceTemp;
import com.bcht.its.commons.beans.DsTfcpassRminfTemp;
import com.bcht.its.commons.beans.TrafficVechilePassInfo;
import com.bcht.its.commons.beans.VehicleInfo;
import com.bcht.its.service.IRedisService;
import com.bcht.its.service.KeyValidationService;
import com.bcht.its.utils.PropertiesUtils;
import com.bcht.its.webserviceclient.Client;
import net.sf.json.JSONObject;
import org.nutz.dao.Dao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 异步将数据上传到继承指挥平台
 * Created by Administrator on 2017/6/8.
 */

@Component
public class AsyncRminf {
    @Autowired
    private Dao dao;
    @Resource
    private IRedisService iRedisService;
    private static Logger logger = LoggerFactory.getLogger(AsyncRminf.class);
   @Autowired
   private KeyValidationService keyValidationServiceImpl;

    //异步处理上传集成指挥平台
    @Async
    public void upLoadToRminf(TrafficVechilePassInfo passData) {
        if(passData==null){
            return;
        }

        Map<String,DeviceTemp> devMap = new HashMap<String,DeviceTemp>();
        String devStr = iRedisService.get("devMap");
        if(!StringUtils.isEmpty(devStr)){
            JSONObject jsonMap =JSONObject.fromObject(devStr);
            Iterator ite = jsonMap.keys();
            while(ite.hasNext()){
                String key = ite.next().toString();
               String valStr  = jsonMap.get(key).toString();
               JSONObject valObj = JSONObject.fromObject(valStr);
                DeviceTemp temp = (DeviceTemp)JSONObject.toBean(valObj,DeviceTemp.class);
                devMap.put(key,temp);
            }
            // devMap = (Map<String, DeviceTemp>) jsonMap;
        }else{
            Map<String ,DeviceTemp> map =keyValidationServiceImpl.findAllDevs();
            iRedisService.set("devMap",JSONObject.fromObject(map).toString());
            iRedisService.expire("devMap",5);
            devMap = map;
        }
        DeviceTemp deviceTemp  =  devMap.get(passData.getSbxh());

        if(deviceTemp==null){
            return ;
        }
        if(StringUtils.isEmpty(deviceTemp.getJcsbbh())){
            return ;
        }


   /*  String[] str =new String[6];
        str[0]="533603000010010001";
        str[1]="533603000010010002";
        str[2]="533603000010010003";
        str[3]="533603000010010004";
        str[4]="533603000010010005";
        str[5]="533603000010010006";
        boolean isok = false;
     for(String strs :str){
         if(strs.equals(passData.getSbxh())){
             isok= true;
             break;
         }else{
             continue;
         }
     }
     if(!isok){
         return;
     }*/
        String result = "";
        DsTfcpassRminfTemp rminfTemp = null;
        VehicleInfo vInfo = null;
        try {
            //将过车数据转换成集成指挥平台接口的实体类
              vInfo = passDataConvertVehicleInfo(passData,deviceTemp);//,deviceTemp
            //调用写入接口。 成功返回1 失败返回负值，连接失败返回"
            result = Client.writerxml(vInfo);
            if (result.equals("1")) {

                logger.info("过车数据上传集成指挥平台成功！号牌号码为:" + vInfo.getHphm());
            }else{
                rminfTemp = new DsTfcpassRminfTemp();
                rminfTemp.setId(getUUID());
                rminfTemp.setRksj(new Date());
                rminfTemp.setPassjson(net.sf.json.JSONObject.fromObject(vInfo).toString());
                dao.insert(rminfTemp);
                logger.error("上传集成指挥平台失败：错误代码:"+result);
            }
        } catch (Exception e) {
            rminfTemp = new DsTfcpassRminfTemp();
            rminfTemp.setId(getUUID());
            rminfTemp.setRksj(new Date());
            rminfTemp.setPassjson(net.sf.json.JSONObject.fromObject(vInfo).toString());
            dao.insert(rminfTemp);
            logger.error("上传集成指挥平台失败,请检查接口地址能否正确访问："+ PropertiesUtils.getValue("bcht_webservice_url"));
        }
    }
    public VehicleInfo passDataConvertVehicleInfo(TrafficVechilePassInfo passData,DeviceTemp deviceTemp){//,DeviceTemp deviceTemp
        VehicleInfo vInfo = new VehicleInfo();
        vInfo.setKkbh(deviceTemp.getJcsbbh());//533600100014
        vInfo.setFxlx(passData.getFx());
        vInfo.setCdh(passData.getCdbh()+"");
        vInfo.setHphm(passData.getHphm());
        vInfo.setHpzl(passData.getHpzl());//
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        vInfo.setGcsj(sdf.format(passData.getJgsj()));
        vInfo.setCllx("");
        vInfo.setClsd(passData.getSd()+"");
        vInfo.setClxs("0");
        vInfo.setCwkc(passData.getCwkc()+"");
        vInfo.setHpys("2");//passData.getHpys()
        String tplj = passData.getTpurl().substring(0,passData.getTpurl().lastIndexOf("/"));
        vInfo.setTplj(tplj);
        vInfo.setTp1(passData.getTpurl().substring(tplj.length(),passData.getTpurl().length()));
        vInfo.setTp2("");
        vInfo.setTp3("");
        vInfo.setWfdm(passData.getWfdm());
        vInfo.setFzhphm("");
        vInfo.setFzhpys("");
        vInfo.setFzhpzl("");
        vInfo.setClpp("");
        vInfo.setClwx("");
        vInfo.setCsys("");
        vInfo.setTztp("");
        return vInfo;
    }
    /**
     * 获取随机数（UUID）
     * @return
     */
    public String getUUID(){
        String str = UUID.randomUUID().toString();
        return str.substring(0,8)+ str.substring(9,13)+ str.substring(19,23)+ str.substring(24);
    }

}
