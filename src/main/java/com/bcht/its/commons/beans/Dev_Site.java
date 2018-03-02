package com.bcht.its.commons.beans;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 点位表
 * @author scs
 *
 */
@Setter
@Getter
public class Dev_Site implements Serializable
{

	private static final long serialVersionUID = 1L;
	private String dwid;	//VARCHAR2(32)	N			点位ＩＤ 由道路编号+路口号+米数形成
	private String dwdm;	//VARCHAR2(32)	Y			点位代码 用于跟其它系统点位同步
	private String dwmc;	//VARCHAR2(100)	Y			点位名称 
	private String glbm;	//VARCHAR2(12)	N			管理部门
	private String xzqh;	//CHAR(6)	N			行政区划  c.xtlb='00' and c.dmlb='0033' and c.dmz like '3412%'
	private String dldm;	//CHAR(5)	Y			道路代码 关联 frm_roaditem
	private String dllx;	//VARCHAR2(2)	Y			道路类型 c.xtlb='00' and c.dmlb='3115' 或 c.xtlb='00' and c.dmlb='3124'
	private String lkh;	//VARCHAR2(4)	Y			路口号  frm_roadsegitem
	private String dlms;	//NUMBER(3)	Y			道路米数
	private String szdd;	//VARCHAR2(128)	Y			点位地址
	private String jd;	//VARCHAR2(17)	Y			经度
	private String wd;	//VARCHAR2(17)	Y			纬度
	private String dcxs;	//NUMBER	Y			大车限速
	private String xcxs;	//NUMBER	Y			小车限速
	private String mtcxs;	//NUMBER	Y			摩托车限速
	private String xzds;	//NUMBER	Y			限低速
	private String zt;	//NUMBER	Y			1:启用 2:禁用
	private String glms;	//NUMBER(3)	Y			道路米数

	
}
