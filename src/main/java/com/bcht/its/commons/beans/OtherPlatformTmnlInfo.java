package com.bcht.its.commons.beans;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 第三方平台设备相关配置信息
 */
@Getter
@Setter
public class OtherPlatformTmnlInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**蓝盾平台的点位信息*/
    private String LD_SITE_CODE;
    /**蓝盾平台的设备ID*/
    private String LD_SBID;
    /**蓝盾平台的设备方向*/
    private String LD_SBFX;

    /**六合一备案的设备ID*/
    private String TRMI_SBID;
    /**六合一备案的设备方向*/
    private String TRMI_SBFX;

    /**集成指挥平台的设备ID*/
    private String RMINF_SBID;
    /**集成指挥平台的设备方向*/
    private String RMINF_SBFX;
}
