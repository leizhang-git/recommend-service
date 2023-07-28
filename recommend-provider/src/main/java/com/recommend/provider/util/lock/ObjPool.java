package com.recommend.provider.util.lock;

import java.util.concurrent.Semaphore;

/**
 * @Describe
 * @Author zhanglei
 * @Date 2023/7/13 16:55
 */
public class ObjPool<T, R> {

    static int count;

    /**
     * 初始化信号量
     */
    static final Semaphore s = new Semaphore(1);

    /**
     * 用信号量保证互斥
     * @throws InterruptedException
     */
    static void addOne() throws InterruptedException {
        // 相当于 down
        s.acquire();
        try {
            count += 1;
        } finally {
            // 相当于up
            s.release();
        }
    }
}
