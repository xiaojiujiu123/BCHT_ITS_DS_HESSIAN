package com.bcht.its.pojo;

import lombok.Getter;
import lombok.Setter;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.ManyMany;
import org.nutz.dao.entity.annotation.Table;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by zhaojinglong on 2017/3/30 0030.
 */
@Getter
@Setter
public class Sysrole {
    private  Long roleid;      //角色标号
    private  String rolename;  //角色名称
    private  Integer state;    //角色状态
    private List<Menu> menus;  //拥有的菜单

}
