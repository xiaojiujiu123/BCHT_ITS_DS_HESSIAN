package com.bcht.its.commons.beans;

/**
 * Created by scs on 2017/3/24.
 */

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 交通违法相关信息
 */
@Getter
@Setter
public class AlarmPassInfo   implements Serializable {
    private static final long serialVersionUID = 1L;
    /**超速比*/
    private float csb;
    /**超速值*/
    private float csz;
}
