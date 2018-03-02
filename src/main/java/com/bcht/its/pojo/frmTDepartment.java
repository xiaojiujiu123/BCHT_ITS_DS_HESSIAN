package com.bcht.its.pojo;

import lombok.Getter;
import lombok.Setter;
import org.nutz.dao.DB;
import org.nutz.dao.entity.annotation.*;

/**
 * Created by Administrator on 2017/5/25.
 */
@Table("frm_t_department")
@Getter
@Setter
public class frmTDepartment {
    @Name
    private String glbm;              //		管理部门代码
    private String bmmc;              //		部门名称
    private String bmqc;              //		部门全称
    private String yzmc;              //		印章名称
    private String fzjg;              //		发证机关
    private String bmjb;           //部门级别
    private String fzr	;         //	负责人
    private String lxr	;         //	联系人
    private String lxdh;              //		联系电话
    private String czhm;              //		传真号码
    private String lxdz;              //		联系地址
    private String sjbm;              //		上级管理部门
    private String bz	 ;        //	备注
    private String jzjb;              //	建制级别
    private String gltz;           //管理体制
    private String jfly;           //经费来源
    private String yfly;           //营房来源 大中队填写
    private String jlzt;              //记录状态 1-正常 0-删除
}
