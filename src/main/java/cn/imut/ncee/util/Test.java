package cn.imut.ncee.util;

import java.util.concurrent.Callable;

/**
 * @auther zhanglei
 * @create 2023-04-17-17:51
 */
public class Test {

    static final Object lockA = new Object();

    static final Object lockB = new Object();

    public static void main(String[] args) {

        new Thread(() -> {
            System.out.println(Thread.currentThread() + " 尝试获取锁A");
            synchronized (lockA) {
                System.out.println(Thread.currentThread() + " 获取到了锁A");

                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread() + " 尝试获取锁B");
                synchronized (lockB) {
                    System.out.println(Thread.currentThread() + " 获取到了锁B");
                }
            }
        }).start();

        new Thread(() -> {
            System.out.println(Thread.currentThread() + " 尝试获取锁B");
            synchronized (lockA) {
                System.out.println(Thread.currentThread() + " 获取到了锁B");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread() + " 尝试获取锁A");
                synchronized (lockB) {
                    System.out.println(Thread.currentThread() + " 获取到了锁A");
                }
            }
        }).start();

    }
}
