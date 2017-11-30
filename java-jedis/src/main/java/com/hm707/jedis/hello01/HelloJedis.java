package com.hm707.jedis.hello01;

import redis.clients.jedis.Jedis;

/**
 * Jedis线程非安全。多线程下使用同一个Jedis会有并发问题。
 */
public class HelloJedis {
	public static void main(String[] args) {
		Jedis jedis = new Jedis("120.78.162.77", 6379);
		jedis.auth("LiHao0510");

		jedis.set("singleJedis", "hello jedis!");
		System.out.println(jedis.get("singleJedis"));

		jedis.del("singleJedis");
		jedis.close();

	}
}
