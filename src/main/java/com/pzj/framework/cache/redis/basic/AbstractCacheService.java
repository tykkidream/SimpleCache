package com.pzj.framework.cache.redis.basic;

import com.pzj.framework.cache.core.CacheConnection;

/**
 * Created by Administrator on 2016-12-30.
 */
abstract class AbstractCacheService {
    protected CacheConnection connection;

    public AbstractCacheService(CacheConnection connection){
        setConnection(connection);
    }

    public CacheConnection getConnection() {
        return connection;
    }

    public void setConnection(CacheConnection connection) {
        this.connection = connection;
    }
}
