package com.recommend.provider.web.rest;

import com.recommend.consumer.service.IHelloService;
import com.recommend.consumer.web.vm.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zhanglei
 *
 * @Date 2023/5/92126
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    private final Logger log = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private IHelloService helloService;

    @GetMapping("/getStr")
    public ResultVO<?> getHello() {
        return ResultVO.getSuccess(helloService.printf());
    }
}