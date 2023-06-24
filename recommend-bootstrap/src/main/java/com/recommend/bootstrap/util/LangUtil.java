package com.recommend.bootstrap.util;

import cn.hutool.crypto.digest.DigestUtil;
import com.alibaba.fastjson.JSONObject;
import com.recommend.provider.config.ApplicationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auth zhanglei
 * @Date 2023/3/13 9:20
 */
@Component
public class LangUtil {

    private static final Logger log = LoggerFactory.getLogger(LangUtil.class);

    @Autowired
    private ApplicationProperties applicationProperties;

    public static String APP_ID;
    public static String API_HOST;
    public static String SECURITY_KEY;

    @PostConstruct
    public void init() {
        APP_ID = applicationProperties.getBaiduProp().getAppId();
        API_HOST = applicationProperties.getBaiduProp().getApiHosts();
        SECURITY_KEY = applicationProperties.getBaiduProp().getSecurityKey();
    }

    public String chineseToEng(String str) throws UnsupportedEncodingException {
        return getTranslate(str, 1);
    }

    public String chineseToCht(String str) throws UnsupportedEncodingException {
        return getTranslate(str, 0);
    }

    /**
     * 初始语言：中文简体
     * @param str 源字符串
     * @param index 0: 简体 - 繁体  1：简体 - 英文
     * @return 翻译后内容
     */
    public String getTranslate(String str, Integer index) throws UnsupportedEncodingException {
        String to = "";
        if(index == 0) {
            to = "cht";
        }else if (index == 1) {
            to = "en";
        }
        // 随机数
        long salt = System.currentTimeMillis();

        // 签名
        String src = APP_ID + str + salt + SECURITY_KEY;
        String sign = DigestUtil.md5Hex(src);

        // 构造参数
        Map<String, Object> map = new HashMap<>();
        map.put("from", "zh");
        map.put("to", to);
        map.put("appid", APP_ID);
        map.put("salt", "" + salt);
        map.put("q",  URLEncoder.encode(str, "UTF-8"));
        map.put("sign", sign);
        Object value = new Object();
        try {
            String result = cn.hutool.http.HttpUtil.get(API_HOST, map);
            Object o = JSONObject.parseObject(result).getJSONArray("trans_result").get(0);
            value = JSONObject.parseObject(o.toString()).get("dst");
            log.info("百度翻译api: 入参:{},翻译结果:{},解析结果:{}", str, result, value);
        }catch (Exception e) {
            log.error("", e);
        }
        return value.toString();
    }

}
