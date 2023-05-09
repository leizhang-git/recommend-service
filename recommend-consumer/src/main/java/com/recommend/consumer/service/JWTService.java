package com.recommend.consumer.service;


import org.springframework.security.core.Authentication;

import java.time.Instant;
import java.util.Map;

/**
 * @Auth zhanglei
 * @Date 2023/2/28 19:27
 */
public interface JWTService {

    /**
     * 判断token是否过期
     * @param createTime 创建时间
     * @return 是否过期
     */
    boolean validateTokenExpire(Instant createTime);


    String createToken(String login, String orgId, String ts);

    /**
     * 根据token、公钥获取认证信息
     * @param token
     * @param pk
     * @return
     */
    Authentication getAuthentication(String token, String pk);

    Map<String, Object> jwtParse(String token);
}
