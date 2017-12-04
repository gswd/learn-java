package com.hm707.jedis.subpub;

import java.util.concurrent.TimeUnit;

import redis.clients.jedis.Jedis;

/**
 * Created by LH on 2017/12/4.
 */
public class TestPublish {
	public static void main(String[] args) throws InterruptedException {
		Jedis jedis = new Jedis("192.168.33.130", 6379);
		jedis.auth("123123");

		jedis.publish("first", "how are you.");

		TimeUnit.SECONDS.sleep(3);

		jedis.publish("first", "and you?");

	}
}
