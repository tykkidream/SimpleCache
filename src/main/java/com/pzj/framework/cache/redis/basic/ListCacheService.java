package com.pzj.framework.cache.redis.basic;

import com.pzj.framework.cache.core.Connection;
import com.pzj.framework.cache.core.Statement;
import com.pzj.framework.cache.redis.AbstractService;
import redis.clients.jedis.BinaryClient;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * Created by Administrator on 2016-12-29.
 */
public class ListCacheService extends AbstractService {
    public ListCacheService(Connection connection){
        super(connection);
    }

    public String get(final String key, final int index) {
        return connection.execute(new Statement() {
            @Override
            public String evaluate(Jedis jedis) {
                return jedis.lindex(key, index);
            }
        });
    }

    public List<String> get(final String key, final int start, final int stop) {
        return connection.execute(new Statement() {
            @Override
            public List<String> evaluate(Jedis jedis) {
                return jedis.lrange(key, start, stop);
            }
        });
    }

    public String getLast(final String key, final int index) {
        if (index <= 0)
            return null;
        return connection.execute(new Statement() {
            @Override
            public String evaluate(Jedis jedis) {
                return jedis.lindex(key, -1-index);
            }
        });
    }

    public void addBefore(final String key, final String pivot, final String value) {
        connection.execute(new Statement() {
            @Override
            public Long evaluate(Jedis jedis) {
                return jedis.linsert(key, BinaryClient.LIST_POSITION.BEFORE, pivot, value);
            }
        });
    }

    public void addAfter(final String key, final String pivot, final String value) {
        connection.execute(new Statement() {
            @Override
            public Long evaluate(Jedis jedis) {
                return jedis.linsert(key, BinaryClient.LIST_POSITION.AFTER, pivot, value);
            }
        });
    }

    public Long size(final String key) {
        return connection.execute(new Statement() {
            @Override
            public Long evaluate(Jedis jedis) {
                return jedis.llen(key);
            }
        });
    }

    public Long remove(final String key, final String value) {
        return connection.execute(new Statement() {
            @Override
            public Long evaluate(Jedis jedis) {
                return jedis.lrem(key, 0, value);
            }
        });
    }

    public void remove(final String key, final String value, final int count) {
        connection.execute(new Statement() {
            @Override
            public Long evaluate(Jedis jedis) {
                return jedis.lrem(key, count, value);
            }
        });
    }

    public void removeFromLast(final String key, final String value, final int count) {
        if (count <= 0)
            return;
        connection.execute(new Statement() {
            @Override
            public Long evaluate(Jedis jedis) {
                return jedis.lrem(key, -count, value);
            }
        });
    }

    public void set(final String key, final int index, final String value) {
        connection.execute(new Statement() {
            @Override
            public String evaluate(Jedis jedis) {
                return jedis.lset(key, index, value);
            }
        });

    }

    public void trim(final String key, final int start, final int stop) {
        connection.execute(new Statement() {
            @Override
            public String evaluate(Jedis jedis) {
                return jedis.ltrim(key, start, stop);
            }
        });
    }
}