package com.recommend.provider.util;

import com.recommend.consumer.exception.ErrCode;
import com.recommend.consumer.exception.SystemException;
import com.recommend.provider.config.ZookeeperConfig;
import org.apache.commons.lang.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auth zhanglei
 * @Date 2023/2/18 20:49
 */
@Component
public class ZookeeperUtil {
    private static final Logger log = LoggerFactory.getLogger(ZookeeperUtil.class);

    @Autowired
    private ZookeeperConfig zookeeperConfig;

    /**
     * 创建节点
     * @param mode
     * @param path
     */
    public void createNode(CreateMode mode, String path) {
        CuratorFramework client = zookeeperConfig.getClient();
        try {
            //递归创建所需要的父节点 creatingParentsIfNeeded
            client.create().creatingParentsIfNeeded().withMode(mode).forPath(path);
        } catch (Exception e) {
            log.error("isExistNode error ...", e);
            throw new SystemException(ErrCode.SYS_ZOOK_CREATE_ERROR);
        }
    }

    /**
     * 创建节点+数据
     * @param mode
     * @param path
     * @param nodeData
     */
    public void createNodeAndData(CreateMode mode, String path, String nodeData) {
        CuratorFramework client = zookeeperConfig.getClient();
        try {
            client.create().creatingParentsIfNeeded().withMode(mode).forPath(path,nodeData.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            log.error("createNode error...", e);
            throw new SystemException(ErrCode.SYS_ZOOK_CREATE_ERROR);
        }
    }

    /**
     * 设置节点数据
     * @param path
     * @param nodeData
     */
    public void setNodeData(String path, String nodeData) {
        CuratorFramework client = zookeeperConfig.getClient();
        try {
            client.setData().forPath(path, nodeData.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            log.error("isExistNode error ...", e);
            throw new SystemException(ErrCode.SYS_ZOOK_SET_NODE_ERROR);
        }
    }

    /**
     * 判断节点是否存在
     * @param path
     * @return
     */
    public boolean isExistNode(String path) {
        CuratorFramework client = zookeeperConfig.getClient();
        client.sync();
        try {
            //checkExists：启动一个现成的构造器，返回一个stat对象，判断是否存在
            //forPath：使用给定的路径实行当前的操作
            Stat stat = client.checkExists().forPath(path);
            return stat != null;
        } catch (Exception e) {
            log.error("isExistNode error ...", e);
            throw new SystemException(ErrCode.SYS_COMMON_INTERFACE_ERROR);
        }
    }

    /**
     * 获取节点数据
     * @param path
     * @return
     */
    public String getNodeData(String path) {
        CuratorFramework client = zookeeperConfig.getClient();
        try {
            byte[] dataByte = client.getData().forPath("UTF-8");
            String data = new String(dataByte, StandardCharsets.UTF_8);
            if(StringUtils.isNotBlank(data)) {
                return data;
            }
        } catch (Exception e) {
            log.error("getNodeData error...", e);
            throw new SystemException(ErrCode.SYS_COMMON_INTERFACE_ERROR);
        }
        return null;
    }

    /**
     * 获取节点下的所有数据
     * @param path
     * @return
     */
    public List<String> getNodeChild(String path) {
        CuratorFramework client = zookeeperConfig.getClient();
        List<String> nodeChildDataList = new ArrayList<>();
        try {
            // 节点下数据集
            nodeChildDataList = client.getChildren().forPath(path);
        } catch (Exception e) {
            log.error("getNodeChild error...", e);
            throw new SystemException(ErrCode.SYS_COMMON_INTERFACE_ERROR);
        }
        return nodeChildDataList;
    }

    /**
     * 是否递归删除节点
     * @param path
     * @param recursive
     */
    public void deleteNode(String path, Boolean recursive) {
        CuratorFramework client = zookeeperConfig.getClient();
        try {
            if(recursive) {
                //递归删除节点
                client.delete().guaranteed().deletingChildrenIfNeeded().forPath(path);
            }else {
                //删除单个节点
                client.delete().guaranteed().forPath(path);
            }
        }catch (Exception e) {
            log.error("deleteNode error...", e);
            throw new SystemException(ErrCode.SYS_ZOOK_DELETE_ERROR);
        }
    }

    /**
     * 获取读写锁
     * @param path
     * @return InterProcessReadWriteLock 分布式读写锁
     */
    public InterProcessReadWriteLock getReadWriteLock(String path) {
        CuratorFramework client = zookeeperConfig.getClient();
        //写锁互斥、读写互斥
        return new InterProcessReadWriteLock(client, path);
    }
}
