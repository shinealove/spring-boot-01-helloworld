package com.atguigu.config;

import com.atguigu.entities.Employee;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;

import java.net.UnknownHostException;
import java.time.Duration;

@Configuration
public class MyRedisConfig {

    Logger log = LoggerFactory.getLogger(this.getClass());

    //过期时间1天
//    private Duration timeToLive = Duration.ofDays(1);
//    @Bean
//    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
//        //默认1
//        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
//                .entryTtl(this.timeToLive)
//                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(keySerializer()))
//                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(valueSerializer()))
//                .disableCachingNullValues();
//        RedisCacheManager redisCacheManager = RedisCacheManager.builder(connectionFactory)
//                .cacheDefaults(config)
//                .transactionAware()
//                .build();
//        log.debug("自定义RedisCacheManager加载完成");
//        return redisCacheManager;
//    }
//    @Bean(name = "redisTemplate")
//    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
//        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(redisConnectionFactory);
//        redisTemplate.setKeySerializer(keySerializer());
//        redisTemplate.setHashKeySerializer(keySerializer());
//        redisTemplate.setValueSerializer(valueSerializer());
//        redisTemplate.setHashValueSerializer(valueSerializer());
//        log.debug("自定义RedisTemplate加载完成");
//        return redisTemplate;
//    }
//    private RedisSerializer<String> keySerializer() {
//        return new StringRedisSerializer();
//    }
//    private RedisSerializer<Object> valueSerializer() {
//        return new GenericJackson2JsonRedisSerializer();
//    }
}
