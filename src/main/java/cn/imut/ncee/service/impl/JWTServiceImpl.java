package cn.imut.ncee.service.impl;

import cn.hutool.core.codec.Base32;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.unit.DataUnit;
import cn.hutool.core.map.MapUtil;
import cn.imut.ncee.config.ApplicationProperties;
import cn.imut.ncee.entity.pojo.JWTTokenEntity;
import cn.imut.ncee.exception.ErrCode;
import cn.imut.ncee.exception.StrException;
import cn.imut.ncee.exception.SystemException;
import cn.imut.ncee.repository.JWTRepository;
import cn.imut.ncee.service.JWTService;
import com.ctc.wstx.util.DataUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Auth zhanglei
 * @Date 2023/2/28 19:29
 */
@Service
public class JWTServiceImpl implements JWTService {

    private static HashMap<String, String> keyMap = new HashMap<>();

    @Autowired
    private JWTRepository jwtRepository;

    @Autowired
    private ApplicationProperties applicationProperties;

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
    public String createKey(String org, String ts, String ak, String sign) {
        HashMap<String, String> signMap = new HashMap<>();
        signMap.put("ts", ts);
        signMap.put("ak", ak);
        String SK = getSecret(ak);
        return null;
    }

    @Override
    public String createToken(String ak) {
        Map<String, String> payload = new HashMap<>();

        return null;
    }

    @Override
    public String createToken(String login, String orgId, String ts) {
        if(StringUtils.isBlank(login)) {
            throw new StrException("id为空！");
        }
       
        return null;
    }

    @Override
    public Authentication getAuthentication(String token, String pk) {
        return null;
    }


    private PublicKey getPublicKey(String key) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] keyBytes;
        keyBytes = (new BASE64Decoder()).decodeBuffer(key);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }

    public static String getSecret(String ak){
        String encodeStr = Base32.encode(ak);
        if(StringUtils.isNotEmpty(encodeStr)){
            String encodeAk = encodeStr.replaceAll("=","");
            String s = keyMap.get(encodeAk);
            return Base32.decodeStr(s);
        }
        return null;
    }

}
