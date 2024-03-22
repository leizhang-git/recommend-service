package com.recommend.bootstrap.security.jwt;

import com.recommend.provider.config.ApplicationProperties;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

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

        if (isWhite(path)) {
            String pathInfo = Optional.ofNullable(httpServletRequest.getPathInfo()).orElse("");

            if (httpServletRequest.getRequestURL().toString().endsWith("/os-wh/")) {
                path = httpServletRequest.getServletPath() + "index.html";
            } else {
                path = httpServletRequest.getServletPath() + pathInfo;
            }
            httpServletRequest.getRequestDispatcher(path).forward(servletRequest, servletResponse);
        } else {
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
    private boolean isWhite(String path) {
        List<String> whiteUrls = applicationProperties.getJwt().getWhiteUrls();
        return whiteUrls != null && whiteUrls.stream().anyMatch(whiteUrl -> path.startsWith(whiteUrl) || path.contains(whiteUrl) || whiteUrl.contains(path));
    }
}
