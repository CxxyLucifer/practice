package com.cxxy.practice.redis;

import com.cxxy.practice.redis.serializer.RedisObjectSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


/**
 * Author:liuhui
 * Description: Redis缓存常用操作方法的封装
 * Date: 下午11:01 2017/12/23
 */
@Service
public class RedisManager {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 存储数据
     *
     * @param key   String
     * @param value Object
     * @return boolean
     */
    public boolean set(String key, Object value) {
        boolean result = redisTemplate.execute((RedisCallback<Boolean>) connection -> {
            RedisObjectSerializer serializer = (RedisObjectSerializer) redisTemplate.getValueSerializer();
            connection.set(serializer.serialize(key), serializer.serialize(value));
            return true;
        });
        return result;
    }

    /**
     * 存储数据
     *
     * @param key     String
     * @param value   Object
     * @param timeout seconds
     * @return boolean
     */
    public boolean set(String key, Object value, long timeout) {
        boolean result = redisTemplate.execute((RedisCallback<Boolean>) connection -> {
            RedisObjectSerializer serializer = (RedisObjectSerializer) redisTemplate.getValueSerializer();
            connection.setEx(serializer.serialize(key), timeout, serializer.serialize(value));
            return true;
        });
        return result;
    }

    /**
     * 获取数据
     *
     * @param key String
     * @return Object
     */
    public Object get(String key) {
        Object result = redisTemplate.execute((RedisCallback<Object>) connection -> {
            RedisObjectSerializer serializer = (RedisObjectSerializer) redisTemplate.getValueSerializer();
            byte[] value = connection.get(serializer.serialize(key));
            return serializer.deserialize(value);
        });
        return result;
    }


    /**
     * 设置过期时间
     *
     * @param key
     * @param expire
     * @return
     */
    public boolean expire(String key, final long expire) {
        return redisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }

    /**
     * 删除
     *
     * @param key String
     * @return
     */
    public Long remove(String key) {
        Long result = redisTemplate.execute((RedisCallback<Long>) connection -> {
            RedisObjectSerializer serializer = (RedisObjectSerializer) redisTemplate.getValueSerializer();
            Long value = connection.del(serializer.serialize(key));
            return value;
        });
        return result;
    }
}
