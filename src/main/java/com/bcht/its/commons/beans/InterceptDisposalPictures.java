package com.bcht.its.commons.beans;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by jiangxinxin on 2017/6/13.
 */
@Getter
@Setter
public class InterceptDisposalPictures implements Serializable {
    private String yjxh;//预警序号.
    private String tp1;//图片1.
    private String tp2;//图片2.
    private String tp3;//图片3.
    private String scdw;//上传单位.
    private String scr;//上传人.

}
