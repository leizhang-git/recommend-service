package com.recommend.bootstrap.security.jwt;

import cn.hutool.json.JSONUtil;
import com.recommend.bootstrap.context.IContextInfoProxy;
import com.recommend.provider.dto.UserDTO;
import com.recommend.provider.service.cimpl.JWTServiceImpl;
import com.recommend.provider.util.SpringContextHolder;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;


public class JWTFilter implements Filter {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private static final String OS_TOKEN = "OS-Token";

    private static final String OS_ORG = "org";



    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        JWTServiceImpl jwtService = SpringContextHolder.getBean(JWTServiceImpl.class);
        servletRequest.setCharacterEncoding("utf-8");
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        try {
            String jwtToken = decideOSToken(httpRequest);
            String osOrg = decideOSOrg(httpRequest);
            if(StringUtils.isBlank(jwtToken)) {
                resultErrMessage(httpResponse);
            }
            Map<String, Object> map = jwtService.jwtParse(jwtToken);
            String login = (String) map.get("login");
            String orgId = (String) map.get("orgId");
            UserDTO user = JSONUtil.toBean((String) map.get("user"), UserDTO.class);
            IContextInfoProxy.getInstance().setContextAttribute("token", jwtToken);
            IContextInfoProxy.getInstance().setContextAttribute("login", login);
            IContextInfoProxy.getInstance().setContextAttribute("orgId", orgId);
            IContextInfoProxy.getInstance().setContextAttribute("user", user.toString());
            log.info("JWT 解析成功~~~~~ login is {}, orgId is {}", login, orgId);
        }catch (Exception e){
            log.error("jwt token 解析失败.", e);
            resultErrMessage(httpResponse);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private String decideOSToken(HttpServletRequest request) {
        String headerToken = request.getHeader(OS_TOKEN);
        String paramToken = request.getParameter(OS_TOKEN);
        if(StringUtils.isBlank(headerToken) && StringUtils.isBlank(paramToken)) {
            return null;
        }else {
            return StringUtils.isBlank(headerToken) ? paramToken : headerToken;
        }
    }

    private String decideOSOrg(HttpServletRequest request) {
        String headerOrg = request.getHeader(OS_ORG);
        String paramOrg = request.getParameter(OS_ORG);
        if(StringUtils.isBlank(headerOrg) && StringUtils.isBlank(paramOrg)) {
            return "defaultOrg";
        }else {
            return StringUtils.isBlank(headerOrg) ? paramOrg : headerOrg;
        }
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
