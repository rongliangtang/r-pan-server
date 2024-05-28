package com.tangrl.pan.lock.core.key;

import com.tangrl.pan.lock.core.LockContext;

/**
 * 锁的key的生成器顶级接口
 */
public interface KeyGenerator {

    /**
     * 生成锁的key
     *
     * @param lockContext
     * @return
     */
    String generateKey(LockContext lockContext);

}
