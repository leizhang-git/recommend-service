package com.recommend.provider.service.cimpl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.RegisteredPayload;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;
import com.alibaba.fastjson.JSONObject;
import com.recommend.consumer.domain.pojo.Person;
import com.recommend.consumer.exception.StrException;
import com.recommend.consumer.service.JWTService;
import com.recommend.provider.config.ApplicationProperties;
import com.recommend.provider.domain.enums.JWTEnum;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auth zhanglei
 * @Date 2023/2/28 19:29
 */
@Service
public class JWTServiceImpl implements JWTService {

    private static final Logger log = LoggerFactory.getLogger(JWTServiceImpl.class);

    static final String AUTH_TOKEN_KEY = "administration-defined-key";

    static final JWTSigner jwtSigner = JWTSignerUtil.hs256(JWTEnum.AK.getValue().getBytes());

    public static final String key = "vdKey";

    public static final String secret = "996996996996996";

    private static HashMap<String, String> keyMap = new HashMap<>();


    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    private PersonServiceImpl personService;

    @Override
    public boolean validateTokenExpire(Instant createTime) {
        return createTime.getEpochSecond() + applicationProperties.getJwt().getExpireTime() >= Instant.now().getEpochSecond() + 28800L;
    }

    @Override
    public String createToken(String login, String orgId, String ts) {
        if(StringUtils.isBlank(login)) {
            throw new StrException("id为空！");
        }
        Map<String, String> payload = new HashMap<>();
        payload.put("login", login);
        payload.put("orgId", StringUtils.isBlank(orgId) ? "noneOrg" : orgId);
        Person person = personService.selectByIdPerson(login).get(0);
        payload.put("user", JSONObject.toJSONString(person));
        return createToken(payload);
    }

    @Override
    public Map<String, Object> jwtParse(String token) {
        if(StrUtil.isEmpty(token)) {
            throw new StrException("token is null.");
        }
        JWT jwtToken = JWT.of(token);
        jwtToken.setSigner(jwtSigner);

        //解析token
        JWTPayload jwtPayload = jwtToken.getPayload();
        cn.hutool.json.JSONObject claimsJson = jwtPayload.getClaimsJson();
        claimsJson.remove(AUTH_TOKEN_KEY);
        Map<String, Object> resultMap = JSONUtil.toBean(claimsJson, Map.class);
        //管理员直接放过去~~~~
        if(MapUtil.isNotEmpty(resultMap) && "admin".equals(resultMap.get("login")) && "defaultOrg".equals(resultMap.get("orgId"))) {
            return resultMap;
        }

        //1.验证hash256签名
        boolean hs256Bool = jwtToken.verify();
        if(!hs256Bool) {
            throw new StrException("hash256签名验证失败.");
        }

        //2.验证签名时间
        boolean validate = jwtToken.validate(120L);
        if(!validate) {
            throw new StrException("时间负载校验失败.");
        }

        //3.获取二重token认证校验
        Date date = jwtPayload.getClaimsJson().getDate(RegisteredPayload.ISSUED_AT);
        String source = String.valueOf(jwtPayload.getClaim(AUTH_TOKEN_KEY));
        String authToken = SecureUtil.md5(SecureUtil.sha256(key + "_" + DateUtil.formatDateTime(date) + "_" + secret));
        if(StringUtils.isBlank(authToken) || !source.equals(authToken)) {
            throw new StrException("二重身份认证校验失败.");
        }

        return resultMap;
    }


    /**
     * 生成jwt Token
     * @param payload 信息
     * @return token
     */
    public static String createToken(Map<String, String> payload) {
        Date date = new Date();
        log.info("date is {}", date.getTime());
        String authToken = SecureUtil.md5(SecureUtil.sha256(key + "_" + DateUtil.formatDateTime(date) + "_" + secret));
        //增加自定义签名
        payload.put(AUTH_TOKEN_KEY, authToken);
        //创建jwt签名
        return JWT.create()
                .addPayloads(payload)
                .setIssuedAt(date)
                .setExpiresAt(DateUtil.endOfDay(date))
                .sign(jwtSigner);
    }

}
