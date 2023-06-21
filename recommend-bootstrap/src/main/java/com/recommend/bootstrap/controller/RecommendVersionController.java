package com.recommend.bootstrap.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.io.InputStream;
import java.util.Properties;

/**
 * @Describe
 * @Author zhanglei
 * @Date 2023/6/21 11:00
 */
@RestController
@RequestMapping(value = "/recommend")
@Slf4j
public class RecommendVersionController {

    private static String result = "";
    static {
        try {
            InputStream inputStream = RecommendVersionController.class.getResourceAsStream("/BOOT-INF/classes/git.properties");
            if(null != inputStream) {
                Properties p = new Properties();
                p.load(inputStream);
                result = p.toString();
            }
        } catch (Exception e) {
            log.error("",e);
        }
    }

    /**
     * 获取版本信息
     * @return
     */
    @GetMapping("/version")
    public String queryVersion() {
        return result;
    }


}
