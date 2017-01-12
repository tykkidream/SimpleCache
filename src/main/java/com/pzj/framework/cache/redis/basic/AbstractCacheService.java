package com.pzj.framework.cache.redis.basic;

import com.pzj.framework.cache.core.Connection;

/**
 * Created by Administrator on 2016-12-30.
 */
abstract class AbstractCacheService {
    protected Connection connection;

    public AbstractCacheService(Connection connection){
        setConnection(connection);
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
