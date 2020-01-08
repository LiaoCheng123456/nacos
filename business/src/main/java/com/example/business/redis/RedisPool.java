package com.example.business.redis;


import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;


public class RedisPool {

	private static ShardedJedisPool pool;

	
	static {
			JedisPoolConfig config = new JedisPoolConfig();
	        config.setMaxWaitMillis(10000);

	        config.setMaxTotal(10000);
	        config.setMaxIdle(10);
	        config.setMinIdle(5);
	        JedisShardInfo jedisShardInfo1 = new JedisShardInfo("127.0.0.1", "6379");
	        //if(props.getProperty("redis.pass")!=null && !props.getProperty("redis.pass").equals("") ){
	         jedisShardInfo1.setPassword("123456");
	        //}
	        List<JedisShardInfo> list = new LinkedList<JedisShardInfo>();
	        list.add(jedisShardInfo1);
	        pool = new ShardedJedisPool(config, list);
	}

	public static ShardedJedis getJedisShardInfo() {
		return pool.getResource();
	}
	
	public static ShardedJedisPool getShardedJedisPool() {
		return pool;
	}


}
