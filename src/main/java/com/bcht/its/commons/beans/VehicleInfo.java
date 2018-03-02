package com.bcht.its.commons.beans;


import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by zyq on 2017/5/15.
 */
@XStreamAlias("vehicleinfo")
public class VehicleInfo {
    private String kkbh;//卡口编号，不可为空
    private String fxlx;//方向类型，不可为空
    private String cdh;//车道号，不可为空
    private String hphm;//号牌号码，不可为空
    private String hpzl;//号牌种类，不可为空
    private String gcsj;//过车时间，不可为空
    private String clsd;//车辆速度，不可为空
    private String clxs;//车辆限速，不可为空
    private String wfdm;//违法代码
    private String cwkc;//车外廓长，不可为空
    private String hpys;//号牌颜色，不可为空
    private String cllx;//车辆类型
    private String fzhpzl;//辅助号牌种类
    private String fzhphm;//辅助号牌号码
    private String fzhpys;//辅助号牌颜色
    private String clpp;//车辆品牌
    private String clwx;//车辆外形
    private String csys;//车身颜色
    private String tplj;//图片路径，不可为空
    private String tp1;//图片1
    private String tp2;//图片2
    private String tp3;//图片3
    private String tztp;//特征图片

    public String getKkbh() {
        return kkbh;
    }

    public void setKkbh(String kkbh) {
        this.kkbh = kkbh;
    }

    public String getFxlx() {
        return fxlx;
    }

    public void setFxlx(String fxlx) {
        this.fxlx = fxlx;
    }

    public String getCdh() {
        return cdh;
    }

    public void setCdh(String cdh) {
        this.cdh = cdh;
    }

    public String getHphm() {
        return hphm;
    }

    public void setHphm(String hphm) {
        this.hphm = hphm;
    }

    public String getHpzl() {
        return hpzl;
    }

    public void setHpzl(String hpzl) {
        this.hpzl = hpzl;
    }

    public String getGcsj() {
        return gcsj;
    }

    public void setGcsj(String gcsj) {
        this.gcsj = gcsj;
    }

    public String getClsd() {
        return clsd;
    }

    public void setClsd(String clsd) {
        this.clsd = clsd;
    }

    public String getClxs() {
        return clxs;
    }

    public void setClxs(String clxs) {
        this.clxs = clxs;
    }

    public String getWfdm() {
        return wfdm;
    }

    public void setWfdm(String wfdm) {
        this.wfdm = wfdm;
    }

    public String getCwkc() {
        return cwkc;
    }

    public void setCwkc(String cwkc) {
        this.cwkc = cwkc;
    }

    public String getHpys() {
        return hpys;
    }

    public void setHpys(String hpys) {
        this.hpys = hpys;
    }

    public String getCllx() {
        return cllx;
    }

    public void setCllx(String cllx) {
        this.cllx = cllx;
    }

    public String getFzhpzl() {
        return fzhpzl;
    }

    public void setFzhpzl(String fzhpzl) {
        this.fzhpzl = fzhpzl;
    }

    public String getFzhphm() {
        return fzhphm;
    }

    public void setFzhphm(String fzhphm) {
        this.fzhphm = fzhphm;
    }

    public String getFzhpys() {
        return fzhpys;
    }

    public void setFzhpys(String fzhpys) {
        this.fzhpys = fzhpys;
    }

    public String getClpp() {
        return clpp;
    }

    public void setClpp(String clpp) {
        this.clpp = clpp;
    }

    public String getClwx() {
        return clwx;
    }

    public void setClwx(String clwx) {
        this.clwx = clwx;
    }

    public String getCsys() {
        return csys;
    }

    public void setCsys(String csys) {
        this.csys = csys;
    }

    public String getTplj() {
        return tplj;
    }

    public void setTplj(String tplj) {
        this.tplj = tplj;
    }

    public String getTp1() {
        return tp1;
    }

    public void setTp1(String tp1) {
        this.tp1 = tp1;
    }

    public String getTp2() {
        return tp2;
    }

    public void setTp2(String tp2) {
        this.tp2 = tp2;
    }

    public String getTp3() {
        return tp3;
    }

    public void setTp3(String tp3) {
        this.tp3 = tp3;
    }

    public String getTztp() {
        return tztp;
    }

    public void setTztp(String tztp) {
        this.tztp = tztp;
    }
}
