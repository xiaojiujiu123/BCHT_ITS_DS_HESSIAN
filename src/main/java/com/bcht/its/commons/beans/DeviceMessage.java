package com.bcht.its.commons.beans;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by scs on 2017/4/6.
 * rabbitMQ传递的设备信息
 */
@Setter
@Getter
public class DeviceMessage implements Serializable{
    /**操作信息 有：SAVE、UPDATE、DEL三个值*/
    private String msgType;
    /**设备类型 如：01为设备，02为终端等*/
    private String deviceType;
    /**传递的设备对象*/
    private Object sb;
}
