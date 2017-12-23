package com.cxxy.shop.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Author:liuhui
 * Description:
 * Date: 下午10:34 2017/12/23
 */
@Configuration
@EnableAutoConfiguration
public class RedisConfig {
    private Logger logger = LoggerFactory.getLogger(RedisConfig.class);

    @Bean
    @ConfigurationProperties(prefix = "spring.redis")
    public JedisPoolConfig getRedisConfig(){
        JedisPoolConfig config = new JedisPoolConfig();
        return  config;
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.redis")
    public JedisConnectionFactory getConnectionFactory(){
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setPoolConfig(this.getRedisConfig());
        logger.info("====================== JedisConnectionFactory bean init success.");
        return factory;

    }

    @Bean
    public RedisTemplate<?,?> getRedisTemplate(){
        RedisTemplate<?,?> template = new StringRedisTemplate(this.getConnectionFactory());
        return template;
    }
}
