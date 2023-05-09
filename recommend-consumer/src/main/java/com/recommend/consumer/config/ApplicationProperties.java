package com.recommend.consumer.config;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @Auth zhanglei
 * @Date 2023/2/25 23:45
 */
@Data
@ConfigurationProperties(
        prefix = "application",
        ignoreUnknownFields = false
)
public class ApplicationProperties {

    private static Logger log = LoggerFactory.getLogger(ApplicationProperties.class);

    //=====================================================================================================

    private final Jwt jwt = new Jwt();

    public Jwt getJwt() {
        return this.jwt;
    }

    public static class Jwt {
        private Long expireTime;
        private List<String> whiteUrls;

        public List<String> getWhiteUrls() {
            return whiteUrls;
        }

        public void setWhiteUrls(List<String> whiteUrls) {
            this.whiteUrls = whiteUrls;
        }

        public Jwt() {
        }

        public Long getExpireTime() {
            return this.expireTime;
        }

        public void setExpireTime(Long expireTime) {
            this.expireTime = expireTime;
        }

    }

    //=====================================================================================================

    private final BaiduProp baiduProp = new BaiduProp();

    public static class BaiduProp {

        private String apiHosts;

        private String appId;

        private String securityKey;

        public BaiduProp() {
        }

        public String getApiHosts() {
            return apiHosts;
        }

        public void setApiHosts(String apiHosts) {
            this.apiHosts = apiHosts;
        }

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getSecurityKey() {
            return securityKey;
        }

        public void setSecurityKey(String securityKey) {
            this.securityKey = securityKey;
        }
    }
}
