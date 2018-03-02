package com.bcht.its.pojo;

import lombok.Getter;
import lombok.Setter;
import org.nutz.dao.DB;
import org.nutz.dao.entity.annotation.*;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Created by Administrator on 2017/5/22.
 */
@Table("ds_t_jk")
@Getter
@Setter
public class Dstjk {
    @Id
    @Prev(@SQL(value = "select seq_jkid.nextval from dual",db = DB.ORACLE))
    private int id;
    private String jkbh;
    private String  jkmc;
    private String jklx;
    private String jkff;
    private String jkbb;
    private Date gxsj;

    private String gxsjStr;
    public String getGxsjStr() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return  sdf.format(gxsj);
    }
}
