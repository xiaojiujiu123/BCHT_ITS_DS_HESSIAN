package com.bcht.its.config;

import com.bcht.its.Application;
import com.bcht.its.pojo.Dstjksj;
import com.bcht.its.service.KeyValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/6/9.
 * springboot加载完成后会执行这个类
 * 类的作用主要是初始化一些静态变量。如设备信息，接口信息等
 */
@Component
public class Initalizations implements CommandLineRunner {
    @Autowired
    public KeyValidationService keyValidationServiceImpl;

    @Override
    public void run(String... strings) throws Exception {
        init();
    }

    /**
     * 初始化接口管理中数据
     */
    public void init() {
        try {
            if (Application.INTERFACECACHE == null) {
                keyValidationServiceImpl.findAllJk();
                Application.INTERFACECACHE = keyValidationServiceImpl.findAllJk();
            }
            if (Application.INTERFACEMAP == null) {
                Application.INTERFACEMAP = new HashMap<String, Dstjksj>();
                List<Dstjksj> list = keyValidationServiceImpl.findAll();
                for (Dstjksj lists : list) {
                    Application.INTERFACEMAP.put(lists.getKey(), lists);
                }
            }
            if (Application.INTERFACECACHE != null && Application.INTERFACEMAP != null) {
                //off = true;
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}