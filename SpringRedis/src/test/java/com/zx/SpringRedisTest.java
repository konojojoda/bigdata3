package com.zx;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringRedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void test(){
        Set<String> keys = stringRedisTemplate.keys("*");
        for (String key:keys){
            System.out.println("key="+key);
        }
    }
    @Test
    public void test1(){
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.set("school","CUP");

        System.out.println(operations.get("school"));

    }

}
