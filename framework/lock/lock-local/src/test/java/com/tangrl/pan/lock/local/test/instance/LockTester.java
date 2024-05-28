package com.tangrl.pan.lock.local.test.instance;

import com.tangrl.pan.lock.core.annotation.Lock;
import org.springframework.stereotype.Component;

/**
 * lock实测实例
 */
@Component
public class LockTester {

    @Lock(name = "test", keys = "#name", expireSecond = 10L)
    public String testLock(String name) {
        System.out.println(Thread.currentThread().getName() + " get the lock.");
        String result = "hello " + name;
        System.out.println(Thread.currentThread().getName() + " release the lock.");
        return result;
    }

}
