package com.recommend.bootstrap.controller;

import com.recommend.bootstrap.domain.entity.Person;
import com.recommend.bootstrap.util.SerializationUtil;
import com.recommend.consumer.web.vm.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Describe
 * @Author zhanglei
 * @Date 2023/6/19 18:12
 */
@RestController
@RequestMapping("/test/boot/")
public class TestBootController {

    @Autowired
    private SerializationUtil serializationUtil;

    @GetMapping("/t")
    public ResultVO<?> test() throws Exception {
        Person person = new Person();
        serializationUtil.jdkSerialization(person);
        return ResultVO.getSuccess(true);
    }

}
