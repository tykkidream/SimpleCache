package com.tykkidream.cache.core;

import redis.clients.jedis.Jedis;

/**
 * Created by Administrator on 2017-1-9.
 */
public interface Statement {
    <T> T evaluate(Jedis jedis);
}
