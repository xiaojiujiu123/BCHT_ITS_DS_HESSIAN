package com.bcht.its.commons.beans;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by jiangxinxin on 2017/6/13.
 */
@Getter
@Setter
public class VehicleInspectionLedger implements Serializable {
    private String fwzbh  ;//执法服务站编号	.
    private String clzt;//车辆状态	1-正常 2-无牌 3-套牌 4-假牌.
    private String hpzl;//号牌种类	.
    private String hphm;//号牌号码	.
    private String jccllx;//检查车辆类型	.
    private String sfd;//始发地	.
    private String mdd;//目的地	.
    private String zks;//实际载客人数	.
    private String sjzzl;//实际载质量	.
    private String gps;//GPS装备情况 	1正常 0 不正常 9 未配备.
    private String aqsb;//安全设备配备情况	0不合格 1合格.
    private String cllthw;//车辆轮胎花纹情况	0 不合格 1合格.
    private String wfyy;//是否违法营运	0否 1是.
    private String jaqd;//驾驶人是否系安全带	0否 1是.
    private String pljs;//是否疲劳驾驶	0否 1是.
    private String ffgz;//是否非法改装	0否 1是.
    private String ztfgbs;//是否按规定粘贴反光标识	0否 1是.
    private String azfhzz;//是否安装侧后防护装置	0否 1是.
    private String xgjsbz;//是否悬挂或喷涂警示标志	0否 1是.
    private String azdsj;//是否按指定时间行驶	0否 1是.
    private String azdlx;//是否按指定路线行驶	0否 1是.
    private String sfwzjs;//是否无证驾驶	0否 1是.
    private String sfyfjsy;//是否有副驾驶员	0否 1是.
    private String qdystxz;//是否取得道路运输通行证	0否 1是.
    private String sfyqwjy;//是否逾期未检验	0否 1是.
    private String sfyqwbf;//是否逾期未报废	0否 1是.
    private String sfwfwcl;//是否违法未处理	0否 1是.
    private String jcjg;//是否重大按情 	00-无，10-作案车辆 ，20-A级逃犯.
    private String jcqkms;//检查情况描述	.
    private String jcsj;//检查时间	YYYY-MM-DD HH24:MI:SS.
    private String jcmj;//检查民警	.
    private String zjszh;//主驾驶证号	.
    private String zlxdh;//.
    private String zjssfcf;//.
    private String zjssfyqwsy;//.
    private String zjssfyqwhz;//.
    private String zjszjbf;//.
    private String fjszh;//.
    private String flxdh;//.
    private String fjssfcf;//.
    private String fjssfyqwsy;//.
    private String fjssfyqwhz;//.
    private String fjszjbf;//.

}
