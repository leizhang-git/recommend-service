package com.recommend.provider.config;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @Auth zhanglei
 * @Date 2023/2/18 20:55
 */
@Configuration
public class ZookeeperConfig {

    private static final Logger log = LoggerFactory.getLogger(ZookeeperConfig.class);

    @Resource
    private ZookeeperParam zookeeperParam;
    private static CuratorFramework client = null;

    @PostConstruct
    public void init() {
        //重试策略，初始时间1s，重试10次
        RetryPolicy policy = new ExponentialBackoffRetry(
                zookeeperParam.getBaseSleepTimeMs(),
                zookeeperParam.getMaxRetries());

        //通过工厂创建 Curator
        client = CuratorFrameworkFactory.builder()
                .connectString(zookeeperParam.getServer())
                .authorization("digest",zookeeperParam.getDigest().getBytes())
                .connectionTimeoutMs(zookeeperParam.getConnectionTimeoutMs())
                .sessionTimeoutMs(zookeeperParam.getSessionTimeoutMs())
                .retryPolicy(policy).build();

        //开启连接
        client.start();
        log.info("zookeeper 初始化完成...");
    }

    public CuratorFramework getClient() {
        return client;
    }

    public void closeClient() {
        if(client != null) {
            client.close();
        }
    }
}
