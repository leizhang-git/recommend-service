package com.recommend.provider.config.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.Resource;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author: zhang lei
 * @Date: 2022/3/24 14:33
 */
@Configuration
public class ThreadPoolConfig {

    private final Logger log = LoggerFactory.getLogger(ThreadPoolConfig.class);

    @Resource
    ThreadPoolProperties threadPoolProperties;

    @Bean
    public ThreadPoolTaskExecutor poolExecutor() {

        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        /**
         * 设置核心线程数
         */
        threadPoolTaskExecutor.setCorePoolSize(threadPoolProperties.getCorePoolSize());

        /**
         * 设置最大线程数
         */
        threadPoolTaskExecutor.setMaxPoolSize(threadPoolProperties.getMaxPoolSize());

        /**
         * 配置队列大小
         */
        threadPoolTaskExecutor.setQueueCapacity(threadPoolProperties.getQueueCapacity());

        /**
         * 设置线程活跃时间（s）
         */
        threadPoolTaskExecutor.setKeepAliveSeconds(threadPoolProperties.getKeepAliveSeconds());

        /**
         * 设置默认线程名称
         */
        threadPoolTaskExecutor.setThreadNamePrefix(threadPoolProperties.getDefaultName());

        /**
         * 拒绝策略
         * 不在新线程中执行任务，由调用者所在线程执行
         */
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        /**
         * 初始化
         */
        threadPoolTaskExecutor.initialize();

        log.info("corePoolSize[{}], maxPoolSize[{}], queueCapacity[{}], keepAliveSeconds[{}], threadName[{}]",
                threadPoolProperties.getCorePoolSize(),
                threadPoolProperties.getMaxPoolSize(),
                threadPoolProperties.getQueueCapacity(),
                threadPoolProperties.getKeepAliveSeconds(),
                threadPoolProperties.getDefaultName());

        log.info("----------------------------线程池加载完毕----------------------------------");
        return threadPoolTaskExecutor;
    }
}