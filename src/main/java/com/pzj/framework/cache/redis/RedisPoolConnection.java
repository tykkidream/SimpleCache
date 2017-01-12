package com.pzj.framework.cache.redis;

import com.pzj.framework.cache.core.Connection;
import com.pzj.framework.cache.core.Statement;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by Administrator on 2017-1-9.
 */
public class RedisPoolConnection implements Connection {
    private JedisPool jedisPool;

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    @Override
    public <T> T execute(Statement statement) {
        Jedis jedis = jedisPool.getResource();
        T result = statement.evaluate(jedis);
        jedis.close();
        return result;
    }
}
