package com.bcht.its.pojo;

import lombok.Getter;
import lombok.Setter;
import org.nutz.dao.DB;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Prev;
import org.nutz.dao.entity.annotation.SQL;
import org.nutz.dao.entity.annotation.Table;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/5/22.
 */
@Table("ds_t_jksj")
@Getter
@Setter
public class Dstjksj {
    @Id
    @Prev(@SQL(value = "select seq_jkid.nextval from dual",db = DB.ORACLE))
    private int id;
    private String sydw;
    private String syxz;
    private Date sqrq;
    private Date yxrq;
    private String key;
    private String lxfs;
    private String sqr;
    private String ssdw;
    private String lxdz;
    private Date gxrq;
    private String syip;
    private String yt;
    private String sqrqStr;
    private String yxrqStr;
    private String gxrqStr;
    public String getSqrqStr() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return  sdf.format(sqrq);
    }
    public String getYxrqStr() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return  sdf.format(yxrq);
    }
    public String getGxrqStr() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return  sdf.format(gxrq);
    }
   // private List<Dstjk> dstjk;
}
