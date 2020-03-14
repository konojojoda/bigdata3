package com.zx;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Protocol;

public class JedisPoolTest {
    private String ipAddress = "192.168.179.102";
    private int port= Protocol.DEFAULT_PORT;
    @Test
    public void test(){
        JedisPool jedisPool = new JedisPool(ipAddress, port);
        Jedis jedis = jedisPool.getResource();
        System.out.println(jedis.get("num"));
    }
}
