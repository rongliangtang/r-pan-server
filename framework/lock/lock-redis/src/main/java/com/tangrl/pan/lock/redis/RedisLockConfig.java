package com.tangrl.pan.lock.redis;

import com.tangrl.pan.lock.core.LockConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.integration.support.locks.LockRegistry;

/**
 * 基于Redis使用分布式锁
 * 该方案集成spring-data-redis，配置项也复用原来的配置，不重复造轮子
 */
@SpringBootConfiguration
@Slf4j
public class RedisLockConfig {

    @Bean
    public LockRegistry redisLockRegistry(RedisConnectionFactory redisConnectionFactory) {
        RedisLockRegistry lockRegistry = new RedisLockRegistry(redisConnectionFactory, LockConstants.R_PAN_LOCK);
        log.info("redis lock is loaded successfully!");
        return lockRegistry;
    }

}
