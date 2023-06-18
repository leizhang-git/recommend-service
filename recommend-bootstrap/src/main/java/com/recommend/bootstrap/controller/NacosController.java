package com.recommend.bootstrap.controller;

import com.recommend.consumer.config.ApplicationProperties;
import com.recommend.consumer.web.vm.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Describe
 * @Author zhanglei
 * @Date 2023/6/15 15:57
 */
@RestController
@RequestMapping("/api/v1/nacos")
public class NacosController {

    private static final Logger log = LoggerFactory.getLogger(NacosController.class);

    @Autowired
    private ApplicationProperties applicationProperties;

    @GetMapping("/getEnv")
    public ResultVO<?> getProperties() {
        System.out.println(applicationProperties.getRecommendGlobal().getSuperAdmin());
        return ResultVO.getSuccess("");
    }
}