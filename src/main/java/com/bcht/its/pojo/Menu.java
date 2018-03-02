package com.bcht.its.pojo;

import lombok.Getter;
import lombok.Setter;
import java.util.Set;

/**
 * Created by taosh on 2017/3/30.
 */
@Getter
@Setter
public class Menu {

    private String menuID;//  varchar(30) NOT NULL COMMENT '菜单编号',
    private String mname;//  varchar(50) DEFAULT NULL COMMENT '菜单名称',
    private String parentID;//  varchar(30) NOT NULL COMMENT '上级菜单 顶级为0',
    private String murl;//  varchar(255) DEFAULT NULL COMMENT '菜单地址',
    private String icons;//  varchar(255) DEFAULT NULL COMMENT '菜单图标 根据各个系统，可以为class或图标地址',
    private String checked;
}
