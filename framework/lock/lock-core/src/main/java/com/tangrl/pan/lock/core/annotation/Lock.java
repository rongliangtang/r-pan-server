package com.tangrl.pan.lock.core.annotation;

import com.tangrl.pan.lock.core.key.KeyGenerator;
import com.tangrl.pan.lock.core.key.StandardKeyGenerator;

import java.lang.annotation.*;

/**
 * 自定义锁的注解
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Lock {

    /**
     * 锁的名称
     *
     * @return
     */
    String name() default "";

    /**
     * 锁的过期时长
     *
     * @return
     */
    long expireSecond() default 60L;

    /**
     * 自定义锁的key，支持el表达式
     *
     * @return
     */
    String[] keys() default {};

    /**
     * 指定锁key的生成器
     *
     * @return
     */
    Class<? extends KeyGenerator> keyGenerator() default StandardKeyGenerator.class;

}
