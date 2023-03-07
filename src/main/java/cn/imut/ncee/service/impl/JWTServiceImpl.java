package cn.imut.ncee.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;
import cn.imut.ncee.config.ApplicationProperties;
import cn.imut.ncee.domain.enums.JWTEnum;
import cn.imut.ncee.entity.pojo.JWTTokenEntity;
import cn.imut.ncee.entity.pojo.Person;
import cn.imut.ncee.exception.StrException;
import cn.imut.ncee.repository.JWTRepository;
import cn.imut.ncee.service.JWTService;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
    private JWTRepository jwtRepository;

    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    private PersonServiceImpl personService;

    @Override
    public JWTTokenEntity getJwtTokenByOrgId(String orgId) {
        List<JWTTokenEntity> jwtKeys = jwtRepository.findAllByOrgId(orgId);
        if (CollUtil.isNotEmpty(jwtKeys)){
            return jwtKeys.get(0);
        }else{
            return null;
        }
    }

    @Override
    public JWTTokenEntity getJwtTokenByOrgIdOrCreate(String orgId) {
        List<JWTTokenEntity> jwtKeys = jwtRepository.findAllByOrgId(orgId);
        if (CollUtil.isNotEmpty(jwtKeys)){
            JWTTokenEntity jwtToken = jwtKeys.get(0);
            if (!validateTokenExpire(jwtToken.getCreateTime())){
                //过期
                return null;
            }
            return jwtKeys.get(0);
        }else{
            return null;
        }
    }

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
        payload.put("orgId", StringUtils.isBlank(orgId) ? "defaultOrg" : orgId);
        Person person = personService.selectByIdPerson(login).get(0);
        payload.put("user", JSONObject.toJSONString(person));
        return createToken(payload);
    }

    @Override
    public Authentication getAuthentication(String token, String pk) {
        return null;
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
