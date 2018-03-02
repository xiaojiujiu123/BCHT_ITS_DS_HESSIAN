package com.bcht.its.utils;

import org.nutz.dao.QueryResult;
import org.nutz.dao.pager.Pager;

import java.util.List;

/**
 * Created by ${zhaojinglong} on 2017/3/30 0030.
 */
public class BchtPages extends QueryResult {

    public BchtPages(List<?> list, Pager pager){
        super(list,pager);
    }

    public List<?> getRows(){
        return super.getList();

    }

    public int gettotal(){
    return  super.getPager().getRecordCount();
    }


}
