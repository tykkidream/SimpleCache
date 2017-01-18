package com.pzj.framework.cache.core;

import java.util.Map;

/**
 * Created by Administrator on 2017-1-12.
 */
public interface Serializer<T> {
    T descrialize(String cacheValue);

    byte[] serialize(Object obj);


    T descrializeMap(Map<byte[], byte[]> cacheValue);

    Map<byte[], byte[]> serializeMap(T obj);
}
