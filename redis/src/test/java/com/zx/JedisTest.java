package com.zx;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Protocol;

public class JedisTest {
    private String ipAddress="192.168.179.102";
    private int port= Protocol.DEFAULT_PORT;
    @Test
    public void testJedis(){
        Jedis jedis = new Jedis(ipAddress, port);
        String ping = jedis.ping();
        System.out.println(ping);
    }

}
