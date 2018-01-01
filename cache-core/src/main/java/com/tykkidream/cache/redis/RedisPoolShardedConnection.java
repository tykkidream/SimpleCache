package com.tykkidream.cache.redis;

import com.tykkidream.cache.core.Connection;
import com.tykkidream.cache.core.Statement;

/**
 * Created by Administrator on 2017-1-9.
 */
public class RedisPoolShardedConnection implements Connection {
    @Override
    public <T> T execute(Statement statement) {
        return null;
    }
    /*private ShardedJedisPool shardedJedisPool;


    @Override
    public <T> T execute(Statement statement) {
        ShardedJedis jedis = shardedJedisPool.getResource();
        T result = statement.evaluate(jedis);
        jedis.close();
        return result;
    }*/
}