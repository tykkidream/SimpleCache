package com.pzj.framework.cache.redis;

import com.pzj.framework.cache.core.CacheConnection;
import com.pzj.framework.cache.core.CacheStatement;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by Administrator on 2017-1-9.
 */
public class RedisPoolConnection implements CacheConnection {
    private JedisPool jedisPool;

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    @Override
    public <T> T execute(CacheStatement statement) {
        Jedis jedis = jedisPool.getResource();
        T result = statement.evaluate(jedis);
        jedis.close();
        return result;
    }
}
