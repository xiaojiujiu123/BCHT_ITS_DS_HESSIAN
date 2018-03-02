package com.bcht.its.service;

/**
 * Created by Administrator on 2017/5/18.
 */
public interface BchtTrffService {
    /**
     * 百诚慧通过车写入接口
     * @param key 序列号
     * @param passingVehicle 过车对象JSO
     * @return
     */
    public String writePassVehicle(String key, String passingVehicle);
    /**
     * 百诚慧通过车违法写入接口
     * @param key 序列号
     * @param violationVehicle 过车违法对象JSON
     * @return
     */
    public String writeViolationVehicle(String key, String violationVehicle);
    /**
     * 百诚慧通
     * @param key 序列号
     * @param heartBeat 设备心跳对象JSON
     * @return
     */
    public String writeDeviceHeartBeat(String key, String heartBeat);

    /**
     * 写入服务器心跳
     * @param key
     * @param serviceHeartBeat
     * @return
     */
    public String writeServiceHeartBeat(String key, String serviceHeartBeat);

    /**
     * 写入警车GPS信息
     * @param key
     * @param policeCarLocation
     * @return
     */
    public String writePoliceCarLocation(String key, String policeCarLocation);

    /**
     * 上传交通气象信息接口
     * @param key
     * @param trafficWeather
     * @return
     */
    public String writeTrafficWeather(String key, String trafficWeather);

    /**
     * 上传交通事件信息接口
     * @param key
     * @param trafficIncident
     * @return
     */
    public String writeTrafficIncident(String key, String trafficIncident);

    /**
     * 上传交通诱导发布信息接口
     * @param key
     * @param trafficGuidance
     * @return
     */
    public  String writeTrafficGuidance(String key, String trafficGuidance);
    /**
     * 上传单警定位信息接口
     *
     * @param key
     * @param singlePoliceLocation
     * @return
     */
    public String writeSinglePoliceLocation(String key, String singlePoliceLocation);

    /**
     * 上传停车场车辆信息接口
     * @param key
     * @param parkVehicle
     * @return
     */
    public String writeParkVehicle(String key, String parkVehicle);
    public String writeVehicleInspectionLedger(String key, String vehicleInspectionLedger);
    public String writeEarlyWarningFeedback(String key, String earlyWarningFeedback);
    public String writeInterceptDisposalPictures(String key, String interceptDisposalPictures);
}
