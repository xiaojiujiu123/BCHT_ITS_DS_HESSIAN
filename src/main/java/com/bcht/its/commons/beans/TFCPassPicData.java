package com.bcht.its.commons.beans;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 过车图片数据
 */
@Setter
@Getter
public class TFCPassPicData implements Serializable{
    private static final long serialVersionUID = 1L;
    private String gcbh;
    private String sbbh;
    private Date gcsj;
    private List<byte[]> dataList;
}
