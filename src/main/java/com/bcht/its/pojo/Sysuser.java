package com.bcht.its.pojo;

import lombok.Getter;
import lombok.Setter;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

import java.util.Date;

/**
 * Created by zhaojinglong on 2017/3/30 0030.
 */
@Table("sys_user")
@Getter
@Setter
public class Sysuser {
    @Name
    @Column("loginName")
    private String  loginname; //用户账号
    @Column("roleID")
    private Long    roleid;    //角色编号
    @Column("loginPass")
    private String  loginpass; //用户密码
    @Column("startIP")
    private String  startip;  //开始IP
    @Column("endIP")
    private String  endip;   //结束IP
    @Column("state")
    private Integer state;   //在线状态
    @Column("userName")
    private String  username; //用户名称
    @Column("sex")
    private Integer sex;      //性别
    @Column("birthday")
    private String    birthday; //生日
    private String roleName;  //角色名称 非数据库字段


}
