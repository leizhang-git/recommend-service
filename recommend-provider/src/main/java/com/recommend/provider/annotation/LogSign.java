package com.recommend.provider.annotation;

import java.lang.annotation.*;

/**
 * @auther zhanglei
 * @create 2023-04-22-22:42
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogSign {

    /**
     * 模块名称
     * @return
     */
    public String name() default "";
}
