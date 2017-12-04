package com.hm707.jedis.subpub;

import redis.clients.jedis.JedisPubSub;

/**
 * Created by LH on 2017/12/4.
 */
public class RedisMsgPubsubListener extends JedisPubSub{
	@Override
	public void onMessage(String channel, String message) {
		System.out.println("onMessage()," + channel + "=" + message);
		this.unsubscribe();
	}
}
