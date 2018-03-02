package com.bcht.its.commons.beans;

import lombok.Getter;
import lombok.Setter;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

/**
 * Created by Administrator on 2017/7/20.
 */
@Getter
@Setter
@Table("ds_t_mqtemp")
public class DsTMqTemp {
    @Name
    private String id;
    private String gccount;
    private String wfcount;
    private String gpscount;
    private String llcount;
}
