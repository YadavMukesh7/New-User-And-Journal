package com.springbootmongo.service;

public interface RedisService {
    public <T> T get(String city, Class<T> entityclass);

    public void set(String key, Object o, Long ttl);
}
