package com.example.server.config.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * redis配置类
 */

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String,Object> redisTemplate(LettuceConnectionFactory connectionFactory){

        // 序列化

        RedisTemplate<String, Object> template = new RedisTemplate<>();
        // Strin类型的key序列器
        template.setKeySerializer(new StringRedisSerializer());
        // Strin类型的value序列器
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        // hash类型的key序列器
        template.setHashKeySerializer(new StringRedisSerializer());
        // hash类型的value序列器
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        //设置连接工厂
        template.setConnectionFactory(connectionFactory);
        return template;
    }
}
