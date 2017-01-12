package com.pzj.framework.cache.core;

/**
 * Created by Administrator on 2017-1-13.
 */
public interface CacheObjectService {
    <T> T get(String key, Class<T> dataClass);

    <T> T get(String key, Serializer serializer);

    void set(String key, Object value);

    void set(String key, Serializer serializer, Object value);

    <T> T hget(String key, Class<T> dataClass);

    <T> T hget(String key, Class<T> dataClass, String... fields);

    <T> T hget(String key, Serializer serializer);

    <T> T hget(String key, Serializer serializer, String... fields);

    void hset(String key, Object value);

    void hset(String key, Serializer serializer, Object value);
}
