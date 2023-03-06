package cn.imut.ncee.security.jwt;

import javax.servlet.*;
import java.io.IOException;

/**
 * @Auth zhanglei
 * @Date 2023/3/6 21:25
 */
public class InvocationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
