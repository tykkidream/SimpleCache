package com.pzj.framework.cache.core;

/**
 * Created by Administrator on 2016-12-28.
 */
public interface CacheContext {
    CacheService getCacheService();
    CacheObjectService getCacheObjectService();
    LockService getLockService();
}
