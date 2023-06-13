package com.recommend.bootstrap.task.listen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * @Describe
 * @Author zhanglei
 * @Date 2023/6/13 16:58
 */
@Configuration
public class RedisListenConfig {

    private static final Logger log = LoggerFactory.getLogger(RedisListenConfig.class);

    private final static String MESSAGE = "recommend-Task";

    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                            RedisListen listen) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        //订阅了一个叫chat 的通道
        container.addMessageListener(listen, new PatternTopic(MESSAGE));//配置要订阅的订阅项
        //这个container 可以添加多个 messageListener
        return container;
    }
}