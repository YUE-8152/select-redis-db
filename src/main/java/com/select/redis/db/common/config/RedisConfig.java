package com.select.redis.db.common.config;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * @ProjectName: select-redis-db
 * @Package: com.select.redis.db.common.config
 * @ClassName: RedisConfig
 * @Author: YUE
 * @Description:
 * @Date: 2020/9/25 9:29
 * @Version: 1.0
 */
@Configuration
public class RedisConfig {
    @Resource
    private RedisTemplate redisTemplate;

    // 使用 springboot 2.1.3.RELEASE   springboot 2.1.4.RELEASE springboot 2.1.5.RELEASE 可以成功切换。
    // 但是从springboot 2.1.6.RELEASE 之后版本 如果不加connectionFactory.afterPropertiesSet()方法都会无法切换成功
    public RedisTemplate setDataBase(int num) {
        LettuceConnectionFactory connectionFactory = (LettuceConnectionFactory) redisTemplate.getConnectionFactory();
        if (connectionFactory != null && num != connectionFactory.getDatabase()) {
            connectionFactory.setDatabase(num);
            this.redisTemplate.setConnectionFactory(connectionFactory);
            connectionFactory.resetConnection();
            // springboot 2.1.6.RELEASE 之后版本 如果不加connectionFactory.afterPropertiesSet()方法都会无法切换成功
            connectionFactory.afterPropertiesSet();
        }
        return redisTemplate;
    }

    @Bean
    public RedisTemplate<Serializable, Object> getRedisTemplate() {
        // {"@class":"com.select.redis.db.bean.UserPo","id":1,"name":"AA","address":"A12345678"}
        // GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer();

        // {"@type":"com.select.redis.db.bean.UserPo","address":"A12345678","id":1,"name":"AA"}
        GenericFastJsonRedisSerializer serializer = new GenericFastJsonRedisSerializer();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(serializer);
        redisTemplate.setHashValueSerializer(serializer);
        return redisTemplate;
    }
}
