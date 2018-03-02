package com.bcht.its.service;

import com.bcht.its.commons.beans.DeviceInfo;
import com.bcht.its.commons.beans.DeviceTemp;
import com.bcht.its.commons.beans.DsTfcpassJsonTemp;
import com.bcht.its.pojo.Dstjk;
import com.bcht.its.pojo.Dstjksj;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/26.
 */
public interface KeyValidationService  extends Serializable {
    public List<String> findAllJk();
    public List<Dstjksj> findAll();
    public Map<String,List<String>> findAllMethod();
    public Map<String,DeviceTemp> findAllDevs();
}
