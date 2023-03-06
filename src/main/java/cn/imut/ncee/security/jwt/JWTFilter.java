package cn.imut.ncee.security.jwt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.imut.ncee.entity.pojo.JWTTokenEntity;
import cn.imut.ncee.exception.ErrCode;
import cn.imut.ncee.exception.SystemException;
import cn.imut.ncee.service.JWTService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;


public class JWTFilter implements Filter {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private static final String OS_TOKEN = "OS-Token";

    private static final String OS_ORG = "org";

    @Autowired
    private JWTService jwtService;

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("utf-8");
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String jwtToken = decideOSToken(httpRequest);
        String osOrg = decideOSOrg(httpRequest);
        if(StringUtils.isBlank(jwtToken)) {
            resultErrMessage(httpResponse);
        }
        JWTTokenEntity jwtTokenEntity = jwtService.getJwtTokenByOrgId(osOrg);
        if(null == jwtTokenEntity) {
            throw new SystemException(ErrCode.SYS_ZOOK_CREATE_ERROR);
        }
        Date date = Date.from(jwtTokenEntity.getCreateTime().minusSeconds(28800L));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        log.info("jwt is {}, pbKey is {}, prKey is {}, time is {}", jwtToken, jwtTokenEntity.getPbKey(), jwtTokenEntity.getPrKey(), formatter.format(date));
        if (!jwtService.validateTokenExpire(jwtTokenEntity.getCreateTime())) {
            throw new SystemException(ErrCode.SYS_ZOOK_CREATE_ERROR);
        }
        return;
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
            return null;
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
