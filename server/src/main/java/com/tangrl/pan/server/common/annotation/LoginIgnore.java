package com.tangrl.pan.server.common.annotation;

import java.lang.annotation.*;

/**
 * 该注解主要影响那些不需要登录的接口
 * 标注该注解的方法会自动屏蔽统一的登录拦截校验逻辑
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface LoginIgnore {
}
