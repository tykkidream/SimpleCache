package com.tykkidream.cache.redis.lock;

import com.alibaba.fastjson.JSON;

import com.tykkidream.cache.core.*;
import com.tykkidream.cache.redis.AbstractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.util.Date;

/**
 * Created by Administrator on 2017-3-31.
 */
public class SimpleLockService extends AbstractService implements LockService {
    private static Logger logger = LoggerFactory.getLogger(SimpleLockService.class);


    public SimpleLockService(Connection connection) {
        super(connection);
    }

    @Override
    public Boolean begin(final Lock lock, final long waitTime) {
        Boolean result = connection.execute(new Statement() {
            @Override
            public Boolean evaluate(Jedis jedis) {
                return SimpleLockService.this.beginLock(jedis, lock, waitTime);
            }
        });

        return result;
    }

    protected Boolean beginLock(Jedis jedis, Lock lock, long waitTime){
        if (lock == null){
            String msg = String.format("抢锁参数为空。");
            throw new CacheException(msg);
        }

        // 锁之皇帝
        String lockEmperor = lock.getLock();
        // 锁之太子
        String lockPrince = seizeLockPrince(jedis, lockEmperor, waitTime);

        // 锁之皇帝上位。
        Long seize = jedis.setnx(lockEmperor, lockValue(lock));
        if (seize != 1){
            String msg = String.format("抢锁 %1$s 失败。", lockEmperor);
            throw new CacheException(msg);
        }
        Long expire = jedis.pexpireAt(lockEmperor, lock.getEndDate().getTime());
        if (expire < 0){
            String msg = String.format("设置锁 %1$s 的超时时间时出现错误。", lockEmperor);
            throw new CacheException(msg);
        }

        deleteLockPrince(jedis, lockPrince);

        return true;
    }

    private String seizeLockPrince(Jedis jedis, String lockEmperor, long waitTime){
        String lockPrince = null;
        for (;;){
            // 锁之僵尸
            Boolean lockZombie = jedis.exists(lockEmperor);
            if (lockZombie){
                // ttl 能说明锁是正常锁，还是死锁。
                Long survivalTime = jedis.pttl(lockEmperor);
                if (survivalTime > waitTime){
                    String msg = String.format("获取锁失败，锁 %1$s 目前被其它程序占有。", lockEmperor);
                    throw new CacheException(msg);
                } else if (survivalTime > 0){
                    sleep(survivalTime);
                    continue;
                } else {
                    lockPrince = seizeLockPrince(jedis, lockEmperor);
                }
            }
            break;
        }
        return lockPrince;
    }

    private void sleep(long waitTime) {
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
        }
    }

    private String seizeLockPrince(Jedis jedis, String lockEmperor){
        String lockPrince = lockEmperor + "-Prince";
        logger.warn("获取锁 {} 时，发现为死锁，开始抢死锁。", lockEmperor);

        Date currentDate = new Date();
        RedisLock lock = RedisLock.createLock(lockPrince, currentDate, 66666L);

        Boolean seizeLock = beginLock(jedis, lock, 0L);
        if (seizeLock){
            // 删除锁之僵尸
            jedis.del(lockEmperor);
        }
        return lockPrince;
    }

    protected String lockValue(Lock lock){
        RedisLock redisLock = getRedisLock(lock);
        String json = JSON.toJSONString(redisLock.getLockOpener());
        return json;
    }

    protected RedisLock getRedisLock(Lock lock) {
        RedisLock redisLock;
        if (lock instanceof RedisLock){
            redisLock = (RedisLock)lock;
        } else {
            redisLock = RedisLock.createLock(lock);
        }
        return redisLock;
    }

    private void deleteLockPrince(Jedis jedis, String lockPrince){
        if (lockPrince != null){
            // 太子上位后就没太子了。
            jedis.del(lockPrince);
        }
    }

    @Override
    public Boolean end(final Lock lock) {
        Boolean result = connection.execute(new Statement() {
            @Override
            public Boolean evaluate(Jedis jedis) {
                return SimpleLockService.this.endLock(jedis, lock);
            }
        });

        return result;
    }

    protected Boolean endLock(Jedis jedis, Lock lock){
        if (lock == null){
            return false;
        }

        logBegin(lock);

        String lockJson = jedis.get(lock.getLock());

        if (lockJson == null){
            logLockNoExist(lock);
            return false;
        }

        RedisLockOpener lockOpener = JSON.parseObject(lockJson, RedisLockOpener.class);
        if (lockOpener == null){
            logLockInfoLost(lock);
            return false;
        }

        boolean sameLock = isTheSameLock(lock, lockOpener);
        if (!sameLock){
            logLockBelongOtherOwner(lock, lockOpener);
            return false;
        }

        jedis.del(lock.getLock());

        logEnd(lock);

        return true;
    }

    protected boolean isTheSameLock(Lock lock, RedisLockOpener lockOpener){
        return lock.getOwner().equals(lockOpener.getOwner());
    }

    private void logBegin(Lock lock) {
        logger.info("释放锁 {} 开始。", lock.getLock());
    }

    private void logLockNoExist(Lock lock) {
        logger.error("获取锁失败，锁 {} 不存在", lock.getLock());
    }

    private void logLockInfoLost(Lock lock) {
        logger.error("读取锁信息失败，锁 {} 信息丢失或格式错误。", lock.getLock());
    }

    private void logLockBelongOtherOwner(Lock lock, RedisLockOpener lockOpener) {
        logger.error("释放锁失败，锁属于其它程序，锁拥有者为 {}，尝试释放锁的拥有者为 {}。", lockOpener.getOwner(), lock.getOwner());
    }

    private void logEnd(Lock lock) {
        logger.info("释放锁 {} 成功。", lock.getLock());
    }
}
