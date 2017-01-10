package com.pzj.framework.cache.redis.basic;

import com.pzj.framework.cache.core.CacheConnection;
import com.pzj.framework.cache.core.CacheStatement;
import redis.clients.jedis.Jedis;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016-12-28.
 */
public class StringCacheService extends AbstractCacheService {
    public StringCacheService(CacheConnection connection){
        super(connection);
    }

    public String get(final String key) {
        return connection.execute(new CacheStatement() {
            @Override
            public String evaluate(Jedis jedis) {
                return jedis.get(key);
            }
        });
    }

    public String get(final String key, final String newValue) {
        return connection.execute(new CacheStatement() {
            @Override
            public String evaluate(Jedis jedis) {
                return jedis.getSet(key, newValue);
            }
        });
    }

    public String get(final String key, final int start, final int stop) {
        return connection.execute(new CacheStatement() {
            @Override
            public String evaluate(Jedis jedis) {
                return jedis.getrange(key, start, stop);
            }
        });
    }

    public List<String> get(final String... keys) {
        return connection.execute(new CacheStatement() {
            @Override
            public List<String> evaluate(Jedis jedis) {
                return jedis.mget(keys);
            }
        });
    }

    public void decrNumber(final String key) {
        connection.execute(new CacheStatement() {
            @Override
            public Long evaluate(Jedis jedis) {
                return jedis.decr(key);
            }
        });
    }

    public void incrNumber(final String key) {
        connection.execute(new CacheStatement() {
            @Override
            public Long evaluate(Jedis jedis) {
                return jedis.incr(key);
            }
        });
    }

    public void set(final String key, final String value) {
        connection.execute(new CacheStatement() {
            @Override
            public String evaluate(Jedis jedis) {
                return jedis.set(key, value);
            }
        });
    }

    public void set(final Map<String, String> keyValues) {
        final String[] values = convertArray(keyValues);
        connection.execute(new CacheStatement() {
            @Override
            public String evaluate(Jedis jedis) {
                return jedis.mset(values);
            }
        });
    }

    private String[] convertArray(Map<String, String> keyValues) {
        String[] values = new String[keyValues.size() *2];
        Iterator<Map.Entry<String, String>> iterator = keyValues.entrySet().iterator();
        int i = 0;
        while (iterator.hasNext()){
            Map.Entry<String, String> next = iterator.next();
            values[i] = next.getKey();
            i++;
            values[i] = next.getValue();
            i++;
        }
        return values;
    }

    public void setnx(final String key, final String value) {
        connection.execute(new CacheStatement() {
            @Override
            public Long evaluate(Jedis jedis) {
                return jedis.setnx(key, value);
            }
        });
    }

    public void set(final String key, final String value, final int start) {
        connection.execute(new CacheStatement() {
            @Override
            public Long evaluate(Jedis jedis) {
                return jedis.setrange(key, start, value);
            }
        });
    }

    public void decrNumber(final String key, final long value) {
        connection.execute(new CacheStatement() {
            @Override
            public Long evaluate(Jedis jedis) {
                return jedis.decrBy(key, value);
            }
        });
    }

    public void incrNumber(final String key, final long value) {
        connection.execute(new CacheStatement() {
            @Override
            public Long evaluate(Jedis jedis) {
                return jedis.incrBy(key, value);
            }
        });
    }

    public void incrNumber(final String key, final double value) {
        connection.execute(new CacheStatement() {
            @Override
            public Double evaluate(Jedis jedis) {
                return jedis.incrByFloat(key, value);
            }
        });
    }

    public void append(final String key, final String value) {
        connection.execute(new CacheStatement() {
            @Override
            public Long evaluate(Jedis jedis) {
                return jedis.append(key, value);
            }
        });
    }

    public void setnx(final Map<String, String> keyValues) {
        final String[] values = convertArray(keyValues);
        connection.execute(new CacheStatement() {
            @Override
            public Long evaluate(Jedis jedis) {
                return jedis.msetnx(values);
            }
        });
    }

    public void setex(final String key, final String value, final int seconds) {
        connection.execute(new CacheStatement() {
            @Override
            public String evaluate(Jedis jedis) {
                return jedis.setex(key, seconds, value);
            }
        });
    }

    public void setexm(final String key, final String value, final int milliseconds) {
        connection.execute(new CacheStatement() {
            @Override
            public String evaluate(Jedis jedis) {
                return jedis.psetex(key, milliseconds, value);
            }
        });
    }
}
