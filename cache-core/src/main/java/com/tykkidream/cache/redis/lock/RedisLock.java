package com.tykkidream.cache.redis.lock;

import com.tykkidream.cache.core.CacheException;
import com.tykkidream.cache.core.Lock;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Administrator on 2017-3-31.
 */
public class RedisLock implements Lock {
    private static String defaultOwner(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public static RedisLock createLock(Lock lock){
        return createLock(lock.getLock(), lock.getOwner(), lock.getBeginDate(), lock.getEndDate());
    }

    public static RedisLock createLock(String lock, Date beginDate, Date endDate){
        return createLock(lock, defaultOwner(), beginDate, endDate);
    }

    public static RedisLock createLock(String lock, Date beginDate, Long timeout){
        return createLock(lock, defaultOwner(), beginDate, timeout);
    }

    public static RedisLock createLock(String lock, String owner, Date beginDate, Date endDate){
        return createLock(lock, owner, beginDate, endDate, endDate.getTime() - beginDate.getTime());
    }

    public static RedisLock createLock(String lock, String owner, Date beginDate, Long timeout){
        return createLock(lock, owner, beginDate, new Date(beginDate.getTime() + timeout), timeout);
    }

    private static RedisLock createLock(String lock, String owner, Date beginDate, Date endDate, Long timeout){
        if (lock == null){
            throw new CacheException("创建 RedisLock 失败，lock 参数不能为空。");
        }
        if (owner == null){
            throw new CacheException("创建 RedisLock 失败，owner 参数不能为空。");
        }
        if (beginDate == null){
            throw new CacheException("创建 RedisLock 失败，beginDate 参数不能为空。");
        }
        if (endDate == null){
            throw new CacheException("创建 RedisLock 失败，endDate 参数不能为空。");
        }
        if (timeout == null){
            throw new CacheException("创建 RedisLock 失败，timeout 参数不能为空。");
        }

        RedisLockOpener lockOpener = new RedisLockOpener();
        lockOpener.setOwner(owner);
        lockOpener.setBeginDate(beginDate);
        lockOpener.setEndDate(endDate);

        RedisLock redisLock = new RedisLock();
        redisLock.lock = lock;
        redisLock.timeout =timeout;
        redisLock.lockOpener = lockOpener;
        return redisLock;
    }

    private String lock;

    private Long timeout;

    private RedisLockOpener lockOpener;

    @Override
    public String getLock() {
        return lock;
    }

    @Override
    public String getOwner() {
        return lockOpener.getOwner();
    }

    public Date getBeginDate() {
        return lockOpener.getBeginDate();
    }

    public Date getEndDate() {
        return lockOpener.getEndDate();
    }

    public Long getTimeout() {
        return timeout;
    }

    public RedisLockOpener getLockOpener() {
        return lockOpener;
    }
}
