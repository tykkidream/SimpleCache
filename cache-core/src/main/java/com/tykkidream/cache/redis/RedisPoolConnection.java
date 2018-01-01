package com.tykkidream.cache.redis;

import com.tykkidream.cache.core.Connection;
import com.tykkidream.cache.core.Statement;
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
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return statement.evaluate(jedis);
        } catch (Throwable throwable){
            throw throwable;
        } finally {
            if (jedis != null){
                jedisPool.returnResource(jedis);
            }
        }
    }
}
