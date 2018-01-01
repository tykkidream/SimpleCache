package com.tykkidream.cache.redis.basic;

import java.util.Map;
import java.util.Set;

import com.tykkidream.cache.core.Connection;
import com.tykkidream.cache.core.Statement;
import com.tykkidream.cache.redis.AbstractService;
import redis.clients.jedis.Jedis;

/**
 * 集合缓存
 *
 * @author DongChunfu
 * @version $Id: SortSetCacheService.java, v 0.1 2017年2月16日 下午7:12:15 DongChunfu Exp $
 */
public class SetCacheService extends AbstractService {

	public SetCacheService(final Connection connection) {
		super(connection);
	}

	/**
	 * 	为有序集合添加一个成员
	 *
	 * @param key 键
	 * @param score 分数
	 * @param member 成员
	 * @return　<code>error</code> 如果KEY存在,但不是一个有序集合　<code>Integer</code> 如果存在更新分数，不存在则新增
	 */
	public Long zaddSingle(final String key, final double score, final String member) {
		return connection.execute(new Statement() {
			@SuppressWarnings("unchecked")
			@Override
			public Long evaluate(final Jedis jedis) {
				return jedis.zadd(key, score, member);
			}
		});
	}

	/**
	 * 为有序集合添加一批成员
	 *
	 * @param key 键
	 * @param members 成员们,值 +分数
	 * @return
	 */
	public Long zaddBatch(final String key, final Map<String, Double> members) {
		return connection.execute(new Statement() {
			@SuppressWarnings("unchecked")
			@Override
			public Long evaluate(final Jedis jedis) {
				return jedis.zadd(key, members);
			}
		});
	}

	/**
	 * 批量获取并移除分数范围内的成员
	 *
	 * @param key 键
	 * @param start 起始分数
	 * @param end 截止分数
	 * @return 有序成员
	 */
	public Set<String> zgetAndDelByScoreRange(final String key, final long start, final long end) {
		return connection.execute(new Statement() {
			@SuppressWarnings("unchecked")
			@Override
			public Set<String> evaluate(final Jedis jedis) {

				final Set<String> members = jedis.zrange(key, start, end);
				if (null == members || members.size() == 0) {
					return null;
				}

				jedis.zrem(key, members.toArray(new String[members.size()]));
				return members;
			}
		});
	}
}