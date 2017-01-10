package com.pzj.framework.cache.redis;

import com.pzj.framework.cache.core.CacheConnection;
import com.pzj.framework.cache.core.CacheStatement;
import redis.clients.jedis.Jedis;

/**
 * Created by Administrator on 2017-1-9.
 */
public class RedisDefaultConnection implements CacheConnection {
    private Jedis jedis;

    @Override
    public <T> T execute(CacheStatement statement) {
        return statement.evaluate(jedis);
    }
}
