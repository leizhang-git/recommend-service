package cn.imut.ncee.security.jwt;

import cn.hutool.http.server.HttpServerRequest;
import cn.hutool.http.server.HttpServerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;


public class JWTFilter implements Filter {

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServerResponse httpResponse = (HttpServerResponse) servletResponse;
//        servletRequest.setCharacterEncoding("utf-8");
//        HttpServerRequest httpRequest = (HttpServerRequest) servletRequest;
        log.info("JWTFilter...");
    }

    @Override
    public void destroy() {

    }
}
