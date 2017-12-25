package com.cxxy.shop.redis;

import com.cxxy.shop.redis.serializer.RedisObjectSerializer;
import com.cxxy.shop.util.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Author:liuhui
 * Description: Redis缓存常用操作方法的封装
 * Date: 下午11:01 2017/12/23
 */
@Service
public class RedisManager {
    private Logger logger = LoggerFactory.getLogger(RedisManager.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 存储数据
     *
     * @param key   String
     * @param value Object
     * @return boolean
     */
    public boolean set(final String key, final Object value) {
        boolean result = redisTemplate.execute((RedisCallback<Boolean>) connection -> {
            RedisObjectSerializer serializer = (RedisObjectSerializer) redisTemplate.getValueSerializer();
            connection.set(serializer.serialize(key), serializer.serialize(value));
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
    public Object get(final String key) {
        Object result = redisTemplate.execute((RedisCallback<Object>) connection -> {
            RedisObjectSerializer serializer = (RedisObjectSerializer) redisTemplate.getValueSerializer();
            byte[] value = connection.get(serializer.serialize(key));
            return serializer.deserialize(value);
        });
        return result;
    }

    /**
     * 删除
     *
     * @param key String
     * @return
     */
    public Long remove(final String key) {
        Long result = redisTemplate.execute((RedisCallback<Long>) connection -> {
            RedisObjectSerializer serializer = (RedisObjectSerializer) redisTemplate.getValueSerializer();
            Long value = connection.del(serializer.serialize(key));
            return value;
        });
        return result;
    }
}
