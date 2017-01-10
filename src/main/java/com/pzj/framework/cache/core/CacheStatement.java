package com.pzj.framework.cache.core;

import redis.clients.jedis.Jedis;

/**
 * Created by Administrator on 2017-1-9.
 */
public interface CacheStatement {
    <T> T evaluate(Jedis jedis);
}
