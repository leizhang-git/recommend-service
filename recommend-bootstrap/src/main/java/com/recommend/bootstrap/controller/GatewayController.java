package com.recommend.bootstrap.controller;

import com.recommend.consumer.web.vm.ResultVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Describe 用于测试网关
 * @Author zhanglei
 * @Date 2023/6/20 17:36
 */
@RestController
@RequestMapping("/api/v1/gateway")
public class GatewayController {

    @GetMapping("/t")
    public ResultVO<?> test() {
        System.out.println("H");
        return ResultVO.getSuccess("OK");
    }
}
