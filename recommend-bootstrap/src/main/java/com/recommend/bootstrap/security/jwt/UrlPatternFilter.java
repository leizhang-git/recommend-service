package com.recommend.bootstrap.security.jwt;

import cn.hutool.core.collection.CollUtil;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * @Auth zhanglei
 * @Date 2023/2/26 21:42
 */
public class UrlPatternFilter implements Filter {

    @Resource
    private ApplicationProperties applicationProperties;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String path = httpServletRequest.getRequestURI();
        if(isWhite(path)) {
            String pathInfo = httpServletRequest.getPathInfo();
            if (null == pathInfo){
                pathInfo = "";
            }

            if(httpServletRequest.getRequestURL().toString().endsWith("/os-wh/")) {
                path = httpServletRequest.getServletPath() + "index.html";
            }else{
                path = httpServletRequest.getServletPath() + pathInfo;
            }
            httpServletRequest.getRequestDispatcher(path).forward(servletRequest, servletResponse);
        }else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    /**
     * 免登判断
     * @param path
     * @return
     */
    private boolean isWhite(String path){
        List<String> whiteUrls = applicationProperties.getJwt().getWhiteUrls();
        if (CollUtil.isNotEmpty(whiteUrls)){
            for (String whiteUrl : whiteUrls) {
                if (path.startsWith(whiteUrl)) {
                    return true;
                }else{
                    if(path.contains(whiteUrl)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
