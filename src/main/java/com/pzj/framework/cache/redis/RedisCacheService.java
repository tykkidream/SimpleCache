package com.pzj.framework.cache.redis;

import com.pzj.framework.cache.core.CacheKey;
import com.pzj.framework.cache.core.CacheService;
import com.pzj.framework.cache.redis.basic.HashCacheService;
import com.pzj.framework.cache.redis.basic.ListCacheService;
import com.pzj.framework.cache.redis.basic.QueueCacheService;
import com.pzj.framework.cache.redis.basic.StringCacheService;

import java.util.*;

/**
 * 精简版缓存操作类
 * Created by Administrator on 2016-12-28.
 */
public class RedisCacheService implements CacheService{
    private StringCacheService stringCacheService;
    private HashCacheService hashCacheService;
    private QueueCacheService queueCacheService;
    private ListCacheService listCacheService;

    public String strGet(CacheKey key) {
        return stringCacheService.get(key.key());
    }

    public List<String> strGet(CacheKey... keys) {
        return stringCacheService.get(convertToStringKey(keys));
    }

    public void strDecrNumber(CacheKey key) {
        stringCacheService.decrNumber(key.key());
    }

    public void strDecrNumber(CacheKey key, long value) {
        stringCacheService.decrNumber(key.key(), value);
    }

    public void strIncrNumber(CacheKey key) {
        stringCacheService.incrNumber(key.key());
    }

    public void strIncrNumber(CacheKey key, long value) {
        stringCacheService.incrNumber(key.key(), value);
    }

    public void strSet(CacheKey key, String value) {
        stringCacheService.set(key.key(), value);
    }

    public void strSet(Map<CacheKey, String> keyValues) {
        stringCacheService.set(convertToStrintKey(keyValues));
    }

    private HashMap<String, String> convertToStrintKey(Map<CacheKey, String> keyValues){
        if (keyValues == null)
            return null;

        HashMap<String, String> result = new HashMap<>(keyValues.size());
        Iterator<Map.Entry<CacheKey, String>> iterator = keyValues.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<CacheKey, String> next = iterator.next();
            result.put(next.getKey().key(), next.getValue());
        }
        return result;
    }

    public void strSetnx(CacheKey key, String value) {
        stringCacheService.setnx(key.key(), value);
    }

    public Map<String,String> mapGet(CacheKey key) {
        return hashCacheService.get(key.key());
    }

    public String mapGet(CacheKey key, String field) {
        return hashCacheService.get(key.key(), field);
    }

    public Map<String,String> mapGet(CacheKey key, String... fields) {
        return hashCacheService.get(key.key(), fields);
    }

    public void mapDel(CacheKey key, String field) {
        hashCacheService.del(key.key(), field);
    }

    public void mapDel(CacheKey key, String... fields) {
        hashCacheService.del(key.key(), fields);
    }

    public void mapSet(CacheKey key, String field, String value) {
        hashCacheService.set(key.key(), field, value);
    }

    public void mapSet(CacheKey key, Map<String, String> fieldValues) {
        hashCacheService.set(key.key(), fieldValues);
    }

    public Set<String> mapKeys(CacheKey key) {
        return hashCacheService.keys(key.key());
    }

    @Override
    public String listGet(CacheKey key, int index) {
        return listCacheService.get(key.key(), index);
    }

    @Override
    public List<String> listGet(CacheKey key, int start, int stop) {
        return listCacheService.get(key.key(), start, stop);
    }

    @Override
    public Long listSize(CacheKey key) {
        return listCacheService.size(key.key());
    }

    @Override
    public void listRemove(CacheKey key, String value) {
        listCacheService.remove(key.key(), value);
    }

    @Override
    public void listSet(CacheKey key, int index, String value) {
        listCacheService.set(key.key(), index, value);
    }

    @Override
    public void listTrim(CacheKey key, int start, int stop) {
        listCacheService.trim(key.key(), start, stop);
    }

    @Override
    public String queuePopFromLeft(CacheKey key) {
        return queueCacheService.popFromLeft(key.key());
    }

    @Override
    public String queuePopFromRight(CacheKey key) {
        return queueCacheService.popFromRight(key.key());
    }

    @Override
    public List<String> queueBlockPopFromLeft(CacheKey... keys) {
        return queueCacheService.blockPopFromLeft(convertToStringKey(keys));
    }

    private String[] convertToStringKey(CacheKey ... keys){
        return CacheKey.keysArray(keys);
    }

    @Override
    public List<String> queueBlockPopFromLeft(int timeout, CacheKey... keys) {
        return queueCacheService.blockPopFromLeft(timeout, convertToStringKey(keys));
    }

    @Override
    public List<String> queueBlockPopFromRight(CacheKey ... keys) {
        return queueCacheService.blockPopFromRight(convertToStringKey(keys));
    }

    @Override
    public List<String> queueBlockPopFromRight(int timeout, CacheKey... keys) {
        return queueCacheService.blockPopFromRight(timeout, convertToStringKey(keys));
    }

    @Override
    public void queuePushToLeft(CacheKey key, String... values) {
        queueCacheService.pushToLeft(key.key(), values);
    }

    @Override
    public void queuePushToRight(CacheKey key, String... values) {
        queueCacheService.pushToRight(key.key(), values);
    }

    public void setStringCacheService(StringCacheService stringCacheService) {
        this.stringCacheService = stringCacheService;
    }

    public void setHashCacheService(HashCacheService hashCacheService) {
        this.hashCacheService = hashCacheService;
    }

    public void setQueueCacheService(QueueCacheService queueCacheService) {
        this.queueCacheService = queueCacheService;
    }

    public void setListCacheService(ListCacheService listCacheService) {
        this.listCacheService = listCacheService;
    }
}
