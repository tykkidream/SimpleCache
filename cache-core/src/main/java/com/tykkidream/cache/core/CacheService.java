package com.tykkidream.cache.core;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016-12-28.
 */
public interface CacheService {
	/**
	 * 字符串缓存：获取一个字符器值
	 * @param key
	 * @return
	 */
	String strGet(String key);

	/**
	 * 字符串缓存：获取多个字符串值
	 * @param keys
	 * @return
	 */
	List<String> strGet(String... keys);

	/**
	 * 字符串缓存：递减1
	 * @param key
	 */
	void strDecrNumber(String key);

	/**
	 * 字符串缓存：递减指定值
	 * @param key
	 * @param value
	 */
	void strDecrNumber(String key, long value);

	/**
	 * 字符串缓存：递增1
	 * @param key
	 */
	Long strIncrNumber(String key);

	/**
	 * 字符串缓存：递增多个值
	 * @param key
	 * @param value
	 */
	Long strIncrNumber(String key, long value);

	/**
	 * 字符串缓存：设置一个值
	 * @param key
	 * @param value
	 */
	void strSet(String key, String value);

	/**
	 * 字符串缓存：设置多个值
	 * @param keyValues
	 */
	void strSet(Map<String, String> keyValues);

	/**
	 * 字符串缓存：设置一个值，如果key存在，则取消设置
	 * @param key
	 * @param value
	 */
	void strSetnx(String key, String value);

	/**
	 * 键值对缓存：获取所有键值对
	 * @param key
	 * @return
	 */
	Map<String, String> mapGet(String key);

	/**
	 * 键值对缓存：获取一个键值
	 * @param key
	 * @param field
	 * @return
	 */
	String mapGet(String key, String field);

	/**
	 * 键值对缓存：获取多个键值对
	 * @param key
	 * @param fields
	 * @return
	 */
	Map<String, String> mapGet(String key, String... fields);

	/**
	 * 键值对缓存：删除一个键值对
	 * @param key
	 * @param field
	 */
	void mapDel(String key, String field);

	/**
	 * 键值对缓存：删除多个键值对
	 * @param key
	 * @param fields
	 */
	void mapDel(String key, String... fields);

	/**
	 * 键值对缓存：设置一个键值对
	 * @param key
	 * @param field
	 * @param value
	 */
	void mapSet(String key, String field, String value);

	/**
	 * 键值对缓存：设置多个键值对
	 * @param key
	 * @param fieldValues
	 */
	void mapSet(String key, Map<String, String> fieldValues);

	/**
	 *  列表缓存：从列表中获得指定位置的元素
	 * @param key
	 * @param index
	 * @return
	 */
	String listGet(String key, int index);

	/**
	 * 列表缓存：获得列表的长度
	 * @param key
	 * @return
	 */
	Long listSize(String key);

	/**
	 * 列表缓存：从列表移除指定（所有的）元素
	 * @param key
	 * @param value
	 */
	void listRemove(String key, String value);

	/**
	 * 列表缓存：设置列表指定位置的元素
	 * @param key
	 * @param index
	 * @param value
	 */
	void listSet(String key, int index, String value);

	/**
	 * 从队列左边弹出一个元素
	 * @param key
	 * @return
	 */
	String queuePopFromLeft(String key);

	/**
	 * 从队列右边弹出一个元素
	 * @param key
	 * @return
	 */
	String queuePopFromRight(String key);

	/**
	 * 从多个队列左边阻塞弹出一个元素
	 * @param keys
	 * @return
	 */
	List<String> queueBlockPopFromLeft(String... keys);

	/**
	 * 在 timeout 的时间内，从多个队列左边阻塞弹出一个元素
	 * @param timeout
	 * @param keys
	 * @return
	 */
	List<String> queueBlockPopFromLeft(int timeout, String... keys);

	/**
	 * 从多个队列右边阻塞弹出一个元素
	 * @param keys
	 * @return
	 */
	List<String> queueBlockPopFromRight(String... keys);

	/**
	 * 在 timeout 的时间内，从多个队列右边阻塞弹出一个元素
	 * @param timeout
	 * @param keys
	 * @return
	 */
	List<String> queueBlockPopFromRight(int timeout, String... keys);

	/**
	 * 向队列左边推入一个元素
	 * @param key
	 * @param values
	 */
	void queuePushToLeft(String key, String... values);

	/**
	 * 向队列右边推入一个元素
	 * @param key
	 * @param values
	 */
	void queuePushToRight(String key, String... values);

	/**
	 * 设置key的固定过期时间，unixTime为unix时间
	 * @param key
	 * @param unixTime
	 */
	void keyExpireat(String key, long unixTime);
}
