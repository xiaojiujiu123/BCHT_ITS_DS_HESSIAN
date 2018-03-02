package com.bcht.its.snmp;
import com.bcht.its.physical.SnmpWindow;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 相机接入服务器状态job
 */

public class ServiceStateJob {
    private Logger logger = Logger.getLogger(ServiceStateJob.class);
    @Value("${service.ds.job.ip}")
    private String serviceDsIp;//接入服务ip
    @Value("${service.pic.job.ip}")
    private String servciePicIp;//图片存储ip
    @Value("${community}")
    private String community = "public";
    @Value("${ds_service_id}")
    private String dsServiceId;
    @Value("${pic_service_id}")
    private String picServiceId;
    private static SnmpWindow dsServiceWindows =null;
    private static SystemInfo systemInfo =null;
    private static SnmpWindow picServiceWindows =null;
    private static SystemInfo picSystemInfo =null;


    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Scheduled(cron = "${service.job.times}")
    public void start(){
        try {
            //获取接入服务器snmp采集
            if(dsServiceWindows==null){
                dsServiceWindows = new SnmpWindow(serviceDsIp,community);
            }
            if(systemInfo==null){

                systemInfo = dsServiceWindows.getSysInfo();
            }
            String resultMsg = HessionClient.uploadServiceHeartbeat(systemInfo,dsServiceId,serviceDsIp);
            if(StringUtils.isNotBlank(resultMsg)){
                logger.info("服务编号为"+dsServiceId+"的服务上传服务心跳失败");
            }
        }catch (Exception e) {
            logger.info("服务编号为"+dsServiceId+"的设备上传设备心跳失败");
        }


    }
}
