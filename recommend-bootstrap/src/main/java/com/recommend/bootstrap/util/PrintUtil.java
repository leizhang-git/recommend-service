package com.recommend.bootstrap.util;

import com.recommend.consumer.util.SystemTimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Describe
 * @Author zhanglei
 * @Date 2023/6/19 13:30
 */
@Component
@EnableScheduling
public class PrintUtil {

    private final Logger logger = LoggerFactory.getLogger(PrintUtil.class);

    @Scheduled(fixedDelay = 1000 * 60)
    @Async
    public void printHello() {
        logger.info("现在是北京时间：{} >>>>>>>>>>>. Hello,World!", SystemTimeUtil.getCurrentTime());
    }
}
