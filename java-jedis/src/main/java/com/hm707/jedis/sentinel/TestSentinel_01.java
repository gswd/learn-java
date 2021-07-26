package com.hm707.jedis.sentinel;

import java.util.HashSet;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

/**
 * Created by LH on 2017/12/5.
 */
public class TestSentinel_01 {
	public static void main(String[] args) {

		String masterName = "mymaster";
		Set<String> sentinels = new HashSet<>();
		sentinels.add("10.223.168.100:26379");
		sentinels.add("10.223.168.101:26379");
		sentinels.add("10.223.168.101:26380");


		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxTotal(1000);
		jedisPoolConfig.setMaxIdle(10);
		jedisPoolConfig.setMinIdle(1);
		jedisPoolConfig.setMaxWaitMillis(30000);
		jedisPoolConfig.setTestOnBorrow(true);
		jedisPoolConfig.setTestOnReturn(true);
		jedisPoolConfig.setTestWhileIdle(true);

		JedisSentinelPool sentinelPool = new JedisSentinelPool(masterName, sentinels, jedisPoolConfig, 2000, "ncp29@(pw5%");


		try(Jedis jedis = sentinelPool.getResource()){
			System.out.println(jedis.info("Replication"));
			System.out.println("host =>" + jedis.getClient().getHost());
			System.out.println("= 1 => " + jedis.getClient().getPort());
//			String setResult = jedis.set("k11", "v_0");
//			jedis.expire("k11", 5);
//			System.out.println("setResult : " + setResult);

		} catch (Exception e) {
			System.out.println("first error.....");
			e.printStackTrace();
		}
		try (Jedis jedis1 = sentinelPool.getResource()) {
			String value = jedis1.get("k11");
			System.out.println("= 2 => " + jedis1.getClient().getPort());
			System.out.println("--> " + value);
		} catch (Exception e) {
			System.out.println("second error.....");
			e.printStackTrace();
		}


	}
}
