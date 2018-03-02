package com.bcht.its.service.impl;

import com.bcht.its.commons.beans.DeviceTemp;
import com.bcht.its.commons.beans.DsTfcpassJsonTemp;
import com.bcht.its.pojo.DsTJksqRefJk;
import com.bcht.its.pojo.Dstjk;
import com.bcht.its.pojo.Dstjksj;
import com.bcht.its.service.KeyValidationService;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/26.
 */
@Service
public class KeyValidationServiceImpl implements KeyValidationService {
    @Autowired
    private Dao dao;
    @Override
    public List<String> findAllJk() {
        List<String>  array  = new ArrayList<String>();
        List<Dstjksj> list =   dao.query(Dstjksj.class, Cnd.where("1","=","1"));
        if(list.size()<1){
        }else{
            for(Dstjksj ds : list){
                array.add(ds.getKey());
            }
        }
        return array;
    }

    @Override
    public List<Dstjksj> findAll() {
        return dao.query(Dstjksj.class, Cnd.where("1","=","1"));
    }

    @Override
    public Map<String, List<String>> findAllMethod() {
            dao.query(DsTJksqRefJk.class,Cnd.where("1","=","1"));
        return null;
    }

    @Override
    public Map<String,DeviceTemp> findAllDevs() {
        Sql sql = Sqls.create("select dtb.sbbh,dtb.sblx,cslb.jcsbbh,cslb.ldsbbh,cslb.lddwbh \n " +
                "from dev_t_base dtb left join ( \n" +
                "select * from (select sbbh bcsbbh,cslx,csz from dev_t_magic ) \n" +
                "pivot (max(csz) for cslx in \n" +
                "('sbbh' as wfsbbh,'jcsbbh' jcsbbh,'lddwbh' lddwbh,'ldsbbh' ldsbbh ))\n" +
                ") cslb on dtb.sbbh = cslb.bcsbbh  \n");
            Map<String,DeviceTemp> devTemps =new HashMap<String,DeviceTemp>();
        sql.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection connection, ResultSet resultSet, Sql sql) throws SQLException {
                while (resultSet.next()){
                    DeviceTemp temp = new DeviceTemp();
                    temp.setSbbh(resultSet.getString("sbbh"));
                    temp.setSblx(resultSet.getString("sblx"));
                    temp.setJcsbbh(resultSet.getString("jcsbbh"));
                    temp.setLdsbbh(resultSet.getString("ldsbbh"));
                    temp.setLddwbh(resultSet.getString("lddwbh"));
                    devTemps.put(temp.getSbbh(),temp);
                }
                return devTemps;
            }
        });
     dao.execute(sql);

        return devTemps;
    }


}
