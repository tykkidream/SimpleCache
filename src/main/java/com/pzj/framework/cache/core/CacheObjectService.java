package com.pzj.framework.cache.core;

/**
 * Created by Administrator on 2017-1-13.
 */
public interface CacheObjectService {
    <T> T get(String key, Class<T> dataClass);

    <T> T get(String key, Serializer<T> serializer);

    <T> void set(String key, T value);

    <T> void set(String key, Serializer<T> serializer, T value);

    <T> T hget(String key, Class<T> dataClass);

    <T> T hget(String key, Serializer<T> serializer);

    <T> T hget(String key, Class<T> dataClass, String... fields);

    <T> T hget(String key, Serializer<T> serializer, String... fields);

    <T> void hset(String key, T value);

    <T> void hset(String key, Serializer<T> serializer, T value);
}
