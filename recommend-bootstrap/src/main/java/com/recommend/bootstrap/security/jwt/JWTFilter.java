package com.recommend.bootstrap.security.jwt;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;


public class JWTFilter implements Filter {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private static final String OS_TOKEN = "OS-Token";

    private static final String OS_ORG = "org";



    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
//        JWTServiceImpl jwtService = SpringContextHolder.getBean(JWTServiceImpl.class);
        servletRequest.setCharacterEncoding("utf-8");
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;

        Optional.ofNullable(decideOSToken(httpRequest))
                .ifPresent(jwtToken -> {
                    try {
//                        Map<String, Object> map = jwtService.jwtParse(jwtToken.orElse(null));
//                        String login = (String) map.get("login");
//                        String orgId = (String) map.get("orgId");
//                        UserDTO user = JSONUtil.toBean((String) map.get("user"), UserDTO.class);
//                        IContextInfoProxy.getInstance().setContextAttribute("token", jwtToken);
//                        IContextInfoProxy.getInstance().setContextAttribute("login", login);
//                        IContextInfoProxy.getInstance().setContextAttribute("orgId", orgId);
//                        IContextInfoProxy.getInstance().setContextAttribute("user", user.toString());
//                        IContextInfoProxy.getInstance().setContextAttribute("name", user.getName());
//                        log.info("JWT 解析成功~~~~~ login is {}, orgId is {}", login, orgId);
                        filterChain.doFilter(servletRequest, servletResponse);
                    } catch (Exception e) {
                        log.error("jwt token 解析失败.", e);
                        try {
                            resultErrMessage(httpResponse);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
    }

    private Optional<String> decideOSToken(HttpServletRequest request) {
        String headerToken = request.getHeader(OS_TOKEN);
        String paramToken = request.getParameter(OS_TOKEN);
        return Optional.ofNullable(StringUtils.isBlank(headerToken) ? paramToken : headerToken);
    }

    private String decideOSOrg(HttpServletRequest request) {
        String headerOrg = request.getHeader(OS_ORG);
        String paramOrg = request.getParameter(OS_ORG);
        return StringUtils.isBlank(headerOrg) ? (StringUtils.isBlank(paramOrg) ? "defaultOrg" : paramOrg) : headerOrg;
    }

    private void resultErrMessage(ServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.print("用户未登录~~~~~");
        writer.flush();
        writer.close();
    }

    @Override
    public void destroy() {

    }
}
