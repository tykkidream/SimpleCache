package com.pzj.framework.cache.redis;

import com.pzj.framework.cache.core.Connection;
import com.pzj.framework.cache.core.Statement;
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
