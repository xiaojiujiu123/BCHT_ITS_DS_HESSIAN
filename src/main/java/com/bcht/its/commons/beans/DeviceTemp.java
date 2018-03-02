package com.bcht.its.commons.beans;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Administrator on 2017/6/28.
 * 缓存设备信息
 */
@Getter
@Setter
public class DeviceTemp {
    private String sbbh; // 我方设备编号
    private String jcsbbh;// 集成指挥平台编号
    private String ldsbbh;// 蓝盾设备编号
    private String lddwbh;//蓝盾点位编号
    private String sblx; //设备类型
    private String cdbh; // 上传集成指挥 车道编号
    private String jcfx;// 上传集成指挥 行驶方向 1 上行 2 下行
    private String ldfxmc;// 上传广控平台方向名称
}
