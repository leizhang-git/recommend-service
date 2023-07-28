package com.recommend.provider.util.lock;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Describe
 * @Author zhanglei
 * @Date 2023/7/13 17:38
 */
public class AtomicUtil {

    /**
     * 基本数据类型
     */
    AtomicInteger c1 = new AtomicInteger(0);

    public static void main(String[] args) {
        AtomicInteger c1 = new AtomicInteger(0);
        c1.getAndIncrement();
        System.out.println(c1);
    }
    AtomicLong c2 = new AtomicLong(0);
    AtomicBoolean c3 = new AtomicBoolean(true);

    /**
     * 数组
     */
    int [] arr = {1,2,4,5,6};
    AtomicIntegerArray c4 = new AtomicIntegerArray(arr);

}
