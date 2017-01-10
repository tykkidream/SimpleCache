package com.pzj.framework.cache.redis;

import com.pzj.framework.cache.core.CacheConnection;
import com.pzj.framework.cache.core.CacheStatement;

/**
 * Created by Administrator on 2017-1-9.
 */
public class RedisPoolShardedConnection implements CacheConnection {
    @Override
    public <T> T execute(CacheStatement statement) {
        return null;
    }
    /*private ShardedJedisPool shardedJedisPool;


    @Override
    public <T> T execute(CacheStatement statement) {
        ShardedJedis jedis = shardedJedisPool.getResource();
        T result = statement.evaluate(jedis);
        jedis.close();
        return result;
    }*/
}