package com.pzj.framework.cache.redis.lock;

import com.pzj.framework.cache.core.Lock;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Administrator on 2017-3-31.
 */
public class RedisLock implements Lock{
    private static String defaultOwner(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
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

    public static RedisLock createLock(String lock, String owner, Date beginDate, Date endDate, Long timeout){
        RedisLock redisLock = new RedisLock();
        redisLock.setLock(lock);
        redisLock.setBeginDate(beginDate);
        redisLock.setEndDate(endDate);
        redisLock.setTimeout(timeout);
        redisLock.setOwner(owner);
        return redisLock;
    }

    private String lock;

    private String owner;

    private Date beginDate;

    private Date endDate;

    private Long timeout;

    @Override
    public String getLock() {
        return lock;
    }

    @Override
    public void setLock(String lock) {
        this.lock = lock;
    }

    @Override
    public String getOwner() {
        return owner;
    }

    @Override
    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getTimeout() {
        return timeout;
    }

    public void setTimeout(Long timeout) {
        this.timeout = timeout;
    }
}
