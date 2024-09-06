package com.tangrl.pan.server.common.annotation;

import java.lang.annotation.*;

/**
 * 该注解主要影响那些不需要登录的接口
 * 标注该注解的方法会自动屏蔽统一的登录拦截校验逻辑
 */
// 表示该注解会被 javadoc 文档化。
@Documented
// 表示该注解在运行时依然存在。
@Retention(RetentionPolicy.RUNTIME)
// 表示该注解只能用于方法。
@Target({ElementType.METHOD})
public @interface LoginIgnore {
}
