package com.recommend.provider.service.cimpl;

import com.recommend.consumer.exception.StrException;
import com.recommend.consumer.lock.RedisLock;
import com.recommend.consumer.service.IHelloService;
import com.recommend.provider.util.SpringContextHolder;
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
        RedisLock redisLock = SpringContextHolder.getBean(RedisLock.class);
        try {
            if(!redisLock.tryLock(HELLO_WORLD)) {
                throw new StrException("获取Redis锁失败~");
            }
            return HELLO_WORLD;
        }finally {
            redisLock.releaseLock(HELLO_WORLD);
        }
    }
}
