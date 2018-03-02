package com.bcht.its.commons.beans;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by jiangxinxin on 2017/6/13.
 */
@Getter
@Setter
public class EarlyWarningFeedback implements Serializable {
    private String yjxh;//预警序号.
    private String ywlx;//业务类型.
    private String qsjg;//签收结果.
    private String sfcjlj;//是否出警拦截.
    private String ljclqk;//拦截车辆情况.
    private String wljdyy;//未拦截到原因.
    private String chjg;//是否嫌疑车辆.
    private String cljg;//处置结果.
    private String wsbh;//法律文书编号.
    private String jyw;//文书校验位.
    private String wslb;//文书类别.
    private String yjbm;//移交部门.
    private String lxr;//联系人.
    private String lxdh;//联系电话.
    private String wchyy;//非嫌疑车辆原因.
    private String czqkms;//处置情况描述.
    private String czdw;//处置单位.
    private String czr;//处置民警.
    private String czsj;//处置时间.

}
