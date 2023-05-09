package com.recommend.provider.service.cimpl;

import com.recommend.consumer.service.IHelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Author zhanglei
 * @Date 2023/5/92130
 */
@Service
public class HelloServiceImpl implements IHelloService {

    private final Logger log = LoggerFactory.getLogger(HelloServiceImpl.class);

    private static final String HELLO_WORLD = "Hello,World";


    @Override
    public String printf() {
        return HELLO_WORLD;
    }
}
