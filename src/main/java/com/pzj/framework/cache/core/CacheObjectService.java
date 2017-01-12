package com.pzj.framework.cache.core;

/**
 * Created by Administrator on 2017-1-13.
 */
public interface CacheObjectService {
    enum CacheObjectMode {
        Single,
        IncrMap,
        FullMap
    }

    <T> T get(CacheKey key, Class<T> dataClass);

    <T> T hget(CacheKey key, Class<T> dataClass, String... fields);

    <T> T get(CacheKey key, Serializer serializer);

    <T> T get(CacheKey key, Serializer serializer, String... fields);

    void set(CacheKey key, CacheObjectMode mode, Object value);

    void set(CacheKey key, CacheObjectMode mode, Serializer serializer, Object value);
}
