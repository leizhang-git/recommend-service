package com.recommend.bootstrap.auth.config.security;

import cn.hutool.log.Log;
import com.recommend.consumer.exception.ErrCode;
import com.recommend.consumer.exception.SystemException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * @Desc 未认证授权
 * @Author zhanglei
 * @Date 2024/3/25 13:49
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    private static final Log log = Log.get();


    /**
     * 处理未授权的接口
     * @param request
     * @param response
     * @param authException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        String uri = request.getRequestURI();
        log.error(">>>>>>>>>>>>>>>没有权限访问！uri is 【[}】", uri);
        throw new SystemException(ErrCode.SYS_NOT_AUTH_ERROR);
    }
}
