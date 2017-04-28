package com.pzj.framework.cache.core;

import com.pzj.framework.cache.redis.lock.RedisLock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by Administrator on 2017-4-28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/redis.xml"})
public class LockServiceTest {
    private static Logger logger = LoggerFactory.getLogger(LockServiceTest.class);

    @Resource
    LockService lockService;

    @Test
    public void test(){
        RedisLock lock = RedisLock.createLock("create_user_lock", new Date(), 5000L);

        Boolean begin = lockService.begin(lock, 20000);

        if (begin){
            logger.info("加锁成功");
        } else {
            logger.info("加锁失败");
        }

        Boolean end = lockService.end(lock);
        if (end){
            logger.info("释放锁成功");
        } else {
            logger.info("释放锁失败");
        }
    }

}
