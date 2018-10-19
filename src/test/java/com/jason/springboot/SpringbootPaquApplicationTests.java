package com.jason.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootPaquApplicationTests {
    @Autowired
    StringRedisTemplate stringRedisTemplate;//操作k-v都是字符串的
    @Autowired
    RedisTemplate redisTemplate;//操作k-v都是对象的

    @Test
    public void contextLoads() {
    }


}
