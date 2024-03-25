package com.recommend.bootstrap.auth.config.security.jwt;

import cn.hutool.core.util.CharsetUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.recommend.provider.util.JSONUtil;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StreamUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Optional;

/**
 * @Desc 装配器模式封装HttpServletRequest
 * @Author zhanglei
 * @Date 2024/3/25 14:08
 */
public class BodyHttpServletRequestWrapper extends HttpServletRequestWrapper {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private byte[] requestBody = null;

    private Charset charSet;

    private final String bodyTag = "cipherBody";

    public BodyHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        try (InputStream inputStream = request.getInputStream()) {
            String requestBodyStr = getRequestPostStr(request);
            if (Optional.ofNullable(requestBodyStr).isPresent() && requestBodyStr.contains(bodyTag)) {
                Map<String, String> map = JSONUtil.toBean(requestBodyStr, new TypeReference<Map<String, String>>() {});
                String value = map.getOrDefault(bodyTag, "");
                if (Base64.isBase64(value)) {
                    String resultStr = Optional.ofNullable(value).orElse("").replaceAll(" +", "+");
                    requestBody = Base64.decodeBase64(resultStr);
                } else {
                    requestBody = new byte[0];
                }
            } else {
                requestBody = StreamUtils.copyToByteArray(inputStream);
            }
            //强制刷新为null
            requestBodyStr = null;
        } catch (Exception e) {
            log.error("BodyHttpServletRequestWrapper", e);
        }
    }

    @Override
    public ServletInputStream getInputStream() {
        if (requestBody == null) {
            requestBody = new byte[0];
        }
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(requestBody);
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }
            @Override
            public boolean isReady() {
                return false;
            }
            @Override
            public void setReadListener(ReadListener readListener) {

            }
            @Override
            public int read() {
                return byteArrayInputStream.read();
            }
        };
    }

    public String getRequestPostStr(HttpServletRequest request) throws IOException {
        String charSetStr = Optional.ofNullable(request.getCharacterEncoding()).orElse(CharsetUtil.UTF_8);
        charSet = Charset.forName(charSetStr);
        return StreamUtils.copyToString(request.getInputStream(), charSet);
    }
}
