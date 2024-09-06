package com.tangrl.pan.server.common.utils;

import com.tangrl.pan.core.constants.RPanConstants;

import java.util.Objects;

/**
 * 用户ID存储工具类
 * 将 userid 存放在 threadlocal 中，方便后续执行取出
 */
public class UserIdUtil {

    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    /**
     * 设置当前线程的用户ID
     *
     * @param userId
     */
    public static void set(Long userId) {
        threadLocal.set(userId);
    }

    /**
     * 获取当前线程的用户ID
     *
     * @return
     */
    public static Long get() {
        Long userId = threadLocal.get();
        if (Objects.isNull(userId)) {
            return RPanConstants.ZERO_LONG;
        }
        return userId;
    }

}
