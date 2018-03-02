package com.bcht.its.Scheduled;

import com.bcht.its.commons.beans.*;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import lombok.Getter;
import lombok.Setter;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2017/6/29.
 * 汇聚平台首页数据组装
 */
@Component
@ConfigurationProperties(prefix = "spring.rabbitmq")
@Getter
@Setter
public class ScheduledIndex {
    @Autowired
    private Dao dao;
    private String host;
    private int port;
    private String username;
    private String password;
    private static ConnectionFactory factory;
    private static com.rabbitmq.client.Connection connection;
    /**
     * 汇聚平台首页 过车曲线图数据组装
     */
    @Scheduled(cron = "*/7 * * * * ?")
    public void AddGcqx(){
        List<DsTGcqxtemp> lists = getGcqx(dao);
        dao.clear(DsTGcqxtemp.class, Cnd.where("1","=","1"));
        dao.insert(lists);
    /*    String sql = "";
       sql+="insert into ds_t_gcqxtemp(id,SJ,IDX,GCZL)  select SEQ_TEMPUP.nextval , sj,idx,gczl  from ( \n";
       sql+="        select   l sj,decode(idx,'','gc',idx) idx,decode(gczl,'',0,gczl) gczl from (select * from (select level-1 l from dual connect by level <=24) gclv left join \n";
       sql+="                (select 'gc' idx,count(gcbh) gczl,to_char(gcsj,'hh24') sj from ds_tfcpass where \n";
       sql+="gcsj> to_date(to_char(sysdate,'yyyy/mm/dd'),'yyyy/mm/dd')and  gcsj<sysdate group by to_char(gcsj,'hh24') order by to_char(gcsj,'hh24') desc ) gcList \n";
       sql+="on gclv.l = gcList.sj order by gclv.l  asc) \n";
       sql+="union \n";
       sql+="select l,decode(idx,'','wf',idx)idx,decode(gczl,'',0,gczl) gczl from (select level-1 l from dual connect by level <=24) wflv left join \n";
       sql+="        (select 'wf' idx,count(gcbh) gczl,to_char(gcsj,'hh24') sj from ds_violation where \n";
       sql+="gcsj> to_date(to_char(sysdate,'yyyy/mm/dd'),'yyyy/mm/dd')and  gcsj<sysdate group by to_char(gcsj,'hh24') order by to_char(gcsj,'hh24') desc ) gcList \n";
       sql+="on wflv.l = gcList.sj \n";
       sql+="union \n" ;
       sql+="select l,decode(idx,'','gps',idx)idx,decode(gczl,'',0,gczl) gczl from (select level-1 l from dual connect by level <=24) wflv left join \n";
       sql+="        (select 'wf' idx,count(lsh) gczl,to_char(sbsj,'hh24') sj from ds_t_policecarlocation where \n";
       sql+="sbsj> to_date(to_char(sysdate,'yyyy/mm/dd'),'yyyy/mm/dd')and  sbsj<sysdate group by to_char(sbsj,'hh24') order by to_char(sbsj,'hh24') desc ) gcList \n";
       sql+="on wflv.l = gcList.sj) " ;
        Sql newSql = Sqls.create(sql);
        newSql.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection connection, ResultSet resultSet, Sql sql) throws SQLException {
                return null;
            }
        });
        if(result>0){
            dao.execute(newSql);
        }*/

    }

    /**
     * 过车指数表盘
     */
    @Scheduled(cron = "*/7 * * * * ?")
    public void  addgczs(){
        List<DsTGczstemp> lists =  getGczs(dao);
        int result = dao.clear(DsTGczstemp.class);
        dao.insert(lists);
      /*  String sql ="insert into ds_t_gczstemp(id,IDX,GCZL)  \n" +
                "select SEQ_TEMPUP.nextval ,idx,gczl  from (select idx,gczl from (\n"+
                "select idx,gczl from (select 'gc'idx, \n" +
                "trunc(decode(avg(t.jssj - t.gcsj)*24*60*60,'',0.0,avg(t.jssj - t.gcsj) * 24 * 60 * 60),2) \n" +
                "gczl from ds_tfcpass t where gcsj >to_date(to_char(sysdate,'yyyy/mm/dd'),'yyyy/mm/dd')\n" +
                " and gcsj< sysdate ) union select 'wf' idx,trunc(decode(avg(t.jssj - t.gcsj)*24*60*60,'',0.0\n" +
                ",avg(t.jssj - t.gcsj) * 24 * 60 * 60),2) wfzl from ds_violation t where gcsj > \n" +
                "to_date(to_char(sysdate,'yyyy/mm/dd'),'yyyy/mm/dd')  and gcsj \n" +
                "<sysdate union select 'gps' idx,  trunc(decode(avg(t.jssj - t.sbsj)*24*60*60,'',0.0\n" +
                ",avg(t.jssj - t.sbsj) * 24 * 60 * 60),2) gpszl from ds_t_policecarlocation t \n" +
                "where sbsj >to_date(to_char(sysdate,'yyyy/mm/dd'),'yyyy/mm/dd')and sbsj <sysdate ))";
        Sql newSql = Sqls.create(sql);
        newSql.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection connection, ResultSet resultSet, Sql sql) throws SQLException {
                return null;
            }
        });
        if(result>0){
            dao.execute(newSql);
        }*/
    }

    /**
     * 流转指数临时数据
     */
    @Scheduled(cron = "*/7 * * * * ?")
    public void addgcxj(){
        try {
         /*   Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date zero = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String startTime = sdf.format(zero);
        String endTime = sdf.format(new Date());
            String sql = "insert into ds_t_sjlztemp(id,name,value,num)\n" +
                    " select seq_tempup.nextval,name,value,num from (select name,value,num from (\n" +
                    "select substr(name, 0, 2) name,\n" +
                    "       decode(trunc(value, 2), '', -1, trunc(value, 2)) value,\n" +
                    "       nums num\n" +
                    "  from (select bmmc name, zdgcvalue value, zdgcnum nums\n" +
                    "          from (select *\n" +
                    "                  from (select glbm bmbh\n" +
                    "                          from frm_t_department dep\n" +
                    "                         where glbm like '%00000010%'\n" +
                    "                            or glbm like '%00000000%'\n" +
                    "                         start with glbm = '530000000000'\n" +
                    "                        connect by prior glbm = sjbm) dep\n" +
                    "                  left join (select frm.bmmc,\n" +
                    "                                   frm.glbm,\n" +
                    "                                   avg(zd.gcvalue) zdgcvalue,\n" +
                    "                                   sum(gcnum) zdgcnum\n" +
                    "                              from frm_t_department frm\n" +
                    "                              left join (select m.name,\n" +
                    "                                               m.sjbm,\n" +
                    "                                               m.glbm,\n" +
                    "                                               m.gcvalue,\n" +
                    "                                               m.gcnum\n" +
                    "                                          from (select dep.bmmc name,\n" +
                    "                                                       dep.glbm,\n" +
                    "                                                       dep.sjbm,\n" +
                    "                                                       avg(dss.s) gcvalue,\n" +
                    "                                                       sum(dss.num) gcnum\n" +
                    "                                                  from frm_t_department dep\n" +
                    "                                                  left join dev_t_base dev\n" +
                    "                                                    on dep.glbm = dev.glbm\n" +
                    "                                                  left join (select ds.sbxh,\n" +
                    "                                                                   count(*) num,\n" +
                    "                                                                   avg(ds.jssj -\n" +
                    "                                                                       ds.gcsj) * 24 * 60 * 60 s\n" +
                    "                                                              from ds_tfcpass ds\n" +
                    "                                                             where ds.gcsj >=\n" +
                    "                                                                   to_date('"+startTime+"',\n" +
                    "                                                                           'yyyy/mm/dd hh24:mi:ss')\n" +
                    "                                                               and ds.gcsj <=\n" +
                    "                                                                   to_date('"+endTime+"',\n" +
                    "                                                                           'yyyy/mm/dd hh24:mi:ss')\n" +
                    "                                                             group by ds.sbxh) dss\n" +
                    "                                                    on dss.sbxh = dev.sbbh\n" +
                    "                                                 where dss.s is not null\n" +
                    "                                                 group by dep.bmmc,\n" +
                    "                                                          dep.sjbm,\n" +
                    "                                                          dep.glbm) m\n" +
                    "                                         where m.gcvalue is not null\n" +
                    "                                         group by m.sjbm,\n" +
                    "                                                  m.glbm,\n" +
                    "                                                  m.name,\n" +
                    "                                                  m.gcvalue,\n" +
                    "                                                  m.gcnum) zd\n" +
                    "                                on frm.glbm = (case\n" +
                    "                                     when zd.glbm like '%00000000%' or\n" +
                    "                                          zd.glbm like '%00000010%' then\n" +
                    "                                      zd.glbm\n" +
                    "                                     else\n" +
                    "                                      zd.sjbm\n" +
                    "                                   end)\n" +
                    "                             group by frm.bmmc, frm.glbm) zd\n" +
                    "                    on dep.bmbh = zd.glbm\n" +
                    "                 where dep.bmbh not in ('530000000100', '530000000000'))\n" +
                    "         order by value asc)))\n";

            Sql newSql = Sqls.create(sql);*/
            List<DsTSjlztemp> lists = getSjlz(dao);
            int result = dao.clear(DsTSjlztemp.class);
            dao.insert(lists);
        }catch (Exception e){
            // e.printStackTrace();
        }
    }

    /**
     * mq数据
     */
    // @Scheduled(cron = "*/8 * * * * ?")
    public void mqCount(){
        if(factory==null){
            factory = new ConnectionFactory();
            factory.setHost(host);
            factory.setPort(port);
            factory.setUsername(username);
            factory.setPassword(password);
        }
        try{
            if(connection==null){
                connection = factory.newConnection();
            }
            Channel channel =connection.createChannel();
            String queue_wf = channel.queueDeclarePassive("ITS_QUEUE_HESSIAN_WF").toString();
            String queue_gc = channel.queueDeclarePassive("ITS_QUEUE_HESSIAN").toString();
            String queue_gps = channel.queueDeclarePassive("ITS_QUEUE_HESSIAN_GPS").toString();
            String message_wfCount = queue_wf.substring(queue_wf.indexOf("count=")+6,queue_wf.indexOf(", c"));
            String consumer_wfCount = queue_wf.substring(queue_wf.lastIndexOf("count=")+6,queue_wf.indexOf(")"));
            String message_gcCount = queue_gc.substring(queue_gc.indexOf("count=")+6,queue_gc.indexOf(", c"));
            String consumer_gcCount = queue_gc.substring(queue_gc.lastIndexOf("count=")+6,queue_gc.indexOf(")"));
            String message_gpsCount = queue_gps.substring(queue_gps.indexOf("count=")+6,queue_gps.indexOf(", c"));
            String consumer_gpsCount = queue_gps.substring(queue_gps.lastIndexOf("count=")+6,queue_gps.indexOf(")"));
            int sumwfCount=0;
            int sumgcCount=0;
            int sumgpsCount=0;
            if(Integer.parseInt(message_wfCount)==0){
                sumwfCount = Integer.parseInt(message_wfCount);
            }else{
                sumwfCount = Integer.parseInt(message_wfCount) +Integer.parseInt(message_wfCount);
            }
            if(Integer.parseInt(message_gcCount)==0){
                sumgcCount = Integer.parseInt(message_gcCount);
            }else{
                sumgcCount = Integer.parseInt(consumer_gcCount) +Integer.parseInt(message_gcCount);
            }
            if(Integer.parseInt(message_gpsCount)==0){
                sumgpsCount = Integer.parseInt(message_gpsCount);
            }else{
                sumgpsCount = Integer.parseInt(consumer_gpsCount) +Integer.parseInt(message_gpsCount);
            }
            DsTMqTemp mqTemp = new DsTMqTemp();
            mqTemp.setId(UUID.randomUUID().toString());
            mqTemp.setGccount(sumgcCount+"");
            mqTemp.setWfcount(sumwfCount+"");
            mqTemp.setGpscount(sumgpsCount+"");
            int result = dao.clear("ds_t_mqtemp");
            if(result>-1){
                dao.insert(mqTemp);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public List<DsTSjlztemp> getSjlz(Dao dao){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date zero = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String startTime = sdf.format(zero);
        String endTime = sdf.format(new Date());
        String sql1 =   "select glbm,decode(substr(name, 0,3),'大丽高','大丽高速','玉龙雪','玉龙雪山',substr(name, 0,3))  name , \n" +
                "                     decode(substr(name, 0,3),'大丽高','大丽高速','玉龙雪','玉龙雪山',substr(name, 0,3))  bmmc,\n" +
                "                       decode(trunc(value, 1), '', -10000000, trunc(value, 1)) gcvalue,\n" +
                "                       decode(trunc(value, 1), '', -10000000, trunc(value, 1)) value, \n" +
                "                       gcnum\n" +
                "                  from (select glbm,\n" +
                "                               bmmc      name,\n" +
                "                               zdgcvalue value, \n" +
                "                               zdgcnum   gcnum \n" +
                "                              \n" +
                "                          from (select *\n" +
                "                                  from (select glbm bmbh\n" +
                "                                          from frm_t_department dep\n" +
                "                                         where glbm like '%0000'\n" +
                "                                         or glbm like '%0010'\n" +
                "                                         start with glbm = '513400000000'\n" +
                "                                        connect by prior glbm = sjbm) dep\n" +
                "                                  left join (select frm.bmmc,\n" +
                "                                                   frm.glbm,\n" +
                "                                                   avg(zd.gcvalue) zdgcvalue, \n" +
                "                                                   sum(gcnum) zdgcnum  \n" +
                "                                              from frm_t_department frm\n" +
                "                                              left join (select m.name,\n" +
                "                                                               m.sjbm,\n" +
                "                                                               m.glbm,\n" +
                "                                                               m.gcvalue,\n" +
                "                                                               m.gcnum  \n" +
                "                                                          from (select dep.bmmc name,\n" +
                "                                                                       dep.glbm,\n" +
                "                                                                       dep.sjbm,\n" +
                "                                                                       avg(dss.s) gcvalue \n" +
                "                                                                    \n" +
                "                                                                       ,sum(dss.gcnum) gcnum \n" +
                "                                                                   from frm_t_department dep\n" +
                "                                                                  left join dev_t_base dev\n" +
                "                                                                    on dep.glbm = dev.glbm\n" +
                "                                                                  left join (select ds.sbxh,\n" +
                "                                                                                   count(ds.gcbh) gcnum,\n" +
                "                                                                                   avg(ds.hjsj -\n" +
                "                                                                                       ds.gcsj) * 24 * 60 * 60 s from ds_tfcpass   ds \n" +
                "                                                                               where ds.gcsj >=to_date('"+startTime+"','yyyy-mm-dd hh24:mi:ss')                                                                               \n" +
                "                                           and ds.gcsj <= to_date('"+endTime+"','yyyy-mm-dd hh24:mi:ss')group by ds.sbxh) dss on dss.sbxh = dev.sbbh where dss.s is not null group by dep.bmmc, dep.sjbm, dep.glbm) m where m.gcvalue is not null group by m.sjbm, m.glbm,                   \n" +
                "                                           m.name,m.gcvalue,m.gcnum) zd  on frm.glbm = (case when zd.glbm like '%0000%' or zd.glbm like '%0010%' then zd.glbm else zd.sjbm end) group by frm.bmmc, frm.glbm) zd                                    \n" +
                "                                           on dep.bmbh = zd.glbm)order by value asc) where glbm !='513400000000' and glbm!='513407000000' and glbm!='513409000000' and glbm !='513412000000'\n" +
                "                                            and glbm !='513414000000'  and glbm !='513415000000' and glbm !='513408000000' and glbm !='513410000000' and glbm !='513498000000' and glbm!='513413000000'";
        Sql sqls = Sqls.create(sql1);
        sqls.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection connection, ResultSet rs, Sql sql) throws SQLException {
                List<DsTSjlztemp> lists = new ArrayList<DsTSjlztemp>();
                while (rs.next()){
                    DsTSjlztemp bp = new DsTSjlztemp();
                    bp.setId(UUID.randomUUID().toString().replace("-","").substring(0,19));
                    bp.setName(rs.getString("name"));
                    bp.setValue(rs.getString("value"));
                    bp.setGcnum(rs.getString("gcnum"));
                    bp.setGcvalue(rs.getString("gcvalue"));
                    bp.setGlbm(rs.getString("glbm"));
                    bp.setBmmc(rs.getString("bmmc"));
                    lists.add(bp);
                }
                return lists;
            }
        });
        dao.execute(sqls);



        String sql2 =   "select    decode(trunc(value, 1), '', -10000000, trunc(value, 1)) wfvalue,\n" +
                "                           gcnum wfnum    from (select glbm,bmmc name,zdgcvalue value,zdgcnum   gcnum from (select *from (select glbm bmbh\n" +
                "               from frm_t_department dep                         \n" +
                "               where glbm like '%0000'                                         \n" +
                "               or glbm like '%0010'                                         \n" +
                "               start with glbm = '513400000000'                        \n" +
                "               connect by prior glbm = sjbm) dep                  \n" +
                "               left join (select frm.bmmc,                                   \n" +
                "               frm.glbm,                                   \n" +
                "               avg(zd.gcvalue) zdgcvalue,                                    \n" +
                "               sum(gcnum) zdgcnum                                \n" +
                "               from frm_t_department frm                              \n" +
                "               left join (select m.name,                                               \n" +
                "               m.sjbm,                                               \n" +
                "               m.glbm,                                               \n" +
                "               m.gcvalue,                                               \n" +
                "               m.gcnum                                            \n" +
                "               from (select dep.bmmc name,                                                       \n" +
                "               dep.glbm,                                                       \n" +
                "               dep.sjbm,                                                       \n" +
                "               avg(dss.s) gcvalue                                                                                                            \n" +
                "               ,sum(dss.gcnum) gcnum                                                    \n" +
                "               from frm_t_department dep                                                  \n" +
                "               left join dev_t_base dev                                                    \n" +
                "               on dep.glbm = dev.glbm                                                  \n" +
                "               left join (select ds.sbxh,                                                                   \n" +
                "               count(ds.gcbh) gcnum,                                                                   \n" +
                "               avg(ds.hjsj -                                                                       \n" +
                "               ds.gcsj) * 24 * 60 * 60 s                                                                                                                                                                                               \n" +
                "               from ds_violation   ds                                                                                                                             \n" +
                "               where ds.gcsj >=to_date('"+startTime+"','yyyy-mm-dd hh24:mi:ss')                                                               \n" +
                "               and ds.gcsj <=to_date('"+endTime+"','yyyy-mm-dd hh24:mi:ss')                                                              \n" +
                "               group by ds.sbxh) dss                                                    \n" +
                "               on dss.sbxh = dev.sbbh                                                 \n" +
                "               where dss.s is not null                                                 \n" +
                "               group by dep.bmmc,                                                          \n" +
                "               dep.sjbm,                                                          \n" +
                "               dep.glbm) m                                         \n" +
                "               where m.gcvalue is not null                                         \n" +
                "               group by m.sjbm,                                                  \n" +
                "               m.glbm,                                                  \n" +
                "               m.name,                                                  \n" +
                "               m.gcvalue,                                                  \n" +
                "               m.gcnum                                                \n" +
                "               ) zd                                \n" +
                "               on frm.glbm = (case                                     \n" +
                "               when zd.glbm like '%0000%' or                                          \n" +
                "               zd.glbm like '%0010%' then                                      \n" +
                "               zd.glbm                                     \n" +
                "               else                                      \n" +
                "               zd.sjbm                                 \n" +
                "               end)                             \n" +
                "               group by frm.bmmc, frm.glbm) zd                    \n" +
                "               on dep.bmbh = zd.glbm)         order by value asc)  where glbm !='513400000000' and glbm!='513407000000' and glbm!='513409000000' and glbm !='513412000000'\n" +
                "                                            and glbm !='513414000000'  and glbm !='513415000000' and glbm !='513408000000' and glbm !='513410000000' and glbm !='513498000000' and glbm!='513413000000';" ;
        Sql sqls2 = Sqls.create(sql2);
        sqls2.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection connection, ResultSet rs, Sql sql) throws SQLException {
                List<DsTSjlztemp> lists = new ArrayList<DsTSjlztemp>();
                while (rs.next()){
                    DsTSjlztemp bp = new DsTSjlztemp();
                    bp.setId(UUID.randomUUID().toString().replace("-","").substring(0,19));
                    bp.setWfnum(rs.getString("wfnum"));
                    bp.setWfvalue(rs.getString("wfvalue"));
                    lists.add(bp);
                }
                return lists;
            }
        });
        dao.execute(sqls2);
        List<DsTSjlztemp> listgc =  sqls.getList(DsTSjlztemp.class);
        List<DsTSjlztemp> listwf    = sqls2.getList(DsTSjlztemp.class);
        List<DsTSjlztemp> listAll = new ArrayList<DsTSjlztemp>();
        for(int i=0;i<listgc.size();i++){
            DsTSjlztemp d = new DsTSjlztemp();
            d.setBmmc(listgc.get(i).getBmmc());
            d.setGcnum(listgc.get(i).getGcnum());
            d.setGcvalue(listgc.get(i).getGcvalue());
            d.setGlbm(listgc.get(i).getGlbm());
            d.setName(listgc.get(i).getName());
            d.setId(UUID.randomUUID().toString().replace("-","").substring(12));
            d.setWfnum(listwf.get(i).getWfnum());
            d.setWfvalue(listwf.get(i).getWfvalue());
            d.setValue(listgc.get(i).getValue());
            listAll.add(d);
        }
        return listAll;
    }
    public List<DsTGcqxtemp> getGcqx(Dao dao){
        String sql = "select   l sj,decode(idx,'','gc',idx) idx,decode(gczl,'',0,gczl) gczl from (select * from (select level-1 l from dual connect by level <=24) gclv left join \n" +
                "                (select 'gc' idx,count(gcbh) gczl,to_char(gcsj,'hh24') sj from ds_tfcpass where \n" +
                "gcsj> to_date(to_char(sysdate,'yyyy/mm/dd'),'yyyy/mm/dd')and  gcsj<sysdate group by to_char(gcsj,'hh24') order by to_char(gcsj,'hh24') desc ) gcList \n" +
                "on gclv.l = gcList.sj order by gclv.l  asc) \n" +
                "union \n" +
                "select l,decode(idx,'','wf',idx)idx,decode(gczl,'',0,gczl) gczl from (select level-1 l from dual connect by level <=24) wflv left join \n" +
                "        (select 'wf' idx,count(gcbh) gczl,to_char(gcsj,'hh24') sj from ds_violation where \n" +
                "gcsj> to_date(to_char(sysdate,'yyyy/mm/dd'),'yyyy/mm/dd')and  gcsj<sysdate group by to_char(gcsj,'hh24') order by to_char(gcsj,'hh24') desc ) gcList \n" +
                "on wflv.l = gcList.sj \n" +
                "union \n" +
                "select l,decode(idx,'','gps',idx)idx,decode(gczl,'',0,gczl) gczl from (select level-1 l from dual connect by level <=24) wflv left join \n" +
                "        (select 'wf' idx,count(lsh) gczl,to_char(sbsj,'hh24') sj from ds_t_policecarlocation where \n" +
                "sbsj> to_date(to_char(sysdate,'yyyy/mm/dd'),'yyyy/mm/dd')and  sbsj<sysdate group by to_char(sbsj,'hh24') order by to_char(sbsj,'hh24') desc ) gcList \n" +
                "on wflv.l = gcList.sj ";
        Sql sqls = Sqls.create(sql);
        sqls.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection connection, ResultSet rs, Sql sql) throws SQLException {
                List<DsTGcqxtemp> lists = new ArrayList<DsTGcqxtemp>();
                while (rs.next()){
                    DsTGcqxtemp bp = new DsTGcqxtemp();
                    bp.setId(UUID.randomUUID().toString().replace("-","").substring(0,19));
                    bp.setGczl(rs.getString("gczl"));
                    bp.setIdx(rs.getString("idx"));
                    bp.setSj(rs.getString("sj"));
                    lists.add(bp);
                }
                return lists;
            }
        });
        dao.execute(sqls);
        return sqls.getList(DsTGcqxtemp.class);
    }
    public List<DsTGczstemp> getGczs(Dao dao){
        String sql =" select idx,gczl from (\n" +
                "select idx,gczl from (select 'gc'idx, \n" +
                "trunc(decode(avg(t.hjsj - t.gcsj)*24*60*60,'',0.0,avg(t.hjsj - t.gcsj) * 24 * 60 * 60),1) \n" +
                "gczl from ds_tfcpass t where gcsj >to_date(to_char(sysdate,'yyyy/mm/dd'),'yyyy/mm/dd')\n" +
                " and gcsj< sysdate ) union select 'wf' idx,trunc(decode(avg(t.hjsj - t.gcsj)*24*60*60,'',0.0\n" +
                ",avg(t.hjsj - t.gcsj) * 24 * 60 * 60),1) wfzl from ds_violation t where gcsj > \n" +
                "to_date(to_char(sysdate,'yyyy/mm/dd'),'yyyy/mm/dd')  and gcsj \n" +
                "<sysdate union select 'gps' idx,  trunc(decode(avg(t.hjsj - t.sbsj)*24*60*60,'',0.0\n" +
                ",avg(t.hjsj - t.sbsj) * 24 * 60 * 60),1) gpszl from ds_t_policecarlocation t \n" +
                "where sbsj >to_date(to_char(sysdate,'yyyy/mm/dd'),'yyyy/mm/dd')and sbsj <sysdate ) ";
        Sql sqls = Sqls.create(sql);
        sqls.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection connection, ResultSet rs, Sql sql) throws SQLException {
                List<DsTGczstemp> lists = new ArrayList<DsTGczstemp>();
                while (rs.next()){
                    DsTGczstemp bp = new DsTGczstemp();
                    bp.setId(UUID.randomUUID().toString().replace("-","").substring(0,19));
                    bp.setGczl(rs.getString("gczl"));
                    bp.setIdx(rs.getString("idx"));
                    lists.add(bp);
                }
                return lists;
            }
        });
        dao.execute(sqls);
        return sqls.getList(DsTGczstemp.class);
    }
}
