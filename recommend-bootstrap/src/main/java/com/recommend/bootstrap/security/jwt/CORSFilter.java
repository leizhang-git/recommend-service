package com.recommend.bootstrap.security.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 跨域过滤器
 * @Auth zhanglei
 * @Date 2023/2/26 0:09
 */
public class CORSFilter implements Filter {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(servletRequest.getServletContext());
        if(null == applicationContext) {
            log.error("-----------CORSFilter------------- application is null.");
            return;
        }
        //是否开启nginx
        String enableCORS = applicationContext.getEnvironment().getProperty("os.enableCORS");
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        boolean nextFilter = true;

        if(Boolean.parseBoolean(enableCORS)){
            response.setHeader("Content-Type", "text/html; charset=UTF-8");
            response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
            response.setHeader("Access-Control-Allow-Methods", "GET, POST, PATCH, OPTIONS, DELETE, PUT");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Headers", "x-requested-with,Authorization,Content-Type,Accept,encrypt,Pinpoint-TraceID,busiid,x-xsrf-token");
            response.setHeader("Access-Control-Allow-Credentials", "true");
            String method  = request.getMethod();
            //预检测
            if("OPTIONS".equals(method)){
                nextFilter = false;
                response.setStatus(HttpServletResponse.SC_OK);
            }
        }

        if(nextFilter){
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
