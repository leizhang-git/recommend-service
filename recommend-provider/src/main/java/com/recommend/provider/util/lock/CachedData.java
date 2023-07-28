package com.recommend.provider.util.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Describe
 * @Author zhanglei
 * @Date 2023/7/13 17:12
 */
public class CachedData {

    Object data;

    volatile boolean cacheValid;

    final ReadWriteLock rwl = new ReentrantReadWriteLock();

    // 读锁
    final Lock r = rwl.readLock();

    // 写锁
    final Lock w = rwl.writeLock();

    void processCachedData() {
        // 获取读锁
        r.lock();
        if(!cacheValid) {
            // 释放读锁，因为不允许读锁升级
            r.unlock();
            // 获取写锁
            w.lock();
            try {
                // 检查状态
                if(!cacheValid) {
                    data = "Hello";
                    cacheValid = true;
                }
                // 释放写锁前，降级为读锁
                r.lock();
            } finally {
                // 释放写锁
                w.unlock();
            }
        }
        // 此处仍然持有读锁
        try {
            data = "xxx";
        }finally {
            r.unlock();
        }
    }
}
