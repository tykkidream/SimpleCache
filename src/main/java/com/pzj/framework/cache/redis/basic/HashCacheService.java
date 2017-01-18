package com.pzj.framework.cache.redis.basic;

import com.pzj.framework.cache.core.Connection;
import com.pzj.framework.cache.core.Statement;
import redis.clients.jedis.Jedis;

import java.util.*;

/**
 * Created by Administrator on 2016-12-28.
 */
public class HashCacheService extends AbstractCacheService {
    public HashCacheService(Connection connection){
        super(connection);
    }

    public Map<String, String> get(final String key) {
        return connection.execute(new Statement() {
            @Override
            public Map<String, String> evaluate(Jedis jedis) {
                return jedis.hgetAll(key);
            }
        });
    }

    public Map<byte[], byte[]> get(final byte[] key) {
        return connection.execute(new Statement() {
            @Override
            public Map<byte[], byte[]> evaluate(Jedis jedis) {
                return jedis.hgetAll(key);
            }
        });
    }

    public String get(final String key, final String field) {
        return connection.execute(new Statement() {
            @Override
            public String evaluate(Jedis jedis) {
                return jedis.hget(key, field);
            }
        });
    }

    public Map<String, String> get(final String key, final String... fields) {
        List<String> hmget =connection.execute(new Statement() {
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

    public Map<byte[], byte[]> get(final byte[] key, final byte[] ... fields) {
        List<byte[]> hmget =connection.execute(new Statement() {
            @Override
            public List<byte[]> evaluate(Jedis jedis) {
                return jedis.hmget(key, fields);
            }
        });

        if (hmget == null || hmget.isEmpty()) {
            return null;
        }

        HashMap<byte[], byte[]> map = new HashMap<>(hmget.size());

        for (int i = 0; i < fields.length; i++){
            map.put(fields[i], hmget.get(i));
        }

        return map;
    }

    public void del(final String key, final String field) {
        connection.execute(new Statement() {
            @Override
            public Long evaluate(Jedis jedis) {
                return jedis.hdel(key, field);
            }
        });

    }

    public void del(final String key, final String... fields) {
        connection.execute(new Statement() {
            @Override
            public Long evaluate(Jedis jedis) {
                return jedis.hdel(key, fields);
            }
        });
    }

    public void set(final String key, final String field, final String value) {
        connection.execute(new Statement() {
            @Override
            public Long evaluate(Jedis jedis) {
                return jedis.hset(key, field, value);
            }
        });
    }

    public void set(final String key, final Map<String, String> fieldValues) {
        connection.execute(new Statement() {
            @Override
            public String evaluate(Jedis jedis) {
                return jedis.hmset(key, fieldValues);
            }
        });
    }

    public void set(final byte[] key, final Map<byte[], byte[]> fieldValues) {
        connection.execute(new Statement() {
            @Override
            public String evaluate(Jedis jedis) {
                return jedis.hmset(key, fieldValues);
            }
        });
    }

    public Set<String> keys(final String key) {
        return connection.execute(new Statement() {
            @Override
            public Set<String> evaluate(Jedis jedis) {
                return jedis.hkeys(key);
            }
        });
    }

    public void incrNumber(final String key, final String field, final long value) {
        connection.execute(new Statement() {
            @Override
            public Long evaluate(Jedis jedis) {
                return jedis.hincrBy(key, field, value);
            }
        });
    }

    public void incrNumber(final String key, final String field, final double value) {
        connection.execute(new Statement() {
            @Override
            public Double evaluate(Jedis jedis) {
                return jedis.hincrByFloat(key, field, value);
            }
        });
    }

    public void setnx(final String key, final String field, final String value) {
        connection.execute(new Statement() {
            @Override
            public Long evaluate(Jedis jedis) {
                return jedis.hsetnx(key, field, value);
            }
        });
    }

    public boolean exist(final String key, final String field) {
        return connection.execute(new Statement() {
            @Override
            public Boolean evaluate(Jedis jedis) {
                return jedis.hexists(key, field);
            }
        });
    }

    public long len(final String key) {
        return connection.execute(new Statement() {
            @Override
            public Long evaluate(Jedis jedis) {
                return jedis.hlen(key);
            }
        });
    }
}

