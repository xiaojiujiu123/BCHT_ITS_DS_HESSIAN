package com.bcht.its.physical;

/**
 * 主要是封装操作系统类型
 * Created by taoshide on 2016/10/22.
 */
public enum SnmpType {
    WINDOWS("windows"),LINUX("linux"),ESXI("esxi");

    private final String type;

    private SnmpType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}
