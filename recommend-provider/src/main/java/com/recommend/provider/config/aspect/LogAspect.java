package com.recommend.provider.config.aspect;

import com.alibaba.fastjson.JSON;
import com.recommend.provider.util.SafeUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @auther zhanglei
 * @create 2023-04-22-22:36
 */
@Slf4j
@Component
@Aspect
public class LogAspect {

    @Pointcut("@annotation(com.recommend.provider.annotation.LogSign)")
    private void pointcut() {

    }

    @Around("pointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String in = "post".equalsIgnoreCase(request.getMethod())? JSON.toJSONString(request.getParameterMap()): request.getQueryString();
        log.info("【{}:in={}】【入参】:{}】",start, SafeUtil.replace(request.getRequestURI()) , SafeUtil.replace(in));
        Object result =joinPoint.proceed();
        long exeTime = System.currentTimeMillis() - start;
        log.warn("【{}:out={}】【返回结果】{}:{}ms!" ,start, request.getRequestURI() ,exeTime > 1000 ? "长耗时" : "短耗时", exeTime);
        return result;
    }
}
