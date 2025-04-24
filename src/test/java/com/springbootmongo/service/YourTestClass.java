package com.springbootmongo.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class YourTestClass {

    @Autowired
    private RedisTemplate redisTemplate;

    @Disabled
    @Test
    public void testRedisSetGet() {
        redisTemplate.opsForValue().set("name", "Krishna");
        Object name = redisTemplate.opsForValue().get("name");
        System.out.println("Value from Redis: " + name);
        System.out.println(redisTemplate.opsForValue().get("salary"));
        assertEquals("Krishna", name);
    }
}
