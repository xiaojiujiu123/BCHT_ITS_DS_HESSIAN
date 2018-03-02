package com.bcht.its;

import com.bcht.its.pojo.Dstjksj;
import com.bcht.its.service.BchtTrffService;
import com.bcht.its.service.KeyValidationService;
import com.bcht.its.service.impl.KeyValidationServiceImpl;
import com.bcht.its.utils.MyPeropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 主应用启动入口
 */
@EnableScheduling //调度任务
@SpringBootApplication
@EnableAsync        //异步方法
public class Application   implements SchedulingConfigurer {

    private static Logger logger = LoggerFactory.getLogger(Application.class);
    /**
     * 缓存密钥
     */
    public  static List<String>  INTERFACECACHE = null;
    /**
     * 缓存接口对象
     */
    public static Map<String,Dstjksj> INTERFACEMAP =null;
    /**
     * 上级汇聚HESSIAN地址
     */
    public static String BCHTHESSIANURL = MyPeropertiesUtil.getValue("bcht.hessian.url");
    /**
     * 本级HESSIAN地址
     *
     */
    public static String BCHTLOCALHESSIANURL =MyPeropertiesUtil.getValue("bcht_hessian.localurl");
    /**
     * 是否向上级汇聚  true汇聚   false不汇聚
     *
     */
    public static boolean isUpload =Boolean.parseBoolean(MyPeropertiesUtil.getValue("bcht.hessian.isUpLoad"));
    /**
     * 是否上传到MQ  true上传 false 不上传
     */
    public static boolean isUpLoadMQ = Boolean.parseBoolean(MyPeropertiesUtil.getValue("bcht.hessian.isUpLoadMQ"));
    /**
     * 汇聚级别
     */
    public static String BCHTHESSIANHJJB  = MyPeropertiesUtil.getValue("bcht.hessian.hjjb");

    /**
     * 警车GPS信息是否向上级汇聚
     * @param args
     */
    public static String BCHTHESSIANGPS = MyPeropertiesUtil.getValue("bcht.hessian.gps");
    /**
     * 是否上传集成指挥平台
     */
    public static String isUploadRminf = MyPeropertiesUtil.getValue("bcht.hessian.isUploadRminf");
    /**
     * 是否上传GPS信息道集成指挥平台
     */
    public static String BCHTHESSIANUPLOADGPS = MyPeropertiesUtil.getValue("bcht.hessian.isUploadGPS");



    public static void main(String[] args){
      start(args);
      // new Application().init();
    }


    @Autowired
    private BchtTrffService bchtTrffServiceImpl;

    @Bean(name = "/BchtTrffService")
    public HessianServiceExporter exporter(){
        HessianServiceExporter ex = new HessianServiceExporter();
        ex.setService(bchtTrffServiceImpl);
        ex.setServiceInterface(BchtTrffService.class);
        return ex;
    }

    /**
     * 应用程序启动
     * @return
     */
    public static int start(String[] args){
    //    ConfigurableApplicationContext context = new SpringApplicationBuilder(Application.class).web(true).run(args);
        SpringApplication.run(Application.class);
        return 1;
    }

    /**
     * 应用程序退出
     * @return
     */
    public static int stop(){
        return -1;
    }


    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(taskExecutor());
    }
    @Bean(destroyMethod="shutdown")
    public Executor taskExecutor() {
        return Executors.newScheduledThreadPool(20);
    }


}
