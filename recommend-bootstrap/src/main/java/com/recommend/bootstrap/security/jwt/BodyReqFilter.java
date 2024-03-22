package com.recommend.bootstrap.security.jwt;

import com.recommend.provider.util.JSONUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StreamUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Optional;

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
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        Optional.ofNullable(request.getHeader("grateful"))
                .filter("true"::equals)
                .ifPresent(header -> {
                    try {
                        String charSetStr = Optional.ofNullable(request.getCharacterEncoding()).orElse("UTF-8");
                        Charset charSet = Charset.forName(charSetStr);
                        String requestBodyStr = StreamUtils.copyToString(request.getInputStream(), charSet);
                        if (StringUtils.isNotEmpty(requestBodyStr) && requestBodyStr.contains("body")) {
                            Map<String, String> map = JSONUtil.gsonToBean(requestBodyStr, Map.class);
                            String value = map.get("body");
                            if (StringUtils.isNotEmpty(value) && Base64.isBase64(value)) {
                                String resultStr = StringUtils.trimToNull(value).replaceAll(" +", "+");
                                byte[] requestBody = Base64.decodeBase64(resultStr);
                                // 使用 requestBody
                            }
                        } else {
                            byte[] requestBody = StreamUtils.copyToByteArray(request.getInputStream());
                            // 使用 requestBody
                        }
                    } catch (IOException e) {
                        log.error("", e);
                    }
                });
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
