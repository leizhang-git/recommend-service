package com.recommend.provider.util.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Describe 阻塞队列
 * @Author zhanglei
 * @Date 2023/7/13 16:31
 */
public class BlockedQueue<T> {

    /**
     * 阻塞队列
     */
    final Lock lock = new ReentrantLock();

    /**
     * 条件队列：队列不满
     */
    final Condition notFull = lock.newCondition();

    /**
     * 条件队列：队列不空
     */
    final Condition notEmpty = lock.newCondition();

    /**
     * 入队
     * @param x
     */
    void enq(T x) {
        lock.lock();
        try {
            // 队列已满
            while (queueFull()) {
                // 等待队列不满
                notFull.await();
            }
            // 入队
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    // 出队
    void deq(){
        lock.lock();
        try {
            // 队列已空
            while (queueEmpty()) {
                // 等待队列不空
                notEmpty.await();
            }
            // 出队
            // 出队后通知可以入队了
            notFull.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    boolean queueFull() {
        return true;
    }

    boolean queueEmpty() {
        return true;
    }
}
