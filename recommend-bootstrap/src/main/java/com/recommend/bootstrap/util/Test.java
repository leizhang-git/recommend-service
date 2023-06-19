package com.recommend.bootstrap.util;

import com.recommend.bootstrap.config.LogProxy;

/**
 * @Author zhanglei
 * @Date 2023/5/111937
 */
public class Test {
    public static void main(String[] args) {
        MyTest myTest = new MyTest();
        new Thread(myTest).start();
        new Thread(myTest).start();
        new Thread(myTest).start();
        new Thread(myTest).start();
        new Thread(myTest).start();
    }
}

class MyTest implements Runnable {

    private static int current = 0;

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                if(current >= 10) {
                    break;
                }
                System.out.println("name:" + Thread.currentThread().getName() + "-" + "current:" + current);
                current++;
            }
        }
    }
}

class CglibTest {
    public static void main(String[] args) {
        // proxy
        PrintUtil printUtil = (PrintUtil) LogProxy.getCglibProxy(PrintUtil.class);

        // call
        String str = printUtil.printHello();
    }
}
