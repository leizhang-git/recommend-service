package com.recommend.bootstrap.auth.config.security.jwt;

import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Auth zhanglei
 * @Date 2023/2/26 21:31
 */
public class BodyReqFilter implements Filter {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ServletRequest requestWrapper = null;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if(request != null) {
            String encrypt =  request.getHeader("encrypt");
            if(StrUtil.isNotEmpty(encrypt) && "true".equals(encrypt)) {
                requestWrapper = new BodyHttpServletRequestWrapper(request);
            }
        }
        if(requestWrapper == null) {
            log.warn("requestWrapper is null.");
            filterChain.doFilter(request, servletResponse);
        }else {
            filterChain.doFilter(requestWrapper, servletResponse);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
