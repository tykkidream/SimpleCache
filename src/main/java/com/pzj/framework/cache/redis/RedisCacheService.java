package com.pzj.framework.cache.redis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.pzj.framework.cache.core.CacheKey;
import com.pzj.framework.cache.core.CacheService;
import com.pzj.framework.cache.redis.basic.HashCacheService;
import com.pzj.framework.cache.redis.basic.KeyCacheService;
import com.pzj.framework.cache.redis.basic.ListCacheService;
import com.pzj.framework.cache.redis.basic.QueueCacheService;
import com.pzj.framework.cache.redis.basic.StringCacheService;

/**
 * 精简版缓存操作类
 * Created by Administrator on 2016-12-28.
 */
public class RedisCacheService implements CacheService {

	private StringCacheService stringCacheService;
	private HashCacheService hashCacheService;
	private QueueCacheService queueCacheService;
	private ListCacheService listCacheService;
	private KeyCacheService keyCacheService;

	public String strGet(String key) {
		return stringCacheService.get(key);
	}

	public List<String> strGet(String... keys) {
		return stringCacheService.get(keys);
	}

	public void strDecrNumber(String key) {
		CacheKey.checkKey(key);
		stringCacheService.decrNumber(key);
	}

	public void strDecrNumber(String key, long value) {
		CacheKey.checkKey(key);
		stringCacheService.decrNumber(key, value);
	}

	public void strIncrNumber(String key) {
		CacheKey.checkKey(key);
		stringCacheService.incrNumber(key);
	}

	public void strIncrNumber(String key, long value) {
		CacheKey.checkKey(key);
		stringCacheService.incrNumber(key, value);
	}

	public void strSet(String key, String value) {
		CacheKey.checkKey(key);
		stringCacheService.set(key, value);
	}

	public void strSet(Map<String, String> keyValues) {
		stringCacheService.set(convertToStrintKey(keyValues));
	}

	private HashMap<String, String> convertToStrintKey(Map<String, String> keyValues) {
		if (keyValues == null)
			return null;

		HashMap<String, String> result = new HashMap<>(keyValues.size());
		Iterator<Map.Entry<String, String>> iterator = keyValues.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, String> next = iterator.next();
			CacheKey.checkKey(next.getKey());
			result.put(next.getKey(), next.getValue());
		}
		return result;
	}

	public void strSetnx(String key, String value) {
		CacheKey.checkKey(key);
		stringCacheService.setnx(key, value);
	}

	public Map<String, String> mapGet(String key) {
		return hashCacheService.get(key);
	}

	public String mapGet(String key, String field) {
		return hashCacheService.get(key, field);
	}

	public Map<String, String> mapGet(String key, String... fields) {
		return hashCacheService.get(key, fields);
	}

	public void mapDel(String key, String field) {
		hashCacheService.del(key, field);
	}

	public void mapDel(String key, String... fields) {
		hashCacheService.del(key, fields);
	}

	public void mapSet(String key, String field, String value) {
		CacheKey.checkKey(key);
		hashCacheService.set(key, field, value);
	}

	public void mapSet(String key, Map<String, String> fieldValues) {
		CacheKey.checkKey(key);
		hashCacheService.set(key, fieldValues);
	}

	@Override
	public String listGet(String key, int index) {
		return listCacheService.get(key, index);
	}

	@Override
	public Long listSize(String key) {
		return listCacheService.size(key);
	}

	@Override
	public void listRemove(String key, String value) {
		listCacheService.remove(key, value);
	}

	@Override
	public void listSet(String key, int index, String value) {
		listCacheService.set(key, index, value);
	}

	@Override
	public String queuePopFromLeft(String key) {
		return queueCacheService.popFromLeft(key);
	}

	@Override
	public String queuePopFromRight(String key) {
		return queueCacheService.popFromRight(key);
	}

	@Override
	public List<String> queueBlockPopFromLeft(String... keys) {
		return queueCacheService.blockPopFromLeft(keys);
	}

	@Override
	public List<String> queueBlockPopFromLeft(int timeout, String... keys) {
		return queueCacheService.blockPopFromLeft(timeout, keys);
	}

	@Override
	public List<String> queueBlockPopFromRight(String... keys) {
		return queueCacheService.blockPopFromRight(keys);
	}

	@Override
	public List<String> queueBlockPopFromRight(int timeout, String... keys) {
		return queueCacheService.blockPopFromRight(timeout, keys);
	}

	@Override
	public void queuePushToLeft(String key, String... values) {
		CacheKey.checkKey(key);
		queueCacheService.pushToLeft(key, values);
	}

	@Override
	public void queuePushToRight(String key, String... values) {
		CacheKey.checkKey(key);
		queueCacheService.pushToRight(key, values);
	}

	public void setStringCacheService(StringCacheService stringCacheService) {
		this.stringCacheService = stringCacheService;
	}

	public void setHashCacheService(HashCacheService hashCacheService) {
		this.hashCacheService = hashCacheService;
	}

	public void setQueueCacheService(QueueCacheService queueCacheService) {
		this.queueCacheService = queueCacheService;
	}

	public void setListCacheService(ListCacheService listCacheService) {
		this.listCacheService = listCacheService;
	}

	@Override
	public void keyExpireat(String key, long unixTime) {
		keyCacheService.keyExpireat(key, unixTime);
	}

	public void setKeyCacheService(KeyCacheService keyCacheService) {
		this.keyCacheService = keyCacheService;
	}
}
