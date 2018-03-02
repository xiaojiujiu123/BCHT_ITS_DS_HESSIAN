package com.bcht.its.commons.beans;

import lombok.Getter;
import lombok.Setter;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

/**
 * Created by Administrator on 2017/6/30.
 */
@Table("ds_t_gczstemp")
@Getter
@Setter
public class DsTGczstemp {
    @Name
    private String id ;
    private String idx;
    private String gczl;
}
