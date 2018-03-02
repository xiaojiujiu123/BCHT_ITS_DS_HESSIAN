package com.bcht.its.service;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jiangxinxin on 2017/6/10.
 */
public interface IRedisService {

    public boolean set(String key, String value);

    public String get(String key);

    public boolean expire(String key, long expire);

    public <T> boolean setList(String key, List<T> list);

    public <T> List<T> getList(String key, Class<T> clz);

    public long lpush(String key, Object obj);

    public long rpush(String key, Object obj);

    public String lpop(String key);

}
