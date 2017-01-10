package com.pzj.framework.cache.core;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2016-12-28.
 */
public interface CacheService {
    /**
     * 字符串缓存：获取一个字符器值
     * @param key
     * @return
     */
    String strGet(CacheKey key);

    /**
     * 字符串缓存：获取多个字符串值
     * @param keys
     * @return
     */
    List<String> strGet(CacheKey ... keys);

    /**
     * 字符串缓存：递减1
     * @param key
     */
    void strDecrNumber(CacheKey key);

    /**
     * 字符串缓存：递减指定值
     * @param key
     * @param value
     */
    void strDecrNumber(CacheKey key, long value);

    /**
     * 字符串缓存：递增1
     * @param key
     */
    void strIncrNumber(CacheKey key);

    /**
     * 字符串缓存：递增多个值
     * @param key
     * @param value
     */
    void strIncrNumber(CacheKey key, long value);

    /**
     * 字符串缓存：设置一个值
     * @param key
     * @param value
     */
    void strSet(CacheKey key, String value);

    /**
     * 字符串缓存：设置多个值
     * @param keyValues
     */
    void strSet(Map<CacheKey,String> keyValues);

    /**
     * 字符串缓存：设置一个值，如果key存在，则取消设置
     * @param key
     * @param value
     */
    void strSetnx(CacheKey key, String value);

    /**
     * 键值对缓存：获取所有键值对
     * @param key
     * @return
     */
    Map<String,String> mapGet(CacheKey key);

    /**
     * 键值对缓存：获取一个键值
     * @param key
     * @param field
     * @return
     */
    String mapGet(CacheKey key, String field);

    /**
     * 键值对缓存：获取多个键值对
     * @param key
     * @param fields
     * @return
     */
    Map<String,String> mapGet(CacheKey key, String ... fields);

    /**
     * 键值对缓存：删除一个键值对
     * @param key
     * @param field
     */
    void mapDel(CacheKey key, String field);

    /**
     * 键值对缓存：删除多个键值对
     * @param key
     * @param fields
     */
    void mapDel(CacheKey key, String ... fields);

    /**
     * 键值对缓存：设置一个键值对
     * @param key
     * @param field
     * @param value
     */
    void mapSet(CacheKey key, String field, String value);

    /**
     * 键值对缓存：设置多个键值对
     * @param key
     * @param fieldValues
     */
    void mapSet(CacheKey key, Map<String, String> fieldValues);

    /**
     * 键值对缓存：获取所有键
     * @param key
     * @return
     */
    Set<String> mapKeys(CacheKey key);

    /**
     *  列表缓存：从列表中获得指定位置的元素
     * @param key
     * @param index
     * @return
     */
    String listGet(CacheKey key, int index);

    /**
     *  列表缓存：从列表中获得指定区间的元素
     * @param key
     * @param start
     * @param stop
     * @return
     */
    List<String> listGet(CacheKey key, int start, int stop);

    /**
     * 列表缓存：获得列表的长度
     * @param key
     * @return
     */
    Long listSize(CacheKey key);

    /**
     * 列表缓存：从列表移除指定（所有的）元素
     * @param key
     * @param value
     */
    void listRemove(CacheKey key, String value);

    /**
     * 列表缓存：设置列表指定位置的元素
     * @param key
     * @param index
     * @param value
     */
    void listSet(CacheKey key, int index, String value);

    /**
     * 列表缓存：裁剪列表，只保留指定区间的元素
     * @param key
     * @param start
     * @param stop
     */
    void listTrim(CacheKey key, int start, int stop);

    /**
     * 从队列左边弹出一个元素
     * @param key
     * @return
     */
    String queuePopFromLeft(CacheKey key);

    /**
     * 从队列右边弹出一个元素
     * @param key
     * @return
     */
    String queuePopFromRight(CacheKey key);

    /**
     * 从多个队列左边阻塞弹出一个元素
     * @param keys
     * @return
     */
    List<String> queueBlockPopFromLeft(CacheKey ... keys);

    /**
     * 在 timeout 的时间内，从多个队列左边阻塞弹出一个元素
     * @param timeout
     * @param keys
     * @return
     */
    List<String> queueBlockPopFromLeft(int timeout, CacheKey ... keys);

    /**
     * 从多个队列右边阻塞弹出一个元素
     * @param keys
     * @return
     */
    List<String> queueBlockPopFromRight(CacheKey ... keys);

    /**
     * 在 timeout 的时间内，从多个队列右边阻塞弹出一个元素
     * @param timeout
     * @param keys
     * @return
     */
    List<String> queueBlockPopFromRight(int timeout, CacheKey ... keys);

    /**
     * 向队列左边推入一个元素
     * @param key
     * @param values
     */
    void queuePushToLeft(CacheKey key, String ... values);

    /**
     * 向队列右边推入一个元素
     * @param key
     * @param values
     */
    void queuePushToRight(CacheKey key, String ... values);

}
