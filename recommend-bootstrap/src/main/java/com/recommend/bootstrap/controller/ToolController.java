package com.recommend.bootstrap.controller;


import com.recommend.bootstrap.service.LangService;
import com.recommend.consumer.web.vm.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * @Auth zhanglei
 * @Date 2023/3/13 18:14
 */
@RestController
@RequestMapping("/api/v1/tool")
public class ToolController {

    private static final Logger log = LoggerFactory.getLogger(ToolController.class);


    @Resource
    private LangService langService;

    @GetMapping("/synLang")
    public ResultVO<?> queryAll() {
        try {
            langService.syncLang();
        }catch (Exception e) {
            log.error("synLang is error.", e);
            throw e;
        }
        return ResultVO.getSuccess(true);
    }
}
