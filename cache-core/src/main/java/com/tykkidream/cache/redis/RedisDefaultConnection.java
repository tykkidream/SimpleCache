package com.tykkidream.cache.redis;

import com.tykkidream.cache.core.Connection;
import com.tykkidream.cache.core.Statement;
import redis.clients.jedis.Jedis;

/**
 * Created by Administrator on 2017-1-9.
 */
public class RedisDefaultConnection implements Connection {
    private Jedis jedis;

    @Override
    public <T> T execute(Statement statement) {
        return statement.evaluate(jedis);
    }
}
