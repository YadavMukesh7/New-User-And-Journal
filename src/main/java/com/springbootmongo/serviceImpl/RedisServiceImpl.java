package com.springbootmongo.serviceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbootmongo.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Slf4j
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T get(String key, Class<T> entityClass) {
        try {
            Object o = redisTemplate.opsForValue().get(key);
            if (o == null) return null;

            // Avoid toString() on objects that may already be deserialized
            if (entityClass.isInstance(o)) {
                return entityClass.cast(o);
            }

            // Deserialize if stored as JSON string
            return mapper.readValue(o.toString(), entityClass);
        } catch (Exception ex) {
            log.error("Exception while getting key from Redis: {}", key, ex);
            return null;
        }
    }

    @Override
    public void set(String key, Object value, Long ttlInSeconds) {
        try {
            if (ttlInSeconds != null && ttlInSeconds > 0) {
                redisTemplate.opsForValue().set(key, value, Duration.ofSeconds(ttlInSeconds));
            } else {
                redisTemplate.opsForValue().set(key, value);
            }
        } catch (Exception ex) {
            log.error("Exception while setting key in Redis: {}", key, ex);
        }
    }
}
