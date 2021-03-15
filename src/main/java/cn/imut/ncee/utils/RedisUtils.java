package cn.imut.ncee.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Redis工具类,基于RedisTemplate
 * @Author zhanglei
 * @Date 2021/2/23 17:38
 */
@Component
public class RedisUtils {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    /**
     * 读取缓存
     * @param key key值
     * @return 返回value
     */
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 写入缓存
     * @param key key值
     * @param value value值
     * @return 是否写入成功
     */
    public boolean set(String key, String value) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().set(key,value);
            result = true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 更新缓存
     * @param key key值
     * @param value value值
     * @return 是否更新成功
     */
    public boolean getAndset(String key, String value) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().getAndSet(key,value);
            result = true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 删除缓存
     * @param key key值
     * @return 是否删除成功
     */
    public boolean delete(String key) {
        boolean result = false;
        try {
            redisTemplate.delete(key);
            result = true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
