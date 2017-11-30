package com.hm707.jedis.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by LH on 2017/11/30.
 */
public abstract class RedisUtil {
	private static String HOST = "";
	private static int PORT = 6379;
	private static String PASSWORD = "";

	private static int TIME_OUT= 3000;

	/**最大空闲连接数, 应用自己评估，不要超过ApsaraDB for Redis每个实例最大的连接数**/
	private static int MAX_IDLE = 200;
	/**最大连接数, 应用自己评估，不要超过ApsaraDB for Redis每个实例最大的连接数**/
	private static int MAX_TOTAL = 200;
	/**在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；**/
	private static boolean TEST_ON_BORROW = true;
	/**在return给pool时，是否提前进行validate操作**/
	private static boolean TEST_ON_RETURN = false;

	private static JedisPoolConfig config = new JedisPoolConfig();

	private static JedisPool JEDIS_POOL;

	static {
		config.setMaxIdle(MAX_IDLE);
		config.setMaxTotal(MAX_TOTAL);

		config.setTestOnBorrow(TEST_ON_BORROW);

		config.setTestOnReturn(TEST_ON_RETURN);

		JEDIS_POOL = new JedisPool(config, HOST, PORT, TIME_OUT, PASSWORD);
	}

	public static Jedis getJedis() {

		return JEDIS_POOL.getResource();
	}
}
