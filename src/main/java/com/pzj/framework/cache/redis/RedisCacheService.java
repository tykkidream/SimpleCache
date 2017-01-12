package com.pzj.framework.cache.redis;

import com.pzj.framework.cache.core.CacheService;
import com.pzj.framework.cache.core.KeyException;
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
    private static String KEY_RULE = "^[a-zA-Z0-9]*:[a-zA-Z0-9:]*$";

    private StringCacheService stringCacheService;
    private HashCacheService hashCacheService;
    private QueueCacheService queueCacheService;
    private ListCacheService listCacheService;

    private void checkKey(String... keys){
        if (keys == null){
            throw new KeyException("缓存 keys 不能为空。");
        }
        for (int i = 0; i < keys.length; i++){
            checkKey(keys[i]);
        }
    }

    private void checkKey(String key){
        if (key == null){
            throw new KeyException("缓存 key 不能为空。");
        }
        if (!key.matches(KEY_RULE)){
            throw new KeyException("缓存 key 不能符合规则，应由多个部分组成，并以英文冒号分隔，且至少要有一个冒号。");
        }
    }

    public String strGet(String key) {
        checkKey(key);
        return stringCacheService.get(key);
    }

    public List<String> strGet(String... keys) {
        checkKey(keys);
        return stringCacheService.get(keys);
    }

    public void strDecrNumber(String key) {
        checkKey(key);
        stringCacheService.decrNumber(key);
    }

    public void strDecrNumber(String key, long value) {
        checkKey(key);
        stringCacheService.decrNumber(key, value);
    }

    public void strIncrNumber(String key) {
        checkKey(key);
        stringCacheService.incrNumber(key);
    }

    public void strIncrNumber(String key, long value) {
        checkKey(key);
        stringCacheService.incrNumber(key, value);
    }

    public void strSet(String key, String value) {
        checkKey(key);
        stringCacheService.set(key, value);
    }

    public void strSet(Map<String, String> keyValues) {
        stringCacheService.set(convertToStrintKey(keyValues));
    }

    private HashMap<String, String> convertToStrintKey(Map<String, String> keyValues){
        if (keyValues == null)
            return null;

        HashMap<String, String> result = new HashMap<>(keyValues.size());
        Iterator<Map.Entry<String, String>> iterator = keyValues.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, String> next = iterator.next();
            checkKey(next.getKey());
            result.put(next.getKey(), next.getValue());
        }
        return result;
    }

    public void strSetnx(String key, String value) {
        checkKey(key);
        stringCacheService.setnx(key, value);
    }

    public Map<String,String> mapGet(String key) {
        checkKey(key);
        return hashCacheService.get(key);
    }

    public String mapGet(String key, String field) {
        checkKey(key);
        return hashCacheService.get(key, field);
    }

    public Map<String,String> mapGet(String key, String... fields) {
        checkKey(key);
        return hashCacheService.get(key, fields);
    }

    public void mapDel(String key, String field) {
        checkKey(key);
        hashCacheService.del(key, field);
    }

    public void mapDel(String key, String... fields) {
        checkKey(key);
        hashCacheService.del(key, fields);
    }

    public void mapSet(String key, String field, String value) {
        checkKey(key);
        hashCacheService.set(key, field, value);
    }

    public void mapSet(String key, Map<String, String> fieldValues) {
        checkKey(key);
        hashCacheService.set(key, fieldValues);
    }

    @Override
    public String listGet(String key, int index) {
        checkKey(key);
        return listCacheService.get(key, index);
    }

    @Override
    public Long listSize(String key) {
        checkKey(key);
        return listCacheService.size(key);
    }

    @Override
    public void listRemove(String key, String value) {
        checkKey(key);
        listCacheService.remove(key, value);
    }

    @Override
    public void listSet(String key, int index, String value) {
        checkKey(key);
        listCacheService.set(key, index, value);
    }

    @Override
    public String queuePopFromLeft(String key) {
        checkKey(key);
        return queueCacheService.popFromLeft(key);
    }

    @Override
    public String queuePopFromRight(String key) {
        checkKey(key);
        return queueCacheService.popFromRight(key);
    }

    @Override
    public List<String> queueBlockPopFromLeft(String... keys) {
        checkKey(keys);
        return queueCacheService.blockPopFromLeft(keys);
    }


    @Override
    public List<String> queueBlockPopFromLeft(int timeout, String... keys) {
        checkKey(keys);
        return queueCacheService.blockPopFromLeft(timeout, keys);
    }

    @Override
    public List<String> queueBlockPopFromRight(String ... keys) {
        checkKey(keys);
        return queueCacheService.blockPopFromRight(keys);
    }

    @Override
    public List<String> queueBlockPopFromRight(int timeout, String... keys) {
        checkKey(keys);
        return queueCacheService.blockPopFromRight(timeout, keys);
    }

    @Override
    public void queuePushToLeft(String key, String... values) {
        checkKey(key);
        queueCacheService.pushToLeft(key, values);
    }

    @Override
    public void queuePushToRight(String key, String... values) {
        checkKey(key);
        queueCacheService.pushToRight(key, values);
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
