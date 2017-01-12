package com.pzj.framework.cache.redis;

import com.pzj.framework.cache.core.Connection;
import com.pzj.framework.cache.core.CacheContext;
import com.pzj.framework.cache.core.CacheService;
import com.pzj.framework.cache.redis.basic.HashCacheService;
import com.pzj.framework.cache.redis.basic.ListCacheService;
import com.pzj.framework.cache.redis.basic.QueueCacheService;
import com.pzj.framework.cache.redis.basic.StringCacheService;

/**
 * Created by Administrator on 2017-1-3.
 */
public class RedisCacheContext implements CacheContext {

    private Connection connection = null;

    private CacheService cacheService = null;
    private final String cacheServiceLock = "cacheServiceLock";

    private StringCacheService stringCacheService = null;
    private final String stringCacheServiceLock = "stringCacheServiceLock";

    private HashCacheService hashCacheService = null;
    private final String hashCacheServiceLock = "hashCacheServiceLock";

    private ListCacheService listCacheService = null;
    private final String listCacheServiceLock = "listCacheServiceLock";

    private QueueCacheService queueCacheService = null;
    private final String queueCacheServiceLock = "queueCacheServiceLock";

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public CacheService getCacheService() {
        if (cacheService == null){
            synchronized (cacheServiceLock){
                if (cacheService == null){
                    RedisCacheService redisCacheService = new RedisCacheService();
                    redisCacheService.setStringCacheService(getOrCreateStringCacheService());
                    redisCacheService.setHashCacheService(getOrCreateHashCacheService());
                    redisCacheService.setListCacheService(getOrCreateListCacheService());
                    redisCacheService.setQueueCacheService(getOrCreateQueueCacheService());
                    cacheService = redisCacheService;
                }
            }
        }
        return cacheService;
    }

    public StringCacheService getOrCreateStringCacheService() {
        if (stringCacheService == null){
            synchronized (stringCacheServiceLock){
                if (stringCacheService == null){
                    stringCacheService = new StringCacheService(connection);
                }
            }
        }
        return stringCacheService;
    }

    public HashCacheService getOrCreateHashCacheService() {
        if (hashCacheService == null){
            synchronized (hashCacheServiceLock){
                if (hashCacheService == null){
                    hashCacheService = new HashCacheService(connection);
                }
            }
        }
        return hashCacheService;
    }

    public ListCacheService getOrCreateListCacheService() {
        if (listCacheService == null){
            synchronized (listCacheServiceLock){
                if (listCacheService == null){
                    listCacheService = new ListCacheService(connection);
                }
            }
        }
        return listCacheService;
    }

    public QueueCacheService getOrCreateQueueCacheService() {
        if (queueCacheService == null){
            synchronized (queueCacheServiceLock){
                if (queueCacheService == null){
                    queueCacheService = new QueueCacheService(connection);
                }
            }
        }
        return queueCacheService;
    }
}
