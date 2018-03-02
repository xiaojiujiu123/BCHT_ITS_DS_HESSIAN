package com.bcht.its.pojo;

import lombok.Getter;
import lombok.Setter;
import org.nutz.dao.DB;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Prev;
import org.nutz.dao.entity.annotation.SQL;
import org.nutz.dao.entity.annotation.Table;

/**
 * Created by Administrator on 2017/5/22.
 */
@Table("ds_t_jksq_ref_jk")
@Setter
@Getter
public class DsTJksqRefJk {
    @Id
    @Prev(@SQL(value = "select seq_jkid.nextval from dual",db = DB.ORACLE))
    private int id;
    private int jksqid;
    private int jkid;
}
