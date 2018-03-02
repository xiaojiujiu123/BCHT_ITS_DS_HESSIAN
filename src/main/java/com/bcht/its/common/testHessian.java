package com.bcht.its.common;

import com.bcht.its.pojo.PassingVehicle;
import com.bcht.its.service.BchtTrffService;
import com.caucho.hessian.client.HessianProxyFactory;

import java.net.MalformedURLException;

/**
 * Created by Administrator on 2017/5/26.
 */
public class testHessian {
    public static void main(String[] args){


    HessianProxyFactory proxyFactory =  new HessianProxyFactory();
        proxyFactory.setOverloadEnabled(true);
    String url="http://192.168.8.35:8080/BchtTrffService";
        try {
        BchtTrffService c = (BchtTrffService)  proxyFactory.create(BchtTrffService.class,url);
            PassingVehicle x = new PassingVehicle();
       // c.writePassVehicle("xfsdfsfdsfsdf",x.toString());
    } catch (MalformedURLException e) {
        e.printStackTrace();
    }
    }
}
