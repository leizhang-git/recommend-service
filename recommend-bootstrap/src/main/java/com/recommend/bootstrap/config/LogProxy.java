package com.recommend.bootstrap.config;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.CallbackFilter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Describe
 * @Author zhanglei
 * @Date 2023/6/19 13:29
 */
public class LogProxy {
    private static Logger log = LoggerFactory.getLogger(LogProxy.class);

    public LogProxy() {
    }

    private static Callback callback1 = (MethodInterceptor) (obj, method, args, proxy) -> {

        // before
        long start = System.currentTimeMillis();
        log.info("callback1 [before] execute method: {}, inTime is {}", method.getName(), start);

        // execute
        Object result = proxy.invokeSuper(obj, args);

        // after
        long exeTime = System.currentTimeMillis() - start;
        String ms = String.format("%s%s", exeTime, "ms");
        log.info("callback1 [after] execute method: {}, [result]:{}, {}：{}",
                method.getName(), result, exeTime > 1000 ? "长耗时" : "短耗时", ms);
        return result;
    };

    private static Callback callback2 = (MethodInterceptor) (obj, method, args, proxy) -> {

        // before
        long start = System.currentTimeMillis();
        log.info("callback2 [before] execute method: {}, inTime is {}", method.getName(), start);

        // execute
        Object result = proxy.invokeSuper(obj, args);

        // after
        long exeTime = System.currentTimeMillis() - start;
        String ms = String.format("%s%s", exeTime, "ms");
        log.info("callback2 [after] execute method: {}, [result]:{}, {}：{}",
                method.getName(), result, exeTime > 1000 ? "长耗时" : "短耗时", ms);
        return result;
    };

    private static Callback callback3 = (MethodInterceptor) (obj, method, args, proxy) -> {

        // before
        long start = System.currentTimeMillis();
        log.info(">>>>>>>>>>>>>>开始执行方法{}", method.getName());

        // execute
        Object result = proxy.invokeSuper(obj, args);

        // after
        long exeTime = System.currentTimeMillis() - start;
        String ms = String.format("%s%s", exeTime, "ms");
        log.info(">>>>>>>>>>>>>>>执行完毕，共耗时：{}", ms);
        return result;
    };

    private static CallbackFilter callbackFilter = method -> {
        if ("printHello".equals(method.getName())) {
            return 0;
        } else {
            return 2;
        }
    };

    //创建代理对象
    public static Object getCglibProxy(Class<?> clazz) {

        //创建加强器，用来创建动态代理类
        Enhancer enhancer = new Enhancer();
        //设置父类
        enhancer.setSuperclass(clazz);
        //设置回调方法
        enhancer.setCallbacks(new Callback[] { callback1, callback2, callback3 });
        //设置回调方法过滤
        enhancer.setCallbackFilter(callbackFilter);
        //创建代理对象
        return enhancer.create();
    }

    public static Object getCglibProxy(Class<?> clazz, Class<?>[] args, Object[] argsValue) {

        //创建加强器，用来创建动态代理类
        Enhancer enhancer = new Enhancer();
        //设置父类
        enhancer.setSuperclass(clazz);
        //设置回调方法
        enhancer.setCallbacks(new Callback[] { callback1, callback2 });
        //设置回调方法过滤
        enhancer.setCallbackFilter(callbackFilter);
        //创建代理对象
        return enhancer.create(args, argsValue);
    }
}
