package com.bcht.its.aop;

import com.bcht.its.Application;
import com.bcht.its.pojo.Dstjksj;
import com.bcht.its.service.KeyValidationService;
import com.bcht.its.service.impl.IRedisServiceImpl;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/6/9.
 */
@Aspect
@Configuration
public class validAOP {
    private static Logger logger  = LoggerFactory.getLogger(validAOP.class);
    /**
     * 定义一个切入点
     */
    @Pointcut("execution(* com.bcht.its.service.impl.BchtTrffServiceImpl..*(..))")
    public void serviceMethodPointCut(){}

    @Around("serviceMethodPointCut()")
    public Object valid(ProceedingJoinPoint proceedingJoinPoint){
        if(Application.INTERFACECACHE==null){
            return "初始化中，请稍后。。。";
        }
      Object[] objes = proceedingJoinPoint.getArgs();
        String key  = objes[0].toString();
        String passingVehicle = objes[1].toString();
        if(StringUtils.isEmpty(passingVehicle)){
            return "禁止传入空字符!";
        }
        if(!Application.INTERFACECACHE.contains(key)){
            return "接口秘钥错误，数据写入失败！";
        }
        /**
         * 判断接口是否过期。
         */
        String timeResult =  timeCompare(Application.INTERFACEMAP.get(key).getYxrq(),new Date());
        if(!timeResult.equals("")){
           logger.info(timeResult);
            return timeResult;
        }
        Object object = null;
        try {
              object =proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return object;
    }

    /**
     * 时间比较 用于判断接口是否过期
     * @param gqsj  接口过期时间
     * @param now   当前时间
     * @return
     */
    public static String  timeCompare(Date gqsj,Date now){

        java.text.DateFormat df=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1 = df.format(gqsj);
        String date2 = df.format(now);
        java.util.Calendar c1=java.util.Calendar.getInstance();
        java.util.Calendar c2=java.util.Calendar.getInstance();
        try{
            c1.setTime(df.parse(date1));
            c2.setTime(df.parse(date2));
        }catch(java.text.ParseException e){
            logger.error("格式不正确");
        }
        int result=c1.compareTo(c2);
        if(result<0){
            return "接口已过期";
        }
        return  "";
    }

}
