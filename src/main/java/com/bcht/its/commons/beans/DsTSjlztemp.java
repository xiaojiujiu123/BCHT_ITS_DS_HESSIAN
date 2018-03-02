package com.bcht.its.commons.beans;

import lombok.Getter;
import lombok.Setter;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

/**
 * Created by Administrator on 2017/7/20.
 */
@Getter
@Setter
@Table("ds_t_sjlztemp")
public class DsTSjlztemp {
    @Name
    private String id ;
    private String name;
    private String gcvalue;
    private String wfvalue;
    private String gcnum;
    private String wfnum;
    private String value;
    private String glbm;
    private String bmmc;
}
