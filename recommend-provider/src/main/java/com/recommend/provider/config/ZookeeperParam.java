package com.recommend.provider.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Auth zhanglei
 * @Date 2023/2/18 20:57
 */
@Data
@Component
public class ZookeeperParam {

    /**
     * 是否开启
     */
    @Value("${zookeeper.enabled}")
    private boolean enabled;

    /**
     * 服务器地址
     */
    @Value("${zookeeper.server}")
    private String server;

    /**
     * 命名空间
     */
    @Value("${zookeeper.nameSpace}")
    private String nameSpace;

    /**
     * 权限加密
     */
    @Value("${zookeeper.digest}")
    private String digest;

    /**
     * 会话超时时间
     */
    @Value("${zookeeper.sessionTimeoutMs}")
    private Integer sessionTimeoutMs;

    /**
     * 连接超时时间
     */
    @Value("${zookeeper.connectionTimeoutMs}")
    private Integer connectionTimeoutMs;

    /**
     * 最大重试次数
     */
    @Value("${zookeeper.maxRetries}")
    private Integer maxRetries;

    /**
     * 初始休眠时间
     */
    @Value("${zookeeper.baseSleepTimeMs}")
    private Integer baseSleepTimeMs;
}
