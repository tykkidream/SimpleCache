package com.pzj.framework.cache.redis.basic;

import com.pzj.framework.cache.redis.AbstractService;
import redis.clients.jedis.Jedis;

import com.pzj.framework.cache.core.Connection;
import com.pzj.framework.cache.core.Statement;

/**
 * Created by Administrator on 2016-12-29.
 */
public class KeyCacheService extends AbstractService {
	public KeyCacheService(Connection connection) {
		super(connection);
	}

	public void keyExpireat(final String key, final long unixTime) {
		connection.execute(new Statement() {
			@Override
			public Long evaluate(Jedis jedis) {
				return jedis.expireAt(key, unixTime);
			}
		});
	}

}