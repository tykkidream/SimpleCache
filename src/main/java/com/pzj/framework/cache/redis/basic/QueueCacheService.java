package com.pzj.framework.cache.redis.basic;

import com.pzj.framework.cache.core.Connection;
import com.pzj.framework.cache.core.Statement;
import com.pzj.framework.cache.redis.AbstractService;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * Created by Administrator on 2016-12-30.
 */
public class QueueCacheService extends AbstractService {
    public QueueCacheService(Connection connection){
        super(connection);
    }

    public String popFromLeft(final String key) {
        return connection.execute(new Statement() {
            @Override
            public String evaluate(Jedis jedis) {
                return jedis.lpop(key);
            }
        });
    }

    public String popFromRight(final String key) {
        return connection.execute(new Statement() {
            @Override
            public String evaluate(Jedis jedis) {
                return jedis.rpop(key);
            }
        });
    }

    public List<String> blockPopFromLeft(final String ... keys){
        return connection.execute(new Statement() {
            @Override
            public List<String> evaluate(Jedis jedis) {
                return jedis.blpop(keys);
            }
        });
    }

    public List<String> blockPopFromLeft(final int timeout, final String ... keys){
        return connection.execute(new Statement() {
            @Override
            public List<String> evaluate(Jedis jedis) {
                return jedis.blpop(timeout, keys);
            }
        });
    }

    public List<String> blockPopFromRight(final String ... keys){
        return connection.execute(new Statement() {
            @Override
            public List<String> evaluate(Jedis jedis) {
                return jedis.brpop(keys);
            }
        });
    }

    public List<String> blockPopFromRight(final int timeout, final String ... keys){
        return connection.execute(new Statement() {
            @Override
            public List<String> evaluate(Jedis jedis) {
                return jedis.brpop(timeout, keys);
            }
        });
    }

    public void pushToLeft(final String key, final String ... values){
        connection.execute(new Statement() {
            @Override
            public Long evaluate(Jedis jedis) {
                return jedis.lpush(key, values);
            }
        });
    }

    public void pushToLeftx(final String key, final String ... values){
        connection.execute(new Statement() {
            @Override
            public Long evaluate(Jedis jedis) {
                return jedis.lpushx(key, values);
            }
        });
    }

    public void pushToRight(final String key, final String ... values){
        connection.execute(new Statement() {
            @Override
            public Long evaluate(Jedis jedis) {
                return jedis.rpush(key, values);
            }
        });
    }

    public void pushToRightx(final String key, final String ... values){
        connection.execute(new Statement() {
            @Override
            public Long evaluate(Jedis jedis) {
                return jedis.rpushx(key, values);
            }
        });
    }
}
