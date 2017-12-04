package com.hm707.jedis.subpub;

import redis.clients.jedis.Jedis;

/**
 * Created by LH on 2017/12/4.
 */
public class TestSubscribe {
	public static void main(String[] args) {
		Jedis jedis = new Jedis("192.168.33.130", 6379);
		jedis.auth("123123");

		RedisMsgPubsubListener listener = new RedisMsgPubsubListener();

		/**
		 * 注意：subscribe是一个阻塞的方法，在取消订阅该频道前，会一直阻塞在这，只有当取消了订阅才会执行下面的done ...
		 * onMessage里面收到消息后，调用了this.unsubscribe(); 来取消订阅，这样才会执行后面的other code
		 */
		jedis.subscribe(listener, "first");

		System.out.println("done ....");

	}
}
