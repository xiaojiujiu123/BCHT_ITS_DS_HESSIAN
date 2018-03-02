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
 * Created by ty on 2017/4/18.
 */
@Getter
@Setter
public class Syssite  {
    private String  sitecode;//varchar(32) NOT NULL点位ＩＤ 由道路编号+路口号+米数形成
    private  String dwmc;//varchar(100) NULL点位名称
    private Integer  dcxs;//int(11) NULL大车限速
    private Integer xcxs;//int(11) NULL小车限速
    private Integer mtcxs;//int(11) NULL摩托车限速
    private Integer xzds;//int(11) NULL限低速
    private Integer zt;//int(11) NULL1:启用 2:禁用

}
