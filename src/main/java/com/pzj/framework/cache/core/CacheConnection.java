package com.pzj.framework.cache.core;

/**
 * Created by Administrator on 2017-1-9.
 */
public interface CacheConnection {
    <T> T execute(CacheStatement statement);
}
