package com.recommend.provider.service.cimpl;

import com.recommend.consumer.service.InitService;
import com.recommend.consumer.util.ConstantDefinitionUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @auther zhanglei
 * @create 2023-04-30-23:46
 */
@Service
public class InitServiceImpl implements InitService {

    private static final String ALL_MACRO = "ALL_MACRO";

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public Map<String, Object> getMarcoValues(HttpServletRequest request) {
        Map<String,Object> result = new HashMap<>();
        Object allMacro = redisTemplate.opsForValue().get(ALL_MACRO);
        if(ObjectUtils.isEmpty(allMacro)) {
            allMacro = ConstantDefinitionUtil.getAllMacro();
            redisTemplate.opsForValue().set(ALL_MACRO, allMacro);
            redisTemplate.expire(ALL_MACRO, 60 * 60 * 24, TimeUnit.SECONDS);
        }
        result.putAll((Map<String, Object>) allMacro);
        return result;
    }
}
