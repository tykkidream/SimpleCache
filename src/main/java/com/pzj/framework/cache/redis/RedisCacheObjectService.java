package com.pzj.framework.cache.redis;

import com.pzj.framework.cache.core.CacheKey;
import com.pzj.framework.cache.core.CacheObjectService;
import com.pzj.framework.cache.core.Serializer;
import com.pzj.framework.cache.redis.basic.HashCacheService;
import com.pzj.framework.cache.redis.basic.StringCacheService;

import java.util.Map;

/**
 * Created by Administrator on 2017-1-13.
 */
public class RedisCacheObjectService implements CacheObjectService {
    private StringCacheService stringCacheService;
    private HashCacheService hashCacheService;

    @Override
    public <T> T get(String key, Class<T> dataClass) {
        return get(key, new DefaultSerializer<>(dataClass));
    }

    @Override
    public <T> T get(String key, Serializer<T> serializer) {
        String value = stringCacheService.get(key);
        if (value != null){
            T object = serializer.descrialize(value);
            return object;
        }
        return null;
    }

    @Override
    public <T> void set(String key, T value) {
        set(key, new DefaultSerializer<>((Class<T>) (value.getClass())), value);
    }

    @Override
    public <T> void set(String key, Serializer<T> serializer, T value) {
        CacheKey.checkKey(key);
        if (value != null){
            byte[] data = serializer.serialize(value);
            stringCacheService.set(key, data);
        }
    }

    @Override
    public <T> T hget(String key, Class<T> dataClass) {
        Serializer<T> serializer = new DefaultSerializer<>(dataClass);
        return hget(key, serializer);
    }

    @Override
    public <T> T hget(String key, Serializer<T> serializer) {
        Map<byte[], byte[]> cache = hashCacheService.get(key.getBytes());
        return serializer.descrializeMap(cache);
    }

    @Override
    public <T> T hget(String key, Class<T> dataClass, String... fields) {
        Serializer<T> serializer = new DefaultSerializer<>(dataClass);
        Map<String, String> stringMap = hashCacheService.get(key, fields);
        return hget(key, serializer, fields);
    }

    @Override
    public <T> T hget(String key, Serializer<T> serializer, String... fields) {
        Map<byte[], byte[]> cache = hashCacheService.get(key.getBytes());
        return serializer.descrializeMap(cache);
    }

    @Override
    public <T> void hset(String key, T value) {
        Serializer<T> serializer = new DefaultSerializer<>((Class<T>) (value.getClass()));
        hset(key, serializer, value);
    }

    @Override
    public <T> void hset(String key, Serializer<T> serializer, T value) {
        CacheKey.checkKey(key);
        Map<byte[], byte[]> map = serializer.serializeMap(value);
        hashCacheService.set(key.getBytes(), map);
    }
}
