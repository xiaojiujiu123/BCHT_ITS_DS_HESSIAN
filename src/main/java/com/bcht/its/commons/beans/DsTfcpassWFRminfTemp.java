package com.bcht.its.commons.beans;

import lombok.Getter;
import lombok.Setter;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

import java.util.Date;

/**
 * Created by JXX on 2017/6/5.
 */
@Table("ds_tfcpasswf_rminf_temp")
@Getter
@Setter
public class DsTfcpassWFRminfTemp {
    @Name
    private String id;
    private String key;
    private Date rksj;
    private String passjson;
}
