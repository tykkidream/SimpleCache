package com.pzj.framework.cache.redis.lock;

import com.alibaba.fastjson.JSON;
import com.pzj.framework.cache.core.Connection;
import com.pzj.framework.cache.core.Lock;
import com.pzj.framework.cache.core.LockService;
import com.pzj.framework.cache.core.Statement;
import com.pzj.framework.cache.redis.AbstractService;
import redis.clients.jedis.Jedis;

/**
 * Created by Administrator on 2017-3-31.
 */
public class RedisLockService extends AbstractService implements LockService{
    public RedisLockService(Connection connection) {
        super(connection);
    }

    @Override
    public Boolean begin(final Lock lock) {
        Boolean result = connection.execute(new Statement() {
            @Override
            public Boolean evaluate(Jedis jedis) {
                return RedisLockService.this.beginLock(jedis, lock);
            }
        });

        return result;
    }

    private Boolean beginLock(Jedis jedis, Lock lock){
        Boolean result = false;
        String json = JSON.toJSONString(lock);
        Long setnx = jedis.setnx(lock.getLock(), json);
        if (setnx == 1){
            jedis.expireAt(lock.getLock(), lock.getEndDate().getTime());
        }
        return result;
    }

    @Override
    public Boolean beginWithWait(Lock lock) {
        return false;
    }

    @Override
    public Boolean end(Lock lock) {
        return false;
    }
}
