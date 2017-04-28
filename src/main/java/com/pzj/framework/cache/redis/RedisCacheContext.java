package com.pzj.framework.cache.redis;

import com.pzj.framework.cache.core.*;
import com.pzj.framework.cache.redis.basic.HashCacheService;
import com.pzj.framework.cache.redis.basic.KeyCacheService;
import com.pzj.framework.cache.redis.basic.ListCacheService;
import com.pzj.framework.cache.redis.basic.QueueCacheService;
import com.pzj.framework.cache.redis.basic.StringCacheService;
import com.pzj.framework.cache.redis.lock.SimpleLockService;

/**
 * Created by Administrator on 2017-1-3.
 */
public class RedisCacheContext implements CacheContext {

	private Connection connection = null;

	private CacheObjectService cacheObjectService = null;
	private final String cacheObjectServiceLock = "cacheObjectServiceLock";

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

	private KeyCacheService keyCacheService = null;
	private final String keyCacheServiceLock = "keyCacheServiceLock";

	private LockService lockService = null;
	private final String lockServiceLock = "lockServiceLock";

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public CacheService getCacheService() {
		if (cacheService == null) {
			synchronized (cacheServiceLock) {
				if (cacheService == null) {
					RedisCacheService redisCacheService = new RedisCacheService();
					redisCacheService.setStringCacheService(getOrCreateStringCacheService());
					redisCacheService.setHashCacheService(getOrCreateHashCacheService());
					redisCacheService.setListCacheService(getOrCreateListCacheService());
					redisCacheService.setQueueCacheService(getOrCreateQueueCacheService());
					redisCacheService.setKeyCacheService(getOrCreateKeyCacheService());
					cacheService = redisCacheService;
				}
			}
		}
		return cacheService;
	}

	@Override
	public CacheObjectService getCacheObjectService() {
		if (cacheObjectService == null) {
			synchronized (cacheObjectServiceLock) {
				if (cacheObjectService == null) {
					RedisCacheObjectService redisCacheObjectService = new RedisCacheObjectService();
					redisCacheObjectService.setStringCacheService(getOrCreateStringCacheService());
					redisCacheObjectService.setHashCacheService(getOrCreateHashCacheService());
					cacheObjectService = redisCacheObjectService;
				}
			}
		}
		return cacheObjectService;
	}

	@Override
	public LockService getLockService() {
		if (lockService == null) {
			synchronized (lockServiceLock) {
				if (lockService == null) {
					lockService = new SimpleLockService(getConnection());
				}
			}
		}
		return lockService;
	}

	public StringCacheService getOrCreateStringCacheService() {
		if (stringCacheService == null) {
			synchronized (stringCacheServiceLock) {
				if (stringCacheService == null) {
					stringCacheService = new StringCacheService(connection);
				}
			}
		}
		return stringCacheService;
	}

	public HashCacheService getOrCreateHashCacheService() {
		if (hashCacheService == null) {
			synchronized (hashCacheServiceLock) {
				if (hashCacheService == null) {
					hashCacheService = new HashCacheService(connection);
				}
			}
		}
		return hashCacheService;
	}

	public ListCacheService getOrCreateListCacheService() {
		if (listCacheService == null) {
			synchronized (listCacheServiceLock) {
				if (listCacheService == null) {
					listCacheService = new ListCacheService(connection);
				}
			}
		}
		return listCacheService;
	}

	public QueueCacheService getOrCreateQueueCacheService() {
		if (queueCacheService == null) {
			synchronized (queueCacheServiceLock) {
				if (queueCacheService == null) {
					queueCacheService = new QueueCacheService(connection);
				}
			}
		}
		return queueCacheService;
	}

	public KeyCacheService getOrCreateKeyCacheService() {
		if (keyCacheService == null) {
			synchronized (keyCacheServiceLock) {
				if (keyCacheService == null) {
					keyCacheService = new KeyCacheService(connection);
				}
			}
		}
		return keyCacheService;
	}
}
