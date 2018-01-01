package com.tykkidream.cache.redis;

import com.tykkidream.cache.core.Connection;

/**
 * Created by Administrator on 2016-12-30.
 */
public abstract class AbstractService {
    protected Connection connection;

    public AbstractService(Connection connection){
        setConnection(connection);
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
