package com.pzj.framework.cache.redis.basic;

import com.pzj.framework.cache.core.CacheConnection;
import com.pzj.framework.cache.core.CacheStatement;
import redis.clients.jedis.Jedis;

import java.util.*;

/**
 * Created by Administrator on 2016-12-28.
 */
public class HashCacheService extends AbstractCacheService {
    public HashCacheService(CacheConnection connection){
        super(connection);
    }

    public Map<String, String> get(final String key) {
        return connection.execute(new CacheStatement() {
            @Override
            public Map<String, String> evaluate(Jedis jedis) {
                return jedis.hgetAll(key);
            }
        });
    }

    public String get(final String key, final String field) {
        return connection.execute(new CacheStatement() {
            @Override
            public String evaluate(Jedis jedis) {
                return jedis.hget(key, field);
            }
        });
    }

    public Map<String, String> get(final String key, final String... fields) {
        List<String> hmget =connection.execute(new CacheStatement() {
            @Override
            public List<String> evaluate(Jedis jedis) {
                return jedis.hmget(key, fields);
            }
        });

        if (hmget == null || hmget.isEmpty()) {
            return null;
        }

        HashMap<String, String> map = new HashMap<>(hmget.size());


        for (int i = 0; i < fields.length; i++){
            map.put(fields[i], hmget.get(i));
        }

        return map;
    }

    public void del(final String key, final String field) {
        connection.execute(new CacheStatement() {
            @Override
            public Long evaluate(Jedis jedis) {
                return jedis.hdel(key, field);
            }
        });

    }

    public void del(final String key, final String... fields) {
        connection.execute(new CacheStatement() {
            @Override
            public Long evaluate(Jedis jedis) {
                return jedis.hdel(key, fields);
            }
        });
    }

    public void set(final String key, final String field, final String value) {
        connection.execute(new CacheStatement() {
            @Override
            public Long evaluate(Jedis jedis) {
                return jedis.hset(key, field, value);
            }
        });
    }

    public void set(final String key, final Map<String, String> fieldValues) {
        connection.execute(new CacheStatement() {
            @Override
            public String evaluate(Jedis jedis) {
                return jedis.hmset(key, fieldValues);
            }
        });
    }

    public Set<String> keys(final String key) {
        return connection.execute(new CacheStatement() {
            @Override
            public Set<String> evaluate(Jedis jedis) {
                return jedis.hkeys(key);
            }
        });
    }

    public void incrNumber(final String key, final String field, final long value) {
        connection.execute(new CacheStatement() {
            @Override
            public Long evaluate(Jedis jedis) {
                return jedis.hincrBy(key, field, value);
            }
        });
    }

    public void incrNumber(final String key, final String field, final double value) {
        connection.execute(new CacheStatement() {
            @Override
            public Double evaluate(Jedis jedis) {
                return jedis.hincrByFloat(key, field, value);
            }
        });
    }

    public void setnx(final String key, final String field, final String value) {
        connection.execute(new CacheStatement() {
            @Override
            public Long evaluate(Jedis jedis) {
                return jedis.hsetnx(key, field, value);
            }
        });
    }

    public boolean exist(final String key, final String field) {
        return connection.execute(new CacheStatement() {
            @Override
            public Boolean evaluate(Jedis jedis) {
                return jedis.hexists(key, field);
            }
        });
    }

    public long len(final String key) {
        return connection.execute(new CacheStatement() {
            @Override
            public Long evaluate(Jedis jedis) {
                return jedis.hlen(key);
            }
        });
    }
}

